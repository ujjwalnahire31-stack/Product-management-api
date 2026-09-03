package com.zestindia.product_management_api.mapper;

import com.zestindia.product_management_api.dto.request.ProductRequestDTO;
import com.zestindia.product_management_api.dto.response.ProductCustomResponseDTO;
import com.zestindia.product_management_api.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T10:12:16+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        return product.build();
    }

    @Override
    public ProductCustomResponseDTO toResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductCustomResponseDTO productCustomResponseDTO = new ProductCustomResponseDTO();

        productCustomResponseDTO.setProductId( product.getId() );
        productCustomResponseDTO.setProductName( product.getProductName() );

        return productCustomResponseDTO;
    }
}
