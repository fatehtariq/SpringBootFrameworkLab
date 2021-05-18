package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() { return userService.all(); }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @PatchMapping("/{id}")
    public User update(@PathVariable("id") int id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") int id) { return userService.delete(id); }

    @GetMapping("/by-age/{age}")
    public List<User> getByAge(@PathVariable int age) {
        return userService.getByAge(age);
    }

    @GetMapping("/by-name/{name}")
    public List<User> getByAge(@PathVariable String name) {
        return userService.getByName(name);
    }
}
