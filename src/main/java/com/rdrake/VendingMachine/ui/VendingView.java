package com.rdrake.VendingMachine.ui;

import com.rdrake.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingView {

    private final UserIO io;

    public VendingView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Vending Menu");
        io.print("1. Insert money");
        io.print("2. Purchase Item");
        io.print("3. Take out change");
        io.print("4. Exit");

        return io.readInt("Please select from the above choices.", 1, 4);
    }

    public BigDecimal inputMoney(){
        while (true){
            BigDecimal userChoice = new BigDecimal(io.readString("Please enter amount of money to input")).setScale(2, RoundingMode.HALF_UP);
            io.print("Amount Entered: £" +userChoice);
            if (io.readString("Type yes to confirm").equalsIgnoreCase("Yes")){
                return userChoice;
            }
            else{
                io.print("Returning to Menu");
                return new BigDecimal("0");
            }
        }
    }

    public void displayItemList(List<Item> itemList) {
        itemList.forEach(item -> io.print(
                item.getName() + ": " + "£" + item.getCost()));
    }

    public void displayItem(Item item) {
        if (item != null) {
            io.print("Item: " + item.getName());
            io.print("Cost: " + item.getCost());
            io.print("Available: " + item.getCount());

        } else {
            io.print("No such item.");
        }
    }

    public String getItemChoice() {
        return io.readString("Please enter Item name.");
    }

    public void displayPurchaseBanner(String item) {
        if (item != null) {
            io.print("Successfully purchased: "+item);

        } else {
            io.print("Item purchase failed.");
        }
    }

    public void displayMoney(BigDecimal money){
        io.print("Current change: £"+money);
    }

    public void displayDisplayAllBanner() {
        io.print("=== Items in Vending Machine ===");
    }

    public void displayDisplayItemBanner() {
        io.print("=== Item ===");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Input");
    }

    public void displayErrorMessage(String message) {
        io.readString("Application encountered Error: " + message + "  Application will close. Please Press Enter");
    }

    public void displayExitBanner() {
        io.print("Good Bye");
    }

    public void returnMoney(BigDecimal returnChange) {

    }
}