package com.zestindia.product_management_api.mapper;

import com.zestindia.product_management_api.dto.response.ItemResponseDTO;
import com.zestindia.product_management_api.entity.Item;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-03T10:12:16+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.12 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemResponseDTO toDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO();

        itemResponseDTO.setItemId( item.getId() );
        itemResponseDTO.setQuantity( item.getQuantity() );

        return itemResponseDTO;
    }
}
