package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.OrderComponent;
import org.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderContoller {
    @Autowired
    OrderComponent orderComponent;

    @GetMapping("byUserPhone")
    @Operation(summary = "Получение заказов пользователя по номеру телефона")
    public List<Order> getAllUsers(
            @RequestParam String phone
    ) {
        return orderComponent.getListOfOrdersByUser(phone);
    }

    /**
     * createOrder(userName,userPhone,userProduct) -реализуй меня , используй метод POST
     */


}
