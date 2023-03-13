package com.webank.databrain.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.dao.bc.contract.AccountModule;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.entity.CompanyInfoEntity;
import com.webank.databrain.dao.db.entity.PersonInfoEntity;
import com.webank.databrain.dao.db.mapper.AccountInfoMapper;
import com.webank.databrain.dao.db.mapper.CompanyInfoMapper;
import com.webank.databrain.dao.db.mapper.PersonInfoMapper;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.enums.AccountType;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.handler.token.ITokenHandler;
import com.webank.databrain.service.AccountService;
import com.webank.databrain.utils.AccountUtils;
import com.webank.databrain.utils.BlockchainUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.account.ApproveAccountRequest;
import com.webank.databrain.vo.request.account.LoginRequest;
import com.webank.databrain.vo.request.account.RegisterRequest;
import com.webank.databrain.vo.response.account.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoKeyPair witnessKeyPair;

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Autowired
    private ITokenHandler tokenHandler;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResponse registerAccount(RegisterRequest request) throws TransactionException, JsonProcessingException {
        //Generation private key
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        CryptoKeyPair keyPair = cryptoSuite.generateRandomKeyPair();
        //Save to blockchain
        AccountModule accountContract = AccountModule.load(
                sysConfig.getContractConfig().getAccountContract(),
                client,
                keyPair);
        TransactionReceipt txReceipt = accountContract.register(BigInteger.valueOf(request.getAccountType().ordinal()), cryptoSuite.hash(request.getUserName().getBytes()));
        byte[] didBytes = accountContract.getRegisterOutput(txReceipt).getValue1();
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        log.info("blockchain generate did : {}", AccountUtils.encode(didBytes));

        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        accountInfoEntity.setAccountType(request.getAccountType().ordinal());
        accountInfoEntity.setDid(AccountUtils.encode(didBytes));
        accountInfoEntity.setPwdHash(AccountUtils.getPwdHash(cryptoSuite, request.getPassword(), sysConfig.getSalt()));
        accountInfoEntity.setSalt(sysConfig.getSalt());
        accountInfoEntity.setStatus(AccountStatus.Registered.ordinal());
        accountInfoEntity.setPrivateKey(keyPair.getHexPrivateKey());
        accountInfoEntity.setUserName(request.getUserName());

        accountInfoMapper.insertAccount(accountInfoEntity);

        if (request.getAccountType().ordinal() == AccountType.Personal.ordinal()) {
            PersonInfoEntity personInfo = objectMapper.readValue(request.getDetailJson(), PersonInfoEntity.class);
            personInfo.setAccountId(accountInfoEntity.getPkId());
            personInfoMapper.insertPerson(personInfo);

        } else if (request.getAccountType().ordinal() == AccountType.Company.ordinal()) {
            CompanyInfoEntity companyInfo = objectMapper.readValue(request.getDetailJson(), CompanyInfoEntity.class);
            companyInfo.setAccountId(accountInfoEntity.getPkId());
            companyInfoMapper.insertCompany(companyInfo);
        }
        return CommonResponse.success();
    }

    @Override
    public CommonResponse login(LoginRequest request) {
        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        AccountInfoEntity accountInfo = accountInfoMapper.selectByUserName(request.getUserName());
        if (accountInfo == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        String pwdHash = AccountUtils.getPwdHash(cryptoSuite, request.getPassword(), accountInfo.getSalt());
        if (!pwdHash.equals(accountInfo.getPwdHash())) {
            return CommonResponse.error(CodeEnum.PWD_NOT_RIGHT);
        }
        String token = tokenHandler.generateToken(accountInfo.getDid());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setAccountType(accountInfo.getAccountType().intValue());
        response.setDid(accountInfo.getDid());
        return CommonResponse.success(response);
    }

    @Override
    public CommonResponse approveAccount(ApproveAccountRequest request) throws TransactionException {
        String did = request.getDid();
        boolean approve = request.isApproved();
        AccountInfoEntity entity = accountInfoMapper.selectByDid(did);
        if (entity == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        if(entity.getStatus() == AccountStatus.Approved.ordinal() || entity.getStatus() == AccountStatus.Denied.ordinal()){
            return  CommonResponse.error(CodeEnum.ACCOUNT_HAS_APPROVED);
        }
        byte[] didBytes = AccountUtils.decode(did);
        CryptoKeyPair witnessKeyPair = this.witnessKeyPair;
        AccountModule accountModule = AccountModule.load(sysConfig.getContractConfig().getAccountContract(), client, witnessKeyPair);
        TransactionReceipt txReceipt = accountModule.approve(didBytes, approve);
        BlockchainUtils.ensureTransactionSuccess(txReceipt, txDecoder);
        //修改数据库状态
        AccountStatus status = approve ? AccountStatus.Approved : AccountStatus.Denied;
        accountInfoMapper.updateStatus(did, status.ordinal());
        return CommonResponse.success();
    }
}
