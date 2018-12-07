package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Session;
import com.hwy.proj_425.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionMapper sessionMapper;

    public List<Session> findAll(){
        return sessionMapper.findAll();
    }
}
