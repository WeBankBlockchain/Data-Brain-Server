package com.webank.databrain.utils;

import cn.hutool.core.codec.Base64;
import com.webank.databrain.enums.AccountStatus;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.model.resp.account.CompanyInfoVO;
import com.webank.databrain.model.resp.account.PersonInfoVO;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;

public class AccountUtils {

    public static byte[] hash() {
        return null;
    }

    public static String encode(byte[] didBytes) {
        return Base64.encode(didBytes);
    }

    public static byte[] decode(String did) {
        return Base64.decode(did);
    }

    public static String getPwdHash(CryptoSuite cryptoSuite, String password, String salt) {
        return cryptoSuite.hash(password+":"+salt);
    }

    public static CompanyInfoVO companyBOToVO(CryptoSuite cryptoSuite, CompanyInfoBO bo){
        if (bo == null){
            return null;
        }
        CompanyInfoVO vo = new CompanyInfoVO();
        vo.setCompanyContact(bo.getCompanyContact());
        vo.setDid(bo.getDid());
        vo.setCompanyName(bo.getCompanyName());
        vo.setCompanyDesc(bo.getCompanyDesc());
        vo.setCompanyCertType(bo.getCompanyCertType());
        vo.setKeyAddress(cryptoSuite.loadKeyPair(bo.getPrivateKey()).getAddress());
        vo.setStatus(bo.getStatus());
        vo.setCreateTime(bo.getCreateTime().getTime());
        vo.setCompanyCertFileUri(bo.getCompanyCertFileUri());
        return vo;
    }

    public static PersonInfoVO personBOToVO(CryptoSuite cryptoSuite, PersonInfoBO bo){
        if (bo == null){
            return null;
        }
        PersonInfoVO vo = new PersonInfoVO();
        vo.setDid(bo.getDid());
        vo.setStatus(bo.getStatus());
        vo.setPersonContact(bo.getPersonContact());
        vo.setPersonEmail(bo.getPersonEmail());
        vo.setPersonCertNo(bo.getPersonCertNo());
        vo.setKeyAddress(cryptoSuite.loadKeyPair(bo.getPrivateKey()).getAddress());
        vo.setPersonName(bo.getPersonName());
        vo.setPersonCertType(bo.getPersonCertType());
        vo.setCreateTime(bo.getCreateTime().getTime());
        return vo;
    }
}