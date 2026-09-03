package com.zestindia.product_management_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "item",schema = "zest")
@Entity
@AllArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private  Integer id;
    @ManyToOne
    @JoinColumn(name="product_id")
    private  Product productId;
    @Column(name="quantity", nullable = false)
    private Integer quantity;

    private Boolean isAvailable;

}
