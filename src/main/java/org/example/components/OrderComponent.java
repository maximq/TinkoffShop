package org.example.components;

import org.example.entity.Order;
import org.example.enums.ProductType;
import org.example.repositories.OrderRepository;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderComponent {
    @Autowired
    UserComponent userComponent;

    @Autowired
    ProductComponent productComponent;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;


    public List<Order> getListOfOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getListOfOrdersByUser(String phone) {
        var user = userComponent.getUserByPhone(phone);
        return orderRepository.findByAuthorId(user.getId());

    }



    /*
    перепиши меня !
    public static Order createOrder(String userName,
                                    String userPhone,
                                    String productName) {
        var user = UserComponent.getOrCreateUser(userName, userPhone);
        var product = ProductComponent.getProductByName(productName);

        if (product instanceof Goods) {
            Goods goods = (Goods) product;
            if (goods.getRemainder() < 1) {
                throw new IllegalStateException(
                        String.format(
                                "Товаров '%s' не осталось", productName
                        )
                );
            }
            goods.setRemainder(goods.getRemainder() - 1);
        }
        var order = new Order(listOfOrders.size()+1L, user.getId(), product.getId());
        listOfOrders.add(order);
        return order;
    }*/
}
