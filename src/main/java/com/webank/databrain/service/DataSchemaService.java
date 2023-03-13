package com.webank.databrain.service;

import com.webank.databrain.config.SysConfig;
import com.webank.databrain.db.dao.DataSchemaInfoDAO;
import com.webank.databrain.db.dao.ProductInfoDAO;
import com.webank.databrain.model.resp.PagedResult;
import com.webank.databrain.model.resp.Paging;
import com.webank.databrain.model.resp.dataschema.DataSchemaDetail;
import com.webank.databrain.vo.common.CommonResponse;
import com.webank.databrain.vo.response.dataschema.DataSchemaInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.transaction.codec.decode.TransactionDecoderInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AccountService accountService;

    @Autowired
    private ProductInfoDAO productInfoDAO;

    @Autowired
    private TagService tagService;

    @Autowired
    private TransactionDecoderInterface txDecoder;

    @Autowired
    private DataSchemaInfoDAO dataSchemaInfoDAO;


    public CommonResponse pageQuerySchema(Paging paging, Long productId, Long providerId, Long tagId, String keyWord) {
        List<DataSchemaInfoResponse> dataSchemaInfoPOS = dataSchemaInfoDAO.pageQuerySchema(
                paging.getPageNo(),
                paging.getPageSize(),
                productId,
                providerId,
                tagId,
                keyWord);

        List<DataSchemaDetail> dataSchemaDetails = new ArrayList<>();
        dataSchemaInfoPOS.forEach(dataSchemaDataObject -> {
            DataSchemaDetail dataSchemaDetail = new DataSchemaDetail();
            BeanUtils.copyProperties(dataSchemaDataObject,dataSchemaDetail);
            dataSchemaDetails.add(dataSchemaDetail);
        });
        return CommonResponse.success(new PagedResult<>(
                dataSchemaDetails,
                paging.getPageNo(),
                paging.getPageSize()));
    }

//    public QueryDataSchemaByIdResponse getDataSchemaById(String schemaId){
//        DataSchemaDataObject schemaDataObject = schemaService.getOne(Wrappers.<DataSchemaDataObject>query().eq("schema_id",schemaId));
//        QueryDataSchemaByIdResponse schemaDetail = new QueryDataSchemaByIdResponse();
//        BeanUtils.copyProperties(schemaDataObject,schemaDetail);
//
//        VisitInfo visitInfo = visitInfoService.getOne(Wrappers.<VisitInfo>query().eq("schema_id",schemaId));
//        BeanUtils.copyProperties(visitInfo,schemaDetail);
//
//        return schemaDetail;
//    }
//
//    @Transactional
//    public CreateDataSchemaResponse createDataSchema(CreateDataSchemaRequest schemaRequest) throws Exception {
//
//        CompanyDetail orgUserDetail = accountService.getOrgDetail(schemaRequest.getProviderId());
//        if(orgUserDetail == null){
//            throw new DataBrainException(ErrorEnums.AccountNotExists);
//        }
//        ProductDetail productDetail = productService.getProductDetail(schemaRequest.getProductId());
//        if(productDetail == null){
//           throw new DataBrainException(ErrorEnums.ProductNotExists);
//        }
//        String productName = productDetail.getProductName();
//        String privateKey = accountService.getPrivateKey(schemaRequest.getProviderId());
//        CryptoKeyPair keyPair = cryptoSuite.loadKeyPair(privateKey);
//        DataSchemaModule dataSchemaModule = DataSchemaModule.load(
//                sysConfig.getContractConfig().getDataSchemaContract(),
//                client,
//                keyPair);
//
//        byte[] hash = cryptoSuite.hash((schemaRequest.getProductId() +
//                schemaRequest.getSchema() +
//                schemaRequest.getSchemaName() +
//                schemaRequest.getProviderId())
//                .getBytes(StandardCharsets.UTF_8));
//
//        TransactionReceipt receipt = dataSchemaModule.createDataSchema(hash);
//        BlockchainUtils.ensureTransactionSuccess(receipt, txDecoder);
//
//        String dataSchemaId = Base64.encode(dataSchemaModule.getCreateDataSchemaOutput(receipt).getValue1());
//
////        TagDetail tagDetail = tagService.getTagByName(schemaRequest.getTagName());
////        if(tagDetail == null){
////            CreateTagRequest createTagRequest = new CreateTagRequest();
////            createTagRequest.setTag(schemaRequest.getTagName());
////            tagService.createTag(createTagRequest);
////            log.info("createTag finish, schemaId = {}, tag = {}", dataSchemaId, schemaRequest.getTagName());
////        } else {
////            tagDetail.setSchemaIdList(tagDetail.getSchemaIdList() + "," + dataSchemaId);
////            tagService.updateTag(tagDetail);
////            log.info("updateTag finish, schemaId = {}, tag = {}", dataSchemaId, schemaRequest.getTagName());
////        }
//
//        DataSchemaDataObject dataSchemaDataObject = new DataSchemaDataObject();
//        BeanUtils.copyProperties(schemaRequest,dataSchemaDataObject);
//        dataSchemaDataObject.setSchemaId(dataSchemaId);
//        dataSchemaDataObject.setProductName(productName);
//        dataSchemaDataObject.setProviderName(orgUserDetail.getCompanyName());
//        dataSchemaDataObject.setTag(schemaRequest.getTagName());
//        schemaService.save(dataSchemaDataObject);
//        log.info("save dataSchemaDataObject finish, schemaId = {}", dataSchemaId);
//
//        VisitInfo visitInfo = new VisitInfo();
//        BeanUtils.copyProperties(schemaRequest,visitInfo);
//        visitInfo.setSchemaId(dataSchemaId);
//        visitInfoService.save(visitInfo);
//        log.info("save visitInfo finish, schemaId = {}", dataSchemaId);
//
//        return new CreateDataSchemaResponse(dataSchemaId);
//    }
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
