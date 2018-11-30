package com.hwy.proj_425.service;

import com.hwy.proj_425.entities.Customer;
import com.hwy.proj_425.entities.Product;
import com.hwy.proj_425.entities.Transaction;
import com.hwy.proj_425.entities.User;
import com.hwy.proj_425.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionMapper transMapper;
    @Override
    public void createAndSave(Map<Product, Integer> products, Customer customer, boolean type)
    {
        save(getTransaction(products, customer, type));
    }
    @Override
    public List<Transaction> getTransaction(Map<Product, Integer> products, Customer customer, boolean type)
    {
        Date createTime = new Date();
        List<Transaction> res = new ArrayList<>();
        for(Product product : products.keySet())
        {
            Transaction transaction = new Transaction();
            transaction.setCustomerId(customer.getId());
            transaction.setCustomer(customer);

            transaction.setProductId(product.getId());
            transaction.setProduct(product);
            transaction.setCount(products.get(product));
            if(type == true)
            transaction.setTotal(product.getPrice().multiply(BigDecimal.valueOf(products.get(product))));
            else
                transaction.setTotal(BigDecimal.valueOf(0));

            transaction.setTime(createTime);
            res.add(transaction);
        }
        return res;
    }
    @Override
    public void save(Collection<Transaction> trans)
    {
        for(Transaction tran : trans)
        {
            transMapper.createTrans(tran);
        }
    }

    @Override
    public List<Transaction> findAllTrans()
    {
        return transMapper.selectAllTrans();
    }

    public BigDecimal calTotal(int monthDiff)
    {

        List<Transaction> res = findTransInMonthDiff(monthDiff);
        return res.stream().map(trans -> trans.getTotal())
                  .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    public Integer calCount(int monthDiff)
    {

        List<Transaction> res = findTransInMonthDiff(monthDiff);

        return res.stream().map(trans -> trans.getCount())
                .reduce( (a, b) -> a + b).orElse(0);
    }
    private int compareMonth(Date date1, Date date2)
    {
        LocalDate dateA = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateB = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(dateA, dateB);

        if(period.getYears() > 0) return -1;
        return period.getMonths();
    }

    private List<Transaction> findTransInMonthDiff(int monthDiff)
    {
        List<Transaction> transactions = findAllTrans();

        transactions.sort(Comparator.comparing(Transaction::getTime));
        Transaction lastTrans = transactions.get(transactions.size() - 1);

        List<Transaction> res = new ArrayList<>();
        //res.add(lastTrans);
        Date lastDate = lastTrans.getTime();
        for(int i = transactions.size() - 1; i >=0; i--)
        {
            Transaction item = transactions.get(i);
            Date date = item.getTime();
            if(compareMonth(lastDate, date) < monthDiff)
                res.add(item);

        }
        return res;
    }
}
