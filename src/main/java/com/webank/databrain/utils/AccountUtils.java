package com.webank.databrain.utils;

import cn.hutool.core.codec.Base64;
import com.webank.databrain.model.bo.CompanyInfoBO;
import com.webank.databrain.model.bo.PersonInfoBO;
import com.webank.databrain.vo.response.account.CompanyInfoResponse;
import com.webank.databrain.vo.response.account.PersonInfoResponse;
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

    public static CompanyInfoResponse companyBOToVO(CryptoSuite cryptoSuite, CompanyInfoBO bo){
        if (bo == null){
            return null;
        }
        CompanyInfoResponse vo = new CompanyInfoResponse();
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

    public static PersonInfoResponse personBOToVO(CryptoSuite cryptoSuite, PersonInfoBO bo){
        if (bo == null){
            return null;
        }
        PersonInfoResponse vo = new PersonInfoResponse();
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