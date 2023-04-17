package org.example.components;

import org.example.entity.Goods;
import org.example.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderComponent {

    private static List<Order> listOfOrders = new ArrayList<>();

    public static List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public static List<Order> getListOfOrdersByUser(String phone) {
        var user = UserComponent.getUserByPhone(phone);
        List<Order> usersOrders = new ArrayList<>();
        for (Order order: listOfOrders) {
            if (order.getAuthorId().equals(user.getId())) {
                usersOrders.add(order);
            }
        }
        return usersOrders;
    }

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
    }
}
