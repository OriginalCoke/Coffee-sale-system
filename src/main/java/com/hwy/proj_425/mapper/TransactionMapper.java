package com.hwy.proj_425.mapper;

import com.hwy.proj_425.entities.Transaction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface TransactionMapper {
    void createTrans(Transaction transaction);

    //Transaction selectTransById(Integer id);
    List<Transaction> selectAllTrans();

    BigDecimal getPriceByTime(@Param("start") String startTime);

    BigDecimal getPriceByPeriod(@Param("start") String startTime, @Param("end") String endTime);
}
