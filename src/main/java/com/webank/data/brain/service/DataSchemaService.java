package com.webank.data.brain.service;

import com.webank.data.brain.model.common.Paging;
import com.webank.data.brain.model.common.PagingResult;
import com.webank.data.brain.model.dataschema.DataSchemaDetail;
import com.webank.data.brain.model.dataschema.DataSchemaSummary;
import com.webank.data.brain.model.dataschema.NewDataSchema;
import com.webank.data.brain.model.dataschema.UpdatedDataSchema;
import com.webank.data.brain.model.product.ProductSummary;

import java.util.List;

public class DataSchemaService {

    public List<PagingResult<DataSchemaSummary>> listDataSchemas(Paging paging, boolean forAudit) {
        return null;
    }

    public List<PagingResult<DataSchemaSummary>> listDataSchemasByEnterprise(String ownerId, Paging paging) {
        return null;
    }

    public List<PagingResult<DataSchemaSummary>> listDataSchemasByProduct(String productId, Paging paging) {
        return null;
    }

    public List<PagingResult<DataSchemaSummary>> listDataSchemasByTag(String tagId, Paging paging) {
        return null;
    }

    public List<PagingResult<DataSchemaSummary>> listDataSchemasBySearch(String keyword, Paging paging) {
        return null;
    }

    public DataSchemaDetail getDataSchemaById(String id){
        return null;
    }

    public String createDataSchema(NewDataSchema dataSchema, byte[] signature){
        return null;
    }

    public void updateDataSchema(UpdatedDataSchema dataSchema, byte[] signature){
    }

    public void deleteDataSchema(String id, byte[] signature){
    }
}
