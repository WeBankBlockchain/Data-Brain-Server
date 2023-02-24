package com.webank.databrain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webank.databrain.db.dao.ISchemaService;
import com.webank.databrain.db.entity.DataSchemaDataObject;
import com.webank.databrain.db.entity.ProductDataObject;
import com.webank.databrain.db.entity.UserInfo;
import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaSummary;
import com.webank.databrain.model.dataschema.NewDataSchema;
import com.webank.databrain.model.dataschema.UpdatedDataSchema;
import com.webank.databrain.model.product.ProductDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSchemaService {

    @Autowired
    private ISchemaService schemaService;

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

    public String createDataSchema(NewDataSchema dataSchema, byte[] signature){
        return null;
    }

    public void updateDataSchema(UpdatedDataSchema dataSchema, byte[] signature){
    }

    public void deleteDataSchema(String id, byte[] signature){
    }
}
