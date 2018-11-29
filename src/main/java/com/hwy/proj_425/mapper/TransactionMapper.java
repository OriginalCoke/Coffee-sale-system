package com.hwy.proj_425.mapper;

import com.hwy.proj_425.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public interface TransactionMapper {
    void createTrans(Transaction transaction);
    //Transaction selectTransById(Integer id);
    List<Transaction> selectAllTrans();

    //List<Transaction> selectByTime(Date startTime, Date endTime);
}
