package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.ProductComponent;
import org.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductContoller {
    @Autowired
    ProductComponent productComponent;

    @GetMapping("all")
    @Operation(summary = "Получение всех товаров")
    public List<Product> getAllProducts()
    {return productComponent.getListOfProducts();}


    @PutMapping("createProduct")
    @Operation(summary = "Добавление товара")
    public Product createProduct(
           @RequestParam String name,
           @RequestParam double price
    ){
        return productComponent.addNewGoods(name, price) ;
    }

}
