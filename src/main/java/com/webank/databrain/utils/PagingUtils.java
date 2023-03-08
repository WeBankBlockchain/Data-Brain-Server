package com.webank.databrain.utils;

public class PagingUtils {

    public static long getStartOffset(int pageNo, int pageSize){
        return (pageNo - 1)*pageSize;
    }
}
