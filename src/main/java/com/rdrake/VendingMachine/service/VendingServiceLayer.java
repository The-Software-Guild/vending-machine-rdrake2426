package com.rdrake.VendingMachine.service;

import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingServiceLayer {

    List<Item> getItemList();

    String getItem(String item);

    BigDecimal getChange();


    void insertChange(BigDecimal inputMoney);

    BigDecimal returnChange();
}
