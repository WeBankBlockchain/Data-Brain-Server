package com.webank.databrain.model.tag;

import lombok.Data;

@Data
public class TagDetail {

    private long tagId;

    private String tag;

    private Integer heat;

    private String schemaIdList;

}
