package com.webank.databrain.model.account;

import com.webank.databrain.model.common.IdName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 */
@Data
public class OrgSummary extends IdName {

    private String createdAt;
}
