package com.lyp.actuator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "api")
public class TestController {

    @GetMapping(path = "test")
    public String test(){
        return "success";
    }

}
