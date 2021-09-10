package com.rdrake.VendingMachine.service;

import com.rdrake.VendingMachine.dao.VendingDao;
import com.rdrake.VendingMachine.dao.VendingDaoFileImpl;
import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingServiceLayerImpl implements VendingServiceLayer{
    VendingDao dao = new VendingDaoFileImpl();
    public VendingServiceLayerImpl(VendingDao dao){
        this.dao = dao;
    }

    @Override
    public List<Item> getItemList() {
        return dao.getAllItems();
    }

    @Override
    public String getItem(String item) {
        return null;
    }

    @Override
    public BigDecimal getChange() {
        return dao.getChange();
    }

    @Override
    public void insertChange(BigDecimal inputMoney) {
        dao.addChange(inputMoney);
    }

    @Override
    public BigDecimal returnChange() {
        return dao.removeChange();
    }
}
