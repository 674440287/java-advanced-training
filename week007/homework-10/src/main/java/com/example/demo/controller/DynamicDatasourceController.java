package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.DynamicDatasourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api")
public class DynamicDatasourceController {

    @Autowired
    private DynamicDatasourceService dynamicDatasourceService;

    @GetMapping("list")
    public Map<?,?> list(@RequestParam String userId) throws SQLException {
        Map<String, List<Order>> result = new HashMap<>();
        result.put("data",dynamicDatasourceService.query(userId));
        return result;
    }

    @GetMapping("create")
    public Map<?,?> create(@RequestParam String userId,@RequestParam String status) throws SQLException {
        Map<String, Boolean> result = new HashMap<>();
        result.put("data",dynamicDatasourceService.create(userId, status));
        return result;
    }

}
