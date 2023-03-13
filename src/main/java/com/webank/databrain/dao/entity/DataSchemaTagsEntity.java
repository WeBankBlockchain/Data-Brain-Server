package com.webank.databrain.dao.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class DataSchemaTagsEntity implements Serializable {

    private Long pkId;

    /**
     * 数据目录外键id
     */
    private Long dataSchemaId;

    /**
     * 标签外键id
     */
    private Long tagId;


}
