package com.engeto.restaurant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Order{
    //TODO set the fulfillmentTime to LocalTime.MIN
    private static int nextId = 1;
    private int id;

    private Table table;
    private Waiter waiter;
    private Dish dish;
    int dishNumber;
    private LocalTime orderedTime;
    private LocalTime fulfillmentTime;

    public Order(Table table, Waiter waiter, Dish dish, int dishNumber, LocalTime orderedTime) throws DishException {

        this.id = nextId++;
        this.table = table;
        this.waiter = waiter;
        this.setDish(dish);
        this.dishNumber = dishNumber;
        this.orderedTime = orderedTime;

    }

    public Table getTable(){
        return table;
    }

    public void setTable(Table newTable){
        table = newTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public Dish getDish() {
        return dish;
    }

    public int getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public LocalTime getOrderedTime() {
        return orderedTime;
    }

    public void setOrderedTime(LocalTime orderedTime) {
        this.orderedTime = orderedTime;
    }

    public LocalTime getFulfilmentTime() {
        return fulfillmentTime;
    }

    public void setFulfilmentTime(LocalTime fulfilmentTime) {
        this.fulfillmentTime = fulfilmentTime;
    }

    public void setDish(Dish dish) throws DishException{
        if (! Menu.getMenuList().contains(dish)){
            throw new DishException("This dish" +dish.getTitle()+ "is not in the Menu " + Menu.getMenuList());
        } else {this.dish = dish;}
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String fulfillmentTimeString;
        if (this.fulfillmentTime == null)fulfillmentTimeString = "in process";
        else fulfillmentTimeString = this.fulfillmentTime.format(formatter);
        return id
                + ". "
                + dish.getTitle()
                + " " + dishNumber + "x"
                + " (" + dish.getPrice()
                + "Kč):\t"
                + orderedTime.format(formatter)
                + "-"
                + fulfillmentTimeString
                + " číšník č. "
                + waiter.getWaiterNum();
    }

}
