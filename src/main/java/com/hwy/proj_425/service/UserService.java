package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }

    public User login(User user){
        user=this.userMapper.login(user.getUserName(),user.getPassword());
        return user;
    }
}
