package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.TransferService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

    public class AccountController {
        private AccountService accountService;
        private TransferService transferService;

        public AccountController(AccountService accountService, TransferService transferService) {
            this.accountService = accountService;
            this.transferService = transferService;
        }

        @RequestMapping(path = "balance/{id}", method = RequestMethod.GET)
        public double getBalance(@PathVariable Integer id){
            return accountService.getBalance(id);
        }


    }
