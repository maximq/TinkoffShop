package org.example.entity;

public class Order {

    private Long id;
    private Long authorId;
    private Long productId;

    public Order(Long id, Long authorId, Long productId) {
        this.id = id;
        this.authorId = authorId;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
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
