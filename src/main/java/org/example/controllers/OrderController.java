package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.OrderComponent;
import org.example.entity.Order;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class OrderController {
    @Autowired
    OrderComponent orderComponent;

    @GetMapping("byUserPhone")
    @Operation(summary = "Получение заказов пользователя по номеру телефона")
    public List<Order> getAllUsers(
            @RequestParam String phone) {
        return orderComponent.getListOfOrdersByUser(phone);
    }

    @PostMapping("createOrder")
    @Operation(summary = "Добавление нового заказа")
    public Order createOrder(
            @RequestParam String userName,
            @RequestParam String userPhone,
            @RequestParam String productName) {
        return orderComponent.createOrder(userName,userPhone, productName);
    }

    @DeleteMapping("deleteOrderById")
    @Operation(summary = "Удаление заказа")
    public void deleteOrder(@RequestParam Long id) {
        orderComponent.deleteOrderById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

}
