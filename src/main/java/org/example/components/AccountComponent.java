package org.example.components;

import org.example.entity.Account;
import org.example.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class AccountComponent {
    @Autowired
    AccountRepository accountRepository;

    public Account getOrCreateAccount(Long userId, int balance) {
        var account = accountRepository.findByUserId(userId);
        if (account != null) {
            return account;
        }
        var newAccount = new Account(userId, balance);
        accountRepository.save(newAccount);
        return newAccount;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountByUserId(Long userId) {
        var user = accountRepository.findByUserId(userId);
        if (user != null) {
            return user;
        }

        throw new NoSuchElementException(
                String.format(
                        "Аккаунта с userId '%s' не существует!", userId));
    }

    public String accountRefill(Long userId, int balance) {
        var account = this.getOrCreateAccount(userId, balance);
        var newBalance = account.getBalance()+balance;
        account.setBalance(newBalance);
        accountRepository.save(account);
        return String.format("Счёт аккаунта с id = '%s' пополнен на '%s'", userId, balance);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);

    }
}
