package com.hwy.proj_425.mapper;

import com.hwy.proj_425.entities.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionMapper {
    List<Session> findAll();
}
