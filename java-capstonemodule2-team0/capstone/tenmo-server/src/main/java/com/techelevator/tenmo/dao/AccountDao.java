package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Balance;

public interface AccountDao {

   public Account getAccountByUserId(Integer id);

   public Double getBalance(Integer id);

   public Account depositAccount(Integer accountToId, double amount);

   public Account withdrawalAccount(Integer accountFromId, double amount);
}

