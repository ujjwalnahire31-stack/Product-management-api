package com.zestindia.product_management_api.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ItemsAssociatedWithProductResponseDTO {

    private Integer productId;
    List<ItemResponseDTO> items;
}
