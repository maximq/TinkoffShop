package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.ProductComponent;
import org.example.entity.Product;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    ProductComponent productComponent;

    @GetMapping("all")
    @Operation(summary = "Получение всех товаров")
    public List<Product> getAllProducts() {
        return productComponent.getListOfProducts();
    }

    @GetMapping("byProductName")
    @Operation(summary = "Получение товара по имени")
    public Product getProductByName (
            @RequestParam String productName){
        return productComponent.getProductByName(productName);
    }

    @PutMapping("createGood")
    @Operation(summary = "Добавление товара")
    public Product createGood(
            @RequestParam String name,
            @RequestParam double price) {
        return productComponent.addNewGoods(name, price);
    }

    @PutMapping("createServe")
    @Operation(summary = "Добавление услуги")
    public Product createServe(
            @RequestParam String name,
            @RequestParam double price) {
        return productComponent.addNewServe(name, price);
    }

    @DeleteMapping("deleteProductById")
    @Operation(summary = "Удаление товара")
    public void deleteProduct(@RequestParam Long id) {
        productComponent.deleteProductById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(exception.getMessage()));
    }



}
