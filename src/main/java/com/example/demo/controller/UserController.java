package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
// 修改这里：用星号允许所有来源，或者精准匹配你的 5174 端口
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserRepository userRepository;

  // 删掉原来的 getAllUsers 和新加的 getUsers，统一改成下面这个：
@GetMapping
public List<User> getUsers(@RequestParam(required = false) String name) {
    if (name != null && !name.isEmpty()) {
        // 如果有搜索词，走模糊查询
        return userRepository.findByNameContaining(name);
    }
    // 如果没有搜索词，查询全部
    return userRepository.findAll();
}
   // 在 getAllUsers 方法下面添加这个删除接口
    @DeleteMapping("/{id}") // 这里的 "/{id}" 一个字符都不能错
    public void deleteUser(@PathVariable Long id) { // 必须有 @PathVariable 注解
        userRepository.deleteById(id);    }  
    @PostMapping
    public User  createUser(@RequestBody User user) {
        return  userRepository.save(user);
    }
    // 1. 处理更新请求，路径带上 ID
@PutMapping("/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    // 2. 先根据 ID 找到旧数据，如果找不到则抛出异常（或者你可以返回 null）
    return userRepository.findById(id).map(user -> {
        // 3. 将前端传来的新值赋给旧对象
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // 4. 保存并返回更新后的对象
        return userRepository.save(user);
    }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
}
  


}

