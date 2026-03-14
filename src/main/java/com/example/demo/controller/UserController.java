package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173") // 允许 Vue 的默认端口跨域请求
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
   // 在 getAllUsers 方法下面添加这个删除接口
    @DeleteMapping("/{id}") // 这里的 "/{id}" 一个字符都不能错
    public void deleteUser(@PathVariable Long id) { // 必须有 @PathVariable 注解
        userRepository.deleteById(id);
    }

//hjhkjhk
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
