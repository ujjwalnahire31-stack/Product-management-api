package com.zestindia.product_management_api.mapper;

import com.zestindia.product_management_api.dto.response.ItemResponseDTO;
import com.zestindia.product_management_api.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    @Mapping(source = "id", target = "itemId")

    ItemResponseDTO toDTO(Item item);
}
