package com.techelevator.tenmo.services;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountService {

    private RestTemplate restTemplate;

    private JdbcAccountDao jdbcAccountDao;

    public AccountService(JdbcAccountDao jdbcAccountDao){
        this.jdbcAccountDao = jdbcAccountDao;
    }

    public Double getBalance(Integer id){
        return jdbcAccountDao.getBalance(id);
    }

}
