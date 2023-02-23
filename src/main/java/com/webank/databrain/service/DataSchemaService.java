package com.webank.databrain.service;

import com.webank.databrain.model.common.Paging;
import com.webank.databrain.model.common.PagingResult;
import com.webank.databrain.model.dataschema.DataSchemaDetail;
import com.webank.databrain.model.dataschema.DataSchemaSummary;
import com.webank.databrain.model.dataschema.NewDataSchema;
import com.webank.databrain.model.dataschema.UpdatedDataSchema;

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
