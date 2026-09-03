package com.zestindia.product_management_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product",schema = "zest")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @Column(name="product_name",nullable = false)
    private String productName;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="created_on")
    private LocalDateTime createdOn;
    @Column(name="modified_by")
    private String modifiedBy;
    @Column(name="modified_on")
    private  LocalDateTime modifiedOn;

    private Boolean isAvailable;

}
