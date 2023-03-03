package com.webank.databrain.db.dao;

public interface ITokenDAO {
    void upsert(String token, String did);

}
