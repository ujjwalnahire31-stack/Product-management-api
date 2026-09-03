package com.zestindia.product_management_api.dto.response;

import com.zestindia.product_management_api.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponseDTO {

    private Integer pageNo;
    private Integer pageSize;
    private Long totalRecords;
    private Integer totalPages;
    private List<ProductCustomResponseDTO> productList;

}
