package com.webank.databrain.utils;

public class PagingUtils {

    public static long getStartOffset(int pageNo, int pageSize){
        return (pageNo - 1)*pageSize;
    }

    public static int toPageCount(int itemsCount, int pageSize) {
        return (int)Math.ceil((double)itemsCount / pageSize);
    }
}
