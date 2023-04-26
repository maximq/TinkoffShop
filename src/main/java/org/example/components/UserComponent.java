package org.example.components;

import org.example.entity.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UserComponent {
    @Autowired
    UserRepository userRepository;

    public User getOrCreateUser(String name, String phone) {
        var user = userRepository.findByPhone(phone);
        if (user != null) {
            return user;
        }
        var newUser = new User(name, phone);
        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByPhone(String phone) {
        var user = userRepository.findByPhone(phone);
        if (user != null) {
            return user;
        }

        throw new NoSuchElementException(
                String.format(
                        "Пользователя с телефоном '%s' не существует!", phone));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }
}
