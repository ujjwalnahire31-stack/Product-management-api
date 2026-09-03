package com.zestindia.product_management_api.repository;

import com.zestindia.product_management_api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository< Item,Integer> {

    List<Item> findByProductId_IdAndIsAvailableTrue(Integer productId);

}
