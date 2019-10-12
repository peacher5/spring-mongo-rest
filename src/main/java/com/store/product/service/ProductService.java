package com.store.product.service;

import com.store.product.model.Product;
import com.store.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    public Product getProductByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }

    public List<Product> getProductsByTag(String tag) {
        return repository.findByTags(tag);
    }

    public Product createProduct(Product product) {
        return repository.insert(product);
    }

    public Product updateProduct(String barcode, Product newProduct) {
        Product product = repository.findByBarcode(barcode);

        if (product == null) {
            return null;
        }

        String name = newProduct.getName();
        Double price = newProduct.getPrice();
        Set<String> tag = newProduct.getTags();

        if (name != null) product.setName(name);
        if (price != null) product.setPrice(price);
        if (tag != null) product.setTags(tag);

        return repository.save(product);
    }

    public void deleteProduct(String barcode) {
        if (repository.deleteByBarcode(barcode) == 0) {
            throw new IllegalArgumentException("Not found Product with barcode '" + barcode + "'");
        }
    }
}
