package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/test")
public class DynamicDatasourceController {

    @GetMapping("test")
    public Map<?,?> list(@RequestParam(required = false) String text) {
        Map<String, String> result = new HashMap<>();
        result.put("msg","1234");
        return result;
    }

}
