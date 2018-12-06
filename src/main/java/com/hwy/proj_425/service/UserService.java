package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.exception.DuplicateIdException;
import com.hwy.proj_425.exception.ExistUserException;
import com.hwy.proj_425.exception.pointException;
import com.hwy.proj_425.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

//    @Cacheable(cacheNames = {"user"})
    public List<User> findAllUsers() {
        return userMapper.findAllUsers();
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public void createUser(User user) throws DuplicateIdException , ExistUserException, pointException {
        if(user.getId() < 0)
            throw new pointException();
        if(getUserById(user.getId())  != null)
            throw new DuplicateIdException();
        if(getUserByName(user.getUserName()) != null)
            throw new ExistUserException();
        userMapper.createUser(user);
    }
    public User getUserByName(String name){return userMapper.getUserByName(name);}
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }
}
