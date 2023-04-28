package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface TransferDao {
  public  List<Transfer> listTransfers();

    public Transfer viewTransferDetails(int id);

   public int sendTransfer(Transfer newTransfer);
    //Transfer sendTransfer(Transfer transfer);

    public List<Transfer> viewMyTransfers(Integer id);

    public List<Transfer> detailsOfTransfers(Integer id);
}
