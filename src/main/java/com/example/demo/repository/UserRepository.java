package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    // 意思：根据姓名（Name）包含（Containing）某个关键字来查找
List<User> findByNameContaining(String name);
}
