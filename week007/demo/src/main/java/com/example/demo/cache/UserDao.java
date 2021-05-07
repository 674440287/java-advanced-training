package com.example.demo.cache;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public User findUserById(Integer id) {
        if(id > 10) {
            return null;
        }
        return new User(id, "user-" + id);
    }
}
