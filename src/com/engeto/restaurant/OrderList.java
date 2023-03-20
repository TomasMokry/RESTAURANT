package com.engeto.restaurant;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orderList = new ArrayList<>();

    public ArrayList<Order> getOrderList() {
        return new ArrayList<>(orderList);
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public void addOrder(Order newOrder){
        this.orderList.add(newOrder);
    }
    //Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    public int allOrdersNumber(){
        return orderList.size();
    }
    //Seznam aktuálně rozpracovaných objednávek.
    public ArrayList<Order> getAllOpenOrdersList(){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (Order order : this.orderList){
            if (order.getFulfilmentTime() == null){
                openOrders.add(order);
            }
        }
        return openOrders;
    }
    //Možnost seřadit objednávky podle číšníka nebo času zadání.
    //Celkovou cenu objednávek pro jednotlivé číšníky (u každého číšníka bude počet jeho zadaných objednávek).
    //Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
    //Seznam jídel, které byly dnes objednány. Bez ohledu na to, kolikrát byly objednány.
    //Seznam objednávek pro jeden stůl ve formátu:
    public String allOrdersForTable(Table table){
        String tableNumberString;
        if (table.getTableNumber() > 0 && table.getTableNumber() < 10) {
            tableNumberString = " " + table.getTableNumber();
        } else {
            tableNumberString = "" + table.getTableNumber();
        }

        String text1 = "** Orders for table no. " + tableNumberString + " **";
        String text2 = "****";
        String text3 = "";
        String text4 = "******";
        for (Order order : orderList){
            if (order.getTable().getTableNumber() == table.getTableNumber()){
                text3 += "\n"+order.toString();
            }
        }
        return text1 + "\n" + text2 + "\n" + text3 + "\n\n" + text4;
    }


}
