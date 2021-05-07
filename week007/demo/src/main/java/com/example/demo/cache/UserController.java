package com.example.demo.cache;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @MyCache(60)
    @RequestMapping("user/{id}")
    public User findUser(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }

}

