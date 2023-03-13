package com.webank.databrain.service;

import cn.hutool.core.codec.Base64;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.dao.bc.contract.DataSchemaModule;
import com.webank.databrain.dao.db.entity.AccountInfoEntity;
import com.webank.databrain.dao.db.entity.DataSchemaAccessInfoEntity;
import com.webank.databrain.dao.db.entity.DataSchemaInfoEntity;
import com.webank.databrain.dao.db.entity.DataSchemaTagsEntity;
import com.webank.databrain.dao.db.mapper.*;
import com.webank.databrain.enums.CodeEnum;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.exception.DataBrainException;
import com.webank.databrain.handler.key.ThreadLocalKeyPairHandler;
import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.vo.common.Paging;
import com.webank.databrain.utils.BlockchainUtils;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.request.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.vo.response.dataschema.DataSchemaDetailResponse;
import com.webank.databrain.vo.response.dataschema.DataSchemaInfoResponse;
import com.webank.databrain.vo.response.dataschema.DataSchemaWithAccessResponse;
import com.webank.databrain.vo.response.product.ProductInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataSchemaService {

    @Autowired
    private Client client;


    @Autowired
    private CryptoKeyPair witnessKeyPair;
    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ThreadLocalKeyPairHandler keyPairHandler;

    @Autowired
    private TagService tagService;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Autowired
    private DataSchemaInfoMapper dataSchemaInfoMapper;

    @Autowired
    private DataSchemaAccessInfoMapper dataSchemaAccessInfoMapper;

    @Autowired
    private DataSchemaTagsMapper dataSchemaTagsMapper;


    public CommonResponse pageQuerySchema(Paging paging, Long productId, Long providerId, Long tagId, String keyWord) {
        List<DataSchemaInfoResponse> dataSchemaInfoPOS = dataSchemaInfoMapper.pageQuerySchema(
                paging.getPageNo(),
                paging.getPageSize(),
                productId,
                providerId,
                tagId,
                keyWord);

        List<DataSchemaDetailResponse> dataSchemaDetailResponses = new ArrayList<>();
        dataSchemaInfoPOS.forEach(dataSchemaDataObject -> {
            DataSchemaDetailResponse dataSchemaDetailResponse = new DataSchemaDetailResponse();
            BeanUtils.copyProperties(dataSchemaDataObject, dataSchemaDetailResponse);
            dataSchemaDetailResponses.add(dataSchemaDetailResponse);
        });
        return CommonResponse.success(new PagedResult<>(
                dataSchemaDetailResponses,
                paging.getPageNo(),
                paging.getPageSize()));
    }

    public CommonResponse getDataSchemaByGid(String schemaGid){
        DataSchemaWithAccessResponse dataSchemaWithAccessResponse = dataSchemaInfoMapper.getSchemaWithAccessByGid(schemaGid);
        return CommonResponse.success(dataSchemaWithAccessResponse);
    }

    @Transactional
    public CommonResponse createDataSchema(CreateDataSchemaRequest schemaRequest) throws Exception {

        CryptoSuite cryptoSuite = keyPairHandler.getCryptoSuite();
        AccountInfoEntity entity = accountInfoMapper.selectByDid(schemaRequest.getProviderGId());
        if (entity == null){
            return CommonResponse.error(CodeEnum.USER_NOT_EXISTS);
        }
        ProductInfoResponse product = productInfoMapper.getProductByGId(schemaRequest.getProductGId());
        if(product == null){
            throw new DataBrainException(ErrorEnums.ProductNotExists);
        }
        String privateKey = entity.getPrivateKey();
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
                sysConfig.getContractConfig().getDataSchemaContract(),
                client,
                keyPair);

        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() +
                schemaRequest.getContentSchema() +
                schemaRequest.getDataSchemaName() +
                schemaRequest.getProductGId())
                .getBytes(StandardCharsets.UTF_8));

        TransactionReceipt receipt = dataSchemaModule.createDataSchema(hash);
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);

        String dataSchemaId = Base64.encode(dataSchemaModule.getCreateDataSchemaOutput(receipt).getValue1());

        DataSchemaInfoEntity dataSchemaInfoEntity = new DataSchemaInfoEntity();
        BeanUtils.copyProperties(schemaRequest, dataSchemaInfoEntity);
        dataSchemaInfoEntity.setDataSchemaGid(dataSchemaId);
        dataSchemaInfoMapper.insertDataSchemaInfo(dataSchemaInfoEntity);
        log.info("save dataSchemaInfoEntity finish, schemaId = {}", dataSchemaId);

        DataSchemaAccessInfoEntity dataSchemaAccessInfoEntity = new DataSchemaAccessInfoEntity();
        BeanUtils.copyProperties(schemaRequest, dataSchemaAccessInfoEntity);
        dataSchemaAccessInfoEntity.setDataSchemaId(dataSchemaInfoEntity.getPkId());
        dataSchemaAccessInfoMapper.insertDataSchemaAccessInfo(dataSchemaAccessInfoEntity);
        log.info("save dataSchemaAccessInfoEntity finish, schemaId = {}", dataSchemaId);

        DataSchemaTagsEntity dataSchemaTagsEntity = new DataSchemaTagsEntity();
        dataSchemaTagsEntity.setDataSchemaId(dataSchemaInfoEntity.getPkId());
        dataSchemaTagsEntity.setTagId(schemaRequest.getTagId());
        dataSchemaTagsMapper.insertDataSchemaTag(dataSchemaTagsEntity);
        log.info("save dataSchemaTagsEntity finish, schemaId = {}", dataSchemaId);

        return CommonResponse.success(dataSchemaId);
    }
//
//    public UpdateDataSchemaResponse updateDataSchema(String did, UpdateDataSchemaRequest schemaRequest) throws TransactionException {
//        String privateKey = accountService.getPrivateKey(did);
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
//                sysConfig.getContractConfig().getAccountContract(),
//                client,
//                keyPair);
//
//        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() + schemaRequest.getSchema() + schemaRequest.getProviderId())
//                .getBytes(StandardCharsets.UTF_8));
//        TransactionReceipt receipt = dataSchemaModule.modifyDataSchema(
//                ByteUtils.hexStringToBytes(schemaRequest.getSchemaId()), hash);
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//
//        DataSchemaDataObject dataSchemaDataObject = new DataSchemaDataObject();
//        BeanUtils.copyProperties(schemaRequest,dataSchemaDataObject);
//        schemaService.saveOrUpdate(dataSchemaDataObject);
//        log.info("save dataSchemaDataObject finish, schemaId = {}", schemaRequest.getSchemaId());
//
//        VisitInfo visitInfo = new VisitInfo();
//        BeanUtils.copyProperties(schemaRequest,visitInfo);
//        visitInfoService.saveOrUpdate(visitInfo);
//        log.info("save visitInfo finish, schemaId = {}", schemaRequest.getSchemaId());
//
//        return new UpdateDataSchemaResponse(schemaRequest.getSchemaId());
//    }

}
