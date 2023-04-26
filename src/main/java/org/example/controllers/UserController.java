package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.UserComponent;
import org.example.entity.User;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {
    @Autowired
    UserComponent userComponent;

    @GetMapping("allUsers")
    @Operation(summary = "Получение всех пользователей")
    public List<User> getAllUsers() {
        return userComponent.getAllUsers();
    }

    @GetMapping("byPhone")
    @Operation(summary = "Получение пользователя по номеру телефона")
    public User getUserBuPhone(
          @RequestParam  String phone) {
        return userComponent.getUserByPhone(phone);
    }

    @DeleteMapping("deleteUserById")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@RequestParam Long id) {
        userComponent.deleteUserById(id);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

}
