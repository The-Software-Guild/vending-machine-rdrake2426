package com.rdrake.VendingMachine.service;

import com.rdrake.VendingMachine.dao.InsufficientFundsException;
import com.rdrake.VendingMachine.dao.NoItemInventoryException;
import com.rdrake.VendingMachine.dao.VendingPersistenceException;
import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingServiceLayer {

    List<Item> getItemList();

    Item getItem(String item) throws VendingPersistenceException, InsufficientFundsException, NoItemInventoryException;

    BigDecimal getChange() throws VendingPersistenceException;

    void insertChange(BigDecimal inputMoney) throws VendingPersistenceException;

    BigDecimal returnChange() throws VendingPersistenceException;
}
