package com.cke.marketapp.dto.request;

import lombok.Data;

@Data
public class DepartmentRequest {
    private Long id;
    private Long shopId;
    private String departmentName;

}
