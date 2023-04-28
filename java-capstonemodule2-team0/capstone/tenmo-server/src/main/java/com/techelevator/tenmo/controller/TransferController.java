package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.services.TransferService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
    public class TransferController {
    private TransferDao transferDao;
private JdbcTransferDao jdbcTransferDao;
        private TransferService transferService;
        private JdbcUserDao userDao;
        public TransferController (TransferService transferService, JdbcUserDao userDao) {
            this.transferService = transferService;
            this.userDao = userDao;
        }

        @RequestMapping(path = "transfers", method = RequestMethod.POST)
        public void sendTransfer (@Valid  @RequestBody Transfer newTransfer){
            transferService.sendTransfer(newTransfer);
            transferDao.sendTransfer(newTransfer);
        }

        @RequestMapping(path = "users", method = RequestMethod.GET)
        public List<User> findAllUsers() {
            return userDao.findAll();
        }

        @RequestMapping(path = "account/transfers/{id}",method = RequestMethod.GET)
        public List<Transfer> viewMyTransfers(@Valid @PathVariable Integer id){
            return transferService.viewMyTransfers(id);
        }

        @RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
        public List<Transfer> detailsOfTransfers(@Valid @PathVariable Integer id){
            return transferService.detailsOfTransfers(id);
        }

    }

