package com.zestindia.product_management_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditProductRequestDTO {
    @NotBlank(message = "Please provide product name")
    @Size(max = 455, message = "Product name cannot exceed 455 characters")
    private  String productName;
}
