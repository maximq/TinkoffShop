package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.components.AccountComponent;
import org.example.components.UserComponent;
import org.example.entity.Account;
import org.example.entity.User;
import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AccountController {
    @Autowired
    AccountComponent accountComponent;
    @Autowired
    UserComponent userComponent;

    @GetMapping("allAccounts")
    @Operation(summary = "Получение всех аккаунтов")
    public List<Account> getAllAccounts() {return accountComponent.getAllAccounts(); }

    @GetMapping("accountByUserId")
    @Operation(summary = "Получение аккаунта по userId")
    public Account getAccountByUserId(
          @RequestParam  Long userId) {
        return accountComponent.getAccountByUserId(userId);
    }

    @DeleteMapping("deleteAccountById")
    @Operation(summary = "Удаление аккаунта")
    public void deleteAccount(@RequestParam Long id) { accountComponent.deleteAccountById(id); }

    @PostMapping("accountRefill")
    @Operation(summary = "Пополнение счёта")
    public String accountRefill(@RequestParam String phone, @RequestParam int balance){
        var user = userComponent.getUserByPhone(phone);
        return accountComponent.accountRefill(user.getId(), balance);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleException(NoSuchElementException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

}
