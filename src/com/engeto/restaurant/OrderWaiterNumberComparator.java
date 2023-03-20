package com.engeto.restaurant;

import java.util.Comparator;

public class OrderWaiterNumberComparator implements Comparator<Order> {
    @Override
    public int compare(Order first, Order second){
        int firstNum = first.getWaiter().getWaiterNum();
        int secondNum = second.getWaiter().getWaiterNum();
        if (firstNum < secondNum) {
            return -1;
        } else if (firstNum > secondNum) {
            return 1;
        } else {
            return 0;
        }
    }
}
