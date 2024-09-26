package com.project.product.service;

import com.project.product.dto.ProductRequest;
import com.project.product.dto.ProductResponse;
import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();

}
