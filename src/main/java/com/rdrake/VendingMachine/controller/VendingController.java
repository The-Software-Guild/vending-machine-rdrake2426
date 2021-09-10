package com.rdrake.VendingMachine.controller;

import com.rdrake.VendingMachine.dao.VendingPersistenceException;
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
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch (VendingPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
            returnMoney();
        }
        exitMessage();
    }

    private void displayChange() {
        view.displayMoney(service.getChange());
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayItems() {
        view.displayDisplayAllBanner();
        view.displayItemList(service.getItemList());
    }

    private void returnMoney() {
        view.returnMoney(service.returnChange());
    }

    private void purchaseItem() throws VendingPersistenceException{
        view.displayDisplayItemBanner();
        String item = view.getItemChoice();
        item = service.getItem(item);
        view.displayPurchaseBanner(item);
    }

    private void insertMoney() {
        service.insertChange(view.inputMoney());
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}

