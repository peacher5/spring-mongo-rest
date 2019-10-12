package com.store.product.repository;

import com.store.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByBarcode(String barcode);

    List<Product> findByTags(String tag);

    long deleteByBarcode(String barcode);
}
