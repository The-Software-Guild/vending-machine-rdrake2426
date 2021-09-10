package com.rdrake.VendingMachine.dao;

public class NoItemInventoryException extends Exception{

    public NoItemInventoryException(String message) {
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
