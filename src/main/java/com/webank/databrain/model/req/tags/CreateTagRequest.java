package com.webank.databrain.model.req.tags;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagRequest {

    private String tagName;

}
