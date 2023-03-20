package com.engeto.restaurant;

import java.util.Comparator;

public class OrderOrderedTimeComparator implements Comparator<Order> {
    @Override
    public int compare(Order first, Order second){
        return first.getOrderedTime().compareTo(second.getOrderedTime());
    }
}
