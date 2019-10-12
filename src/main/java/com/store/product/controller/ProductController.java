package com.store.product.controller;

import com.store.product.model.Product;
import com.store.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProductsByTag(@RequestParam(required = false) String tag) {
        if (tag == null || tag.isEmpty()) {
            return service.getAllProduct();
        }
        return service.getProductsByTag(tag);
    }

    @GetMapping("/{barcode}")
    public Product getProductByBarcode(@PathVariable String barcode) {
        return service.getProductByBarcode(barcode);
    }

    @PostMapping
    public Product postProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{barcode}")
    public Product putProduct(@PathVariable String barcode, @RequestBody Product product) {
        return service.updateProduct(barcode, product);
    }

    @DeleteMapping("/{barcode}")
    public boolean deleteProduct(@PathVariable String barcode) {
        try {
            service.deleteProduct(barcode);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
