package com.engeto.restaurant;

public class Waiter {
    private int waiterNum;
    private  String name;

    public Waiter(int waiterNum, String name) {
        this.waiterNum = waiterNum;
        this.name = name;
    }

    public int getWaiterNum() {
        return waiterNum;
    }

    public void setWaiterNum(int waiterNum) {
        this.waiterNum = waiterNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
