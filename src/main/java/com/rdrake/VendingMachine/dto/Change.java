package com.rdrake.VendingMachine.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Change {

    private BigDecimal totalChange;

    public Change(BigDecimal change){
        totalChange = change;
    }

    public static Map<String, Integer> splitChange(BigDecimal change){
        Map<String, Integer> splitChange = new HashMap<>();

            //incomplete

        return splitChange;
    }

    public BigDecimal getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(BigDecimal totalChange) {
        this.totalChange = totalChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Change change = (Change) o;
        return Objects.equals(totalChange, change.totalChange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalChange);
    }
}
