package com.rdrake.VendingMachine.service;

import com.rdrake.VendingMachine.dao.*;
import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingServiceLayerImpl implements VendingServiceLayer{
    private final VendingDao dao;
    private VendingAuditDao auditDao;

    public VendingServiceLayerImpl(VendingDao dao, VendingAuditDao auditDao)  {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getItemList() {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemName) throws VendingPersistenceException, InsufficientFundsException, NoItemInventoryException {
        Item item = dao.getItem(itemName);
        auditDao.writeAuditEntry("Item Sold: £"+itemName);
        return item;
    }

    @Override
    public BigDecimal getChange() throws VendingPersistenceException {
        return dao.getChange();
    }

    @Override
    public void insertChange(BigDecimal inputMoney) throws VendingPersistenceException {
        dao.addChange(inputMoney);
        auditDao.writeAuditEntry("Change Added: £"+inputMoney);
    }

    @Override
    public BigDecimal returnChange() throws VendingPersistenceException {
        BigDecimal change = dao.removeChange();
        auditDao.writeAuditEntry("Change Returned: £"+change);
        return change;
    }
}
