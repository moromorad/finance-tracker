package com.example.finance_tracker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/") // root URL
    public String hello() {
            return "Welcome to your Finance Tracker!";
    }

    @GetMapping("/sub")
    public String test() {
        return "This is a sub thing";
    }
}


