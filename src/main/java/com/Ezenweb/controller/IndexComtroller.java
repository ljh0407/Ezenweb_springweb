package com.Ezenweb.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexComtroller {

    @GetMapping("/") // http://localhost:8080 -> 도메인구매시 www.ezenweb.com
    public Resource getindex() {
        return new ClassPathResource("templates/index.html");
    }

}
