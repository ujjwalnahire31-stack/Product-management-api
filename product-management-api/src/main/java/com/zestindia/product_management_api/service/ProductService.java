package com.zestindia.product_management_api.service;

import com.zestindia.product_management_api.dto.request.EditProductRequestDTO;
import com.zestindia.product_management_api.dto.request.NewProductRequestDTO;
import com.zestindia.product_management_api.dto.request.ProductRequestDTO;
import com.zestindia.product_management_api.exception.BadRequestException;
import com.zestindia.product_management_api.response.BaseResponse;

public interface ProductService {

    public BaseResponse getAllProducts(ProductRequestDTO  productRequestDTO);

    public BaseResponse getProductById(Integer productId) throws BadRequestException;

    public BaseResponse addNewProduct(NewProductRequestDTO requestDTO) throws BadRequestException;

    public BaseResponse editProduct(Integer productId,EditProductRequestDTO editRequestDTO) throws BadRequestException;

    public BaseResponse deleteProduct(Integer productId) throws BadRequestException;

    public BaseResponse getItemsAssociatedWithProduct(Integer productId) throws BadRequestException;
}
