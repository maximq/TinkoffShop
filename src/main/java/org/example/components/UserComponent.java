package org.example.components;

import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserComponent {
    private static List<User> listOfUsers = new ArrayList<>();

    public static User getOrCreateUser(String name, String phone) {
        for (User user : listOfUsers) {
            if (user.getPhone().equals(phone)) {
                return user;
            }
        }
        var newUser = new User(listOfUsers.size()+1L, name, phone);
        listOfUsers.add(newUser);
        return newUser;
    }
    public static List<User> getAllUsers() {
        return listOfUsers;
    }

    public static User getUserByPhone(String phone) {
        for (User user : listOfUsers) {
            if (user.getPhone().equals(phone)) {
                return user;
            }
        }
        throw new NoSuchElementException(
                String.format(
                        "Пользователя с телефоном '%s' не существует!", phone
                )
        );
    }
}
