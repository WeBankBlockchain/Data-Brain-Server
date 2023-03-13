package com.webank.databrain.dao.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_data_schema_tags")
public class DataSchemaTagsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
