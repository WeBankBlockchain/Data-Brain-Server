package com.webank.databrain.vo.request.product;

import com.webank.databrain.vo.common.CommonRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest extends CommonRequest {

    private String productName;

    private String productDesc;
}
