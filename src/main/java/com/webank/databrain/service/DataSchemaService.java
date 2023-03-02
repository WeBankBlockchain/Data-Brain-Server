package com.webank.databrain.service;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.blockchain.DataSchemaModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.ISchemaService;
import com.webank.databrain.db.dao.IVisitInfoService;
import com.webank.databrain.db.entity.DataSchemaDataObject;
import com.webank.databrain.db.entity.VisitInfo;
import com.webank.databrain.enums.ErrorEnums;
import com.webank.databrain.error.DataBrainException;
import com.webank.databrain.model.account.OrgUserDetail;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.*;
import com.webank.databrain.model.product.ProductDetail;
import com.webank.databrain.model.tag.CreateTagRequest;
import com.webank.databrain.model.tag.TagDetail;
import com.webank.databrain.utils.BlockchainUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.fisco.bcos.sdk.v3.utils.ByteUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DataSchemaService {

    @Autowired
    private Client client;

    @Autowired
    private CryptoSuite cryptoSuite;

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private ISchemaService schemaService;

    @Autowired
    private IVisitInfoService visitInfoService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TagService tagService;

    @Autowired
    private TransactionDecoderInterface txDecoder;
    public PagingResult<DataSchemaDetail> pageQuerySchema(Paging paging, String productId, String providerId, String tag, String keyWord) {
        QueryWrapper<DataSchemaDataObject> wrappers = Wrappers.<DataSchemaDataObject>query();
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(productId)){
            wrappers.eq("product_id",productId);
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(providerId)){
            wrappers.eq("provider_id",productId);
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(tag)){
            wrappers.eq("tag",tag);
        }
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(keyWord)){
            wrappers.like("description", keyWord).or()
                    .like("product_name",keyWord).or()
                    .like("provider_name",keyWord).or()
                    .like("schema_name",keyWord);
        }
        wrappers.orderByDesc("create_time");

        IPage<DataSchemaDataObject> result = schemaService.page(new Page<>(paging.getPageNo(),paging.getPageSize()),wrappers);
        List<DataSchemaDataObject> dataSchemaDataObjects = result.getRecords();
        List<DataSchemaDetail> dataSchemaDetails = new ArrayList<>();

        dataSchemaDataObjects.forEach(dataSchemaDataObject -> {
            DataSchemaDetail dataSchemaDetail = new DataSchemaDetail();
            BeanUtils.copyProperties(dataSchemaDataObject,dataSchemaDetail);
            dataSchemaDetails.add(dataSchemaDetail);
        });
        return new PagingResult<>(
                dataSchemaDetails,
                result.getCurrent(),
                result.getSize(),
                result.getTotal(),
                result.getPages());
    }

    public DataSchemaDetailWithVisit getDataSchemaById(String schemaId){
        DataSchemaDataObject schemaDataObject = schemaService.getOne(Wrappers.<DataSchemaDataObject>query().eq("schema_id",schemaId));
        DataSchemaDetailWithVisit schemaDetail = new DataSchemaDetailWithVisit();
        BeanUtils.copyProperties(schemaDataObject,schemaDetail);

        VisitInfo visitInfo = visitInfoService.getOne(Wrappers.<VisitInfo>query().eq("schema_id",schemaId));
        BeanUtils.copyProperties(visitInfo,schemaDetail);

        return schemaDetail;
    }

    @Transactional
    public String createDataSchema(CreateDataSchemaRequest schemaRequest) throws Exception {

        OrgUserDetail orgUserDetail = accountService.getOrgDetail(schemaRequest.getProviderId());
        if(orgUserDetail == null){
            throw new DataBrainException(ErrorEnums.DidNotExists);
        }
        ProductDetail productDetail = productService.getProductDetail(schemaRequest.getProductId());
        if(productDetail == null){
           throw new DataBrainException(ErrorEnums.ProductNotExists);
        }
        String productName = productDetail.getProductName();
        String privateKey = accountService.getPrivateKey(schemaRequest.getProviderId());
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
                sysConfig.getContracts().getDataSchemaContract(),
                client,
                keyPair);

        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() +
                schemaRequest.getSchema() +
                schemaRequest.getSchemaName() +
                schemaRequest.getProviderId())
                .getBytes(StandardCharsets.UTF_8));

        TransactionReceipt receipt = dataSchemaModule.createDataSchema(hash);
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);

        String dataSchemaId = Base64.encode(dataSchemaModule.getCreateDataSchemaOutput(receipt).getValue1());

        TagDetail tagDetail = tagService.getTagByName(schemaRequest.getTagName());
        if(tagDetail == null){
            CreateTagRequest createTagRequest = new CreateTagRequest();
            createTagRequest.setTag(schemaRequest.getTagName());
            tagService.createTag(createTagRequest);
            log.info("createTag finish, schemaId = {}, tag = {}", dataSchemaId, schemaRequest.getTagName());
        } else {
            tagDetail.setSchemaIdList(tagDetail.getSchemaIdList() + "," + dataSchemaId);
            tagService.updateTag(tagDetail);
            log.info("updateTag finish, schemaId = {}, tag = {}", dataSchemaId, schemaRequest.getTagName());
        }

        DataSchemaDataObject dataSchemaDataObject = new DataSchemaDataObject();
        BeanUtils.copyProperties(schemaRequest,dataSchemaDataObject);
        dataSchemaDataObject.setSchemaId(dataSchemaId);
        dataSchemaDataObject.setProductName(productName);
        dataSchemaDataObject.setProviderName(orgUserDetail.getOrgName());
        dataSchemaDataObject.setTag(schemaRequest.getTagName());
        schemaService.save(dataSchemaDataObject);
        log.info("save dataSchemaDataObject finish, schemaId = {}", dataSchemaId);

        VisitInfo visitInfo = new VisitInfo();
        BeanUtils.copyProperties(schemaRequest,visitInfo);
        visitInfo.setSchemaId(dataSchemaId);
        visitInfoService.save(visitInfo);
        log.info("save visitInfo finish, schemaId = {}", dataSchemaId);

        return dataSchemaId;
    }

    public void updateDataSchema(UpdatedDataSchemaRequest schemaRequest) throws TransactionException {
        String privateKey = accountService.getPrivateKey(schemaRequest.getDid());
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() + schemaRequest.getSchema() + schemaRequest.getProviderId())
                .getBytes(StandardCharsets.UTF_8));
        TransactionReceipt receipt = dataSchemaModule.modifyDataSchema(
                ByteUtils.hexStringToBytes(schemaRequest.getSchemaId()), hash);
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);

        DataSchemaDataObject dataSchemaDataObject = new DataSchemaDataObject();
        BeanUtils.copyProperties(schemaRequest,dataSchemaDataObject);
        schemaService.saveOrUpdate(dataSchemaDataObject);
        log.info("save dataSchemaDataObject finish, schemaId = {}", schemaRequest.getSchemaId());

        VisitInfo visitInfo = new VisitInfo();
        BeanUtils.copyProperties(schemaRequest,visitInfo);
        visitInfoService.saveOrUpdate(visitInfo);
        log.info("save visitInfo finish, schemaId = {}", schemaRequest.getSchemaId());

    }

    public void deleteDataSchema(DeleteDataSchemaRequest schemaRequest) throws TransactionException {
        String privateKey = accountService.getPrivateKey(schemaRequest.getDid());
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        TransactionReceipt receipt = dataSchemaModule.deleteDataSchema(
                ByteUtils.hexStringToBytes(schemaRequest.getSchemaId()));
        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
        log.info("deleteDataSchema finish, schemaId = {}", schemaRequest.getDid());
    }
}
