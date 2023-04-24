package org.example.components;

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


}
