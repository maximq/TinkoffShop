package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="orders")
@Setter
@Getter
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long authorId;
    private Long productId;

    public Order(Long authorId, Long productId) {
        this.authorId = authorId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", productId=" + productId +
                '}';
    }
}
