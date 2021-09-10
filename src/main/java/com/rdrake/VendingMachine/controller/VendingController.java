package com.rdrake.VendingMachine.controller;

import com.rdrake.VendingMachine.dao.InsufficientFundsException;
import com.rdrake.VendingMachine.dao.NoItemInventoryException;
import com.rdrake.VendingMachine.dao.VendingPersistenceException;
import com.rdrake.VendingMachine.dto.Item;
import com.rdrake.VendingMachine.service.VendingServiceLayer;
import com.rdrake.VendingMachine.ui.VendingView;

public class VendingController {
    private final VendingServiceLayer service;
    private final VendingView view;

    public VendingController(VendingServiceLayer service, VendingView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                displayItems();
                displayChange();
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        insertMoney();
                        break;
                    case 2:
                        purchaseItem();
                        break;
                    case 3:
                        returnMoney();
                        break;
                    case 4:
                        keepGoing = false;
                        returnMoney();
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingPersistenceException | NoItemInventoryException | InsufficientFundsException e){
            view.displayErrorMessage(e.getMessage());
        }

        exitMessage();
    }

    private void displayChange() throws VendingPersistenceException {
        view.displayMoney(service.getChange());
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayItems() {
        view.displayDisplayAllBanner();
        view.displayItemList(service.getItemList());
    }

    private void returnMoney() throws VendingPersistenceException {
        view.returnMoney(service.returnChange());
    }

    private void purchaseItem() throws VendingPersistenceException, NoItemInventoryException, InsufficientFundsException {
        view.displayDisplayItemBanner();
        String itemName = view.getItemChoice();
        Item item = service.getItem(itemName);
        view.displayPurchaseBanner(item);
    }

    private void insertMoney() throws VendingPersistenceException {
        service.insertChange(view.inputMoney());
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}

