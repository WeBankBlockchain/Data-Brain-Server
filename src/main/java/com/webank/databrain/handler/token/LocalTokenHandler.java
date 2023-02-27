package com.webank.databrain.handler.token;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LocalTokenHandler implements ITokenHandler{

    private LoadingCache<String, Boolean> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Boolean>() {
                @Override
                public Boolean load(String key) throws Exception {
                    // This is the code that will be executed if the requested key is not present in the cache
                    return Boolean.FALSE;
                }
            });

    @Override
    public String generateToken() {
        String token = UUID.randomUUID().toString();
        cache.put(token, Boolean.TRUE);
        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        try{
            return Boolean.TRUE.equals(cache.get(token));
        }
        catch (Exception ex){
            log.warn("error", ex);
            return false;
        }
    }
}
