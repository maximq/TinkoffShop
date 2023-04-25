package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.ProductComponent;
import org.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductComponent productComponent;

    @GetMapping("all")
    @Operation(summary = "Получение всех товаров")
    public List<Product> getAllProducts() {
        return productComponent.getListOfProducts();
    }


    @PutMapping("createProduct")
    @Operation(summary = "Добавление товара")
    public Product createProduct(
            @RequestParam String name,
            @RequestParam double price) {
        return productComponent.addNewGoods(name, price);
    }

}
