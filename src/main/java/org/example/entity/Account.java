package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="account")
@Getter
@Setter
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;
    private int balance;

    public Account(Long userId) {
        this.userId= userId;
        this.balance = 100;
    }

    public Account(Long userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
