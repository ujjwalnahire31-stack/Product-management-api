package com.zestindia.product_management_api.mapper;

import com.zestindia.product_management_api.dto.request.ProductRequestDTO;

import com.zestindia.product_management_api.dto.response.ProductCustomResponseDTO;
import com.zestindia.product_management_api.dto.response.ProductResponseDTO;
import com.zestindia.product_management_api.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO dto);

    @Mapping(source = "id", target = "productId")
    ProductCustomResponseDTO toResponseDTO(Product product);
}
