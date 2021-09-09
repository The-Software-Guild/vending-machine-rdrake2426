package com.rdrake.VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Item {

    private final String itemName;
    private BigDecimal itemCost;
    private int itemCount;

    public Item(String itemName, BigDecimal itemCost, int itemCount) {
        this.itemName = itemName;
        this.itemCost = itemCost.setScale(2, RoundingMode.HALF_UP);
        this.itemCount = itemCount;
    }

    public String getName() {
        return itemName;
    }

    public BigDecimal getCost() {
        return itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost.setScale(2, RoundingMode.HALF_UP);
    }

    public int getCount() {
        return itemCount;
    }

    public void setCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemCount == item.itemCount && Objects.equals(itemName, item.itemName) && Objects.equals(itemCost, item.itemCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemCost, itemCount);
    }
}
