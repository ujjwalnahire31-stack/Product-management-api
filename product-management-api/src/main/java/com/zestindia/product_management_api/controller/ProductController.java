package com.zestindia.product_management_api.controller;

import com.zestindia.product_management_api.dto.request.EditProductRequestDTO;
import com.zestindia.product_management_api.dto.request.NewProductRequestDTO;
import com.zestindia.product_management_api.dto.request.ProductRequestDTO;
import com.zestindia.product_management_api.exception.BadRequestException;
import com.zestindia.product_management_api.response.BaseResponse;
import com.zestindia.product_management_api.service.ProductService;
import com.zestindia.product_management_api.service.impl.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private final ProductService productService;

    ProductController(ProductServiceImpl productService){

        this.productService=productService;
    }

    @PostMapping()
    public BaseResponse addProduct(@RequestBody NewProductRequestDTO newProductRequestDTO) throws BadRequestException {
        return productService.addNewProduct(newProductRequestDTO);
    }
    @GetMapping()
    public BaseResponse getAllProductList(@ModelAttribute ProductRequestDTO productRequestDTO){
    return productService.getAllProducts(productRequestDTO);
    }

    @GetMapping("/{productId}")
    public BaseResponse getProductById(@PathVariable Integer productId) throws BadRequestException {

        return  productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public BaseResponse editProduct(@PathVariable Integer productId, @RequestBody EditProductRequestDTO editRequest) throws BadRequestException {
        return productService.editProduct(productId,editRequest);
    }

    @DeleteMapping("/{productId}")
    public BaseResponse deleteProduct(@PathVariable Integer productId) throws BadRequestException{
        return productService.deleteProduct(productId);
    }

    @GetMapping("{productId}/items")
    public BaseResponse getItemsAssociatedWithProduct(@PathVariable Integer productId) throws BadRequestException{
        return productService.getItemsAssociatedWithProduct(productId);

    }

}
