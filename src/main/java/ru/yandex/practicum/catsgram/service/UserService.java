package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.yandex.practicum.catsgram.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceptions.InvalidIdException;
import ru.yandex.practicum.catsgram.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final HashMap<String, User> users = new HashMap<>();

    public List<User> findAll() {
        log.debug("Текущее количество пользователей: {}", users.size());
        return users.values().stream().toList();
    }

    public User create(User user) {
        log.debug(user.toString());
        validateEmail(user.getEmail());
        if (users.containsKey(user.getEmail())) throw new UserAlreadyExistException();
        users.put(user.getEmail(), user);
        return user;
    }

    public User update(User user) {
        log.debug(user.toString());
        validateEmail(user.getEmail());
        users.put(user.getEmail(), user);
        return user;
    }

    public boolean checkUserByEmail(String email) {
        return email != null && users.containsKey(email);
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) throw new InvalidEmailException();
    }

    public Optional<User> findByEmail(String email) {
        validateEmail(email);
        return Optional.ofNullable(users.get(email));
    }
}
