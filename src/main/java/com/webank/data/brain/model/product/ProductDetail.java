package com.webank.data.brain.model.product;

import com.webank.data.brain.model.common.IdName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDetail {

    private Long productId;
    private String productName;
    private String providerId;
    private String information;
    private Integer reviewState;
    private LocalDateTime reviewTime;
    private LocalDateTime createTime;


}
