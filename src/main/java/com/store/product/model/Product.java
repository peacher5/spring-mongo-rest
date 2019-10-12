package com.store.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class Product {
    @Id
    private String id;

    @Indexed(unique = true)
    private String barcode;

    private String name;

    private Double price;

    private Set<String> tags;
}
