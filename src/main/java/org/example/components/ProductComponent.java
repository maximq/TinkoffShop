package org.example.components;

import org.example.entity.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
public class ProductComponent {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getListOfProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String name) {
        var product = productRepository.findByName(name);
        if (product != null ){
            return  product;
        }
        throw new NoSuchElementException(
                String.format("Продукта с именем '%s' нет!", name
                )
        );
    }
}
