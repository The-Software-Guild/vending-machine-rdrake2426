package com.rdrake.VendingMachine.dao;

import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingDao {

    List<Item> getAllItems();

    Item getItem(String itemName) throws InsufficientFundsException, NoItemInventoryException;

    String purchaseItem(String itemName);

    BigDecimal getChange();

    void addChange(BigDecimal inputMoney);

    BigDecimal removeChange();
}