package com.rdrake.VendingMachine.dao;


import com.rdrake.VendingMachine.dto.Change;
import com.rdrake.VendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingDaoFileImpl implements VendingDao {
    public static final String VENDING_ITEM_FILE = "VendingItemData.txt";
    public static final String DELIMITER = ",";
    private Map<String, Item> items = new HashMap<>();
    private Change change = new Change(new BigDecimal("0").setScale(2,RoundingMode.HALF_UP));

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String ItemTitle) {
        return items.get(ItemTitle);
    }

    @Override
    public String purchaseItem(String itemName) {
        return null;
    }

    @Override
    public BigDecimal getChange() {
        return change.getTotalChange();
    }

    @Override
    public void addChange(BigDecimal inputMoney) {
        change.setTotalChange(getChange().add(inputMoney));
    }

    @Override
    public BigDecimal removeChange() {
        BigDecimal removedChange = change.getTotalChange();
        change.setTotalChange(new BigDecimal("0"));
        return removedChange;
    }

    private Item unmarshallItem(String itemAsText) {
        String[] itemString = itemAsText.split(DELIMITER);

        return new Item(
                itemString[0],
                new BigDecimal(itemString[1]).setScale(2, RoundingMode.HALF_UP),
                Integer.parseInt(itemString[2]));
    }

    private void loadItems() throws VendingPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingPersistenceException("Could not load data from file into memory.");
        }
        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }

    private String marshallItem(Item item) {

        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getCost() + DELIMITER;
        itemAsText += item.getCount();

        return itemAsText;
    }

    private void writeItems() throws VendingPersistenceException {

        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(VENDING_ITEM_FILE));
        } catch (IOException e) {
            throw new VendingPersistenceException("Could not save Item data to file.", e);
        }

        String ItemAsText;
        List<Item> ItemList = this.getAllItems();
        for (Item currentItem : ItemList) {

            ItemAsText = marshallItem(currentItem);

            out.println(ItemAsText);

            out.flush();
        }
        out.close();
    }
}