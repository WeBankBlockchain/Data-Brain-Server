package com.webank.databrain.db.mapper;

import java.time.LocalDateTime;

public interface TokenMapper {

    void createTable();

    void upsert(String token, String did, LocalDateTime expiredAt);
    
}
