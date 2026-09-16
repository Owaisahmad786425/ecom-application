package ecom_application.Service;


import ecom_application.Model.Product;
import ecom_application.Repository.ProductRepository;
import ecom_application.dto.ProductRequest;
import ecom_application.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }


    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(new java.math.BigDecimal(productRequest.getPrice()));
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());
    }

    private ProductResponse convertToResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice().toString());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setCategory(savedProduct.getCategory());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setIsActive(savedProduct.getIsActive());
        return response;
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
           return productRepository.findById(id).map(existingProduct ->{
               updateProductFromRequest(existingProduct, productRequest);
               Product updatedProduct = productRepository.save(existingProduct);
               return convertToResponse(updatedProduct);
           });
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findByIsActiveTrue().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());


    }

    public boolean deleteProduct(Long id) {
       return productRepository.findById(id).map(existingProduct -> {
           existingProduct.setIsActive(false);
           productRepository.save(existingProduct);
           return true;
       }).orElse(false);
    }

    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
}

