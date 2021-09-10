package com.rdrake.VendingMachine.dao;

public interface VendingAuditDao {

    public void writeAuditEntry(String entry) throws VendingPersistenceException;

}
