package com.Ezenweb.controller.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class Deletecontroller {
    // 1. p. 76
    @DeleteMapping("/{variable}")
    public  String DeleteVariable(@PathVariable("variable") String variable) {
        return variable;
    }

    // 2. p. 76
    @DeleteMapping("/request1")
    public  String  getRequestParam1(@RequestParam("variable") String variable) {
        return  variable;
    }

}
