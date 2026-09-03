package com.zestindia.product_management_api.service.impl;

import com.zestindia.product_management_api.dto.request.EditProductRequestDTO;
import com.zestindia.product_management_api.dto.request.NewProductRequestDTO;
import com.zestindia.product_management_api.dto.request.ProductRequestDTO;

import com.zestindia.product_management_api.dto.response.ItemResponseDTO;
import com.zestindia.product_management_api.dto.response.ItemsAssociatedWithProductResponseDTO;
import com.zestindia.product_management_api.dto.response.ProductCustomResponseDTO;
import com.zestindia.product_management_api.dto.response.ProductResponseDTO;
import com.zestindia.product_management_api.entity.Item;
import com.zestindia.product_management_api.entity.Product;
import com.zestindia.product_management_api.exception.BadRequestException;
import com.zestindia.product_management_api.mapper.ItemMapper;
import com.zestindia.product_management_api.mapper.ProductMapper;
import com.zestindia.product_management_api.repository.ItemRepository;
import com.zestindia.product_management_api.repository.ProductRepository;
import com.zestindia.product_management_api.response.BaseResponse;
import com.zestindia.product_management_api.service.ProductService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {

   private final ProductRepository  productRepository;

    private final ItemRepository itemRepository;

   @Autowired
   private ProductMapper productMapper;

    @Autowired
   private ItemMapper itemMapper;

    public ProductServiceImpl(ProductRepository productRepository,ItemRepository itemRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
    }
    @Override
    public BaseResponse getAllProducts(ProductRequestDTO productRequestDTO) {
        Page<Product> productPage;
        Sort sort = Sort.by("productName").ascending();

        if ("desc".equalsIgnoreCase(productRequestDTO.getSortOrder())) {
            sort = Sort.by("productName").descending();
        }
        Pageable pageable = PageRequest.of(productRequestDTO.getPageNo(), productRequestDTO.getPageSize(),sort);
        if(StringUtils.isEmpty(productRequestDTO.getSearchBy())){
            productPage = productRepository.findAll(pageable);
        }else{
            productPage=   productRepository.findByproductNameContainingIgnoreCase(productRequestDTO.getSearchBy(),pageable);
        }
        List<ProductCustomResponseDTO> products=  productPage.getContent().stream().map(productMapper::toResponseDTO).toList();

       ProductResponseDTO response=  ProductResponseDTO.builder()
                          .pageNo(productRequestDTO.getPageNo())
                          .pageSize(productRequestDTO.getPageSize())
                          .totalRecords(productPage.getTotalElements())
                          .totalPages(productPage.getTotalPages())
                          .productList(products).build();


        return new BaseResponse().setData(response);
    }
    @Override
    public BaseResponse getProductById(Integer productId) throws BadRequestException {
        if(ObjectUtils.isEmpty(productId) || productId <=0){
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Please provide correct product ID");
        }

       Optional<Product> optionalProduct =   productRepository.findById(productId);

        if(optionalProduct.isPresent()){
             Product product =    optionalProduct.get();
            return new BaseResponse().setData(productMapper.toResponseDTO(product));
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Invalid product ID");
        }
    }

    @Override
    public BaseResponse addNewProduct(NewProductRequestDTO requestDTO) throws BadRequestException {

      Product existingProduct= productRepository.findByProductNameIgnoreCase(requestDTO.getProductName());
      if(!ObjectUtils.isEmpty(existingProduct)){
          throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Duplicate product");
      }
      Product product=  Product.builder().productName(requestDTO.getProductName())
                .createdBy("Admin")
                .createdOn(LocalDateTime.now())
                .modifiedBy("Admin")
                .modifiedOn(LocalDateTime.now())
                .isAvailable(Boolean.TRUE)
                .build();

        Product newProduct= productRepository.save(product);

        return new BaseResponse().setData(newProduct);
    }

    @Override
    public BaseResponse editProduct(Integer productId,EditProductRequestDTO editRequestDTO) throws BadRequestException {

       Optional<Product> optionalProduct= productRepository.findById(productId);
       if(!ObjectUtils.isEmpty(optionalProduct)){
           Product product = optionalProduct.get();
           product.setProductName(editRequestDTO.getProductName());
           productRepository.save(product);
           return new BaseResponse().setData(product);
       }else{
           throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"Invalid Product ID");
       }

    }

    @Override
    public BaseResponse deleteProduct(Integer productId) throws BadRequestException {
        Optional<Product> optionalProduct= productRepository.findById(productId);
        if(!ObjectUtils.isEmpty(optionalProduct)){
            Product product = optionalProduct.get();
            product.setIsAvailable(Boolean.FALSE);
            productRepository.save(product);
            return new BaseResponse().setStatusCode();
        }else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"Invalid Product ID");
        }

    }

    @Override
    public BaseResponse getItemsAssociatedWithProduct(Integer productId) throws BadRequestException {
        if(ObjectUtils.isEmpty(productId) || productId <=0){
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(), "Please provide correct product ID");
        }
        Optional<Product> optionalProduct= productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"Invalid Product ID");

        }
        List<Item>items= itemRepository.findByProductId_IdAndIsAvailableTrue(productId);

        List<ItemResponseDTO> itemResponseDTO=items.stream().map(itemMapper::toDTO).toList();
        ItemsAssociatedWithProductResponseDTO   response = new ItemsAssociatedWithProductResponseDTO();
        response.setProductId(productId);
        response.setItems(itemResponseDTO);

        return new BaseResponse().setData(response);

    }


}
