package com.acme.orders.web;

import com.acme.orders.domain.Product;
import com.acme.orders.repository.ProductRepository;
import com.acme.orders.service.NotFoundException;
import com.acme.orders.web.dto.ProductResponse;
import com.acme.orders.web.dto.UpdatePriceRequest;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductResponse> list() {
        return productRepository.findAll().stream().map(ProductResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return ProductResponse.from(find(id));
    }

    @PutMapping("/{id}/price")
    @Transactional
    public ProductResponse updatePrice(@PathVariable Long id, @Valid @RequestBody UpdatePriceRequest request) {
        Product product = find(id);
        product.setPrice(request.price());
        return ProductResponse.from(product);
    }

    private Product find(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found: " + id));
    }
}
