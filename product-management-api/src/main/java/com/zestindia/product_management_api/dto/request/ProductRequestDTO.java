package com.zestindia.product_management_api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class ProductRequestDTO {
    @NotNull
    private Integer pageNo=0;
    @NotNull
    private Integer pageSize=10;
    private String sortOrder;
    private String searchBy;

}
