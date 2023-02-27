package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.blockchain.DataSchemaModule;
import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.ISchemaService;
import com.webank.databrain.db.dao.IVisitInfoService;
import com.webank.databrain.db.entity.DataSchemaDataObject;
import com.webank.databrain.db.entity.VisitInfo;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.CreateDataSchemaRequest;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaSummary;
import com.webank.databrain.model.dataschema.UpdatedDataSchema;
import com.webank.databrain.utils.BlockchainUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.TransactionException;
import org.fisco.bcos.sdk.v3.utils.StringUtils;
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

    public List<PagingResult<DataSchemaSummary>> listDataSchemas(Paging paging, boolean forAudit) {
        return null;
    }

    public PagingResult<DataSchemaDetail> listDataSchemasByEnterprise(String providerId, Paging paging) {
        IPage<DataSchemaDataObject> result = schemaService.page(new Page<>(paging.getPageNo(),paging.getPageSize()),
                Wrappers.<DataSchemaDataObject>query().eq("providerId",providerId));
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

    public PagingResult<DataSchemaDetail> listDataSchemasByProduct(String productId, Paging paging) {
        IPage<DataSchemaDataObject> result = schemaService.page(new Page<>(paging.getPageNo(),paging.getPageSize()),
                Wrappers.<DataSchemaDataObject>query().eq("productId",productId));
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

    public PagingResult<DataSchemaDetail> listDataSchemasByTag(String tagId, Paging paging) {
        IPage<DataSchemaDataObject> result = schemaService.page(new Page<>(paging.getPageNo(),paging.getPageSize()),
                Wrappers.<DataSchemaDataObject>query().eq("tagId",tagId));
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

    public PagingResult<DataSchemaDetail> listDataSchemasBySearch(String keyword, Paging paging) {
        IPage<DataSchemaDataObject> result = schemaService.page(new Page<>(paging.getPageNo(),paging.getPageSize()),
                Wrappers.<DataSchemaDataObject>query().like("description",keyword));
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

    public DataSchemaDetail getDataSchemaById(long schemaId){
        DataSchemaDataObject schemaDataObject = schemaService.getOne(Wrappers.<DataSchemaDataObject>query().eq("schemaId",schemaId));
        DataSchemaDetail schemaDetail = new DataSchemaDetail();
        BeanUtils.copyProperties(schemaDataObject,schemaDetail);
        return schemaDetail;
    }

    @Transactional
    public String createDataSchema(CreateDataSchemaRequest schemaRequest) throws TransactionException {
        String privateKey = accountService.getPrivateKey(schemaRequest.getDid());
        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
                sysConfig.getContracts().getAccountContract(),
                client,
                keyPair);

        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() + schemaRequest.getSchema() + schemaRequest.getProviderId())
                .getBytes(StandardCharsets.UTF_8));
        TransactionReceipt receipt = dataSchemaModule.createDataSchema(hash);
        BlockchainUtils.ensureTransactionSuccess(receipt);

        String dataSchemaId = StringUtils.fromByteArray(dataSchemaModule.getCreateDataSchemaOutput(receipt).getValue1());

        DataSchemaDataObject dataSchemaDataObject = new DataSchemaDataObject();
        BeanUtils.copyProperties(schemaRequest,dataSchemaDataObject);
        dataSchemaDataObject.setSchemaId(dataSchemaId);
        schemaService.save(dataSchemaDataObject);
        log.info("save dataSchemaDataObject finish, schemaId = {}", dataSchemaId);

        VisitInfo visitInfo = new VisitInfo();
        BeanUtils.copyProperties(schemaRequest,visitInfo);
        visitInfo.setSchemaId(dataSchemaId);
        visitInfoService.save(visitInfo);
        log.info("save visitInfo finish, schemaId = {}", dataSchemaId);

        return dataSchemaId;
    }

    public void updateDataSchema(UpdatedDataSchema dataSchema, byte[] signature){
    }

    public void deleteDataSchema(String id, byte[] signature){
    }
}
