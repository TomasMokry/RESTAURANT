package com.engeto.restaurant;

import java.time.LocalTime;
import java.util.*;

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

    public int allOrdersNumber(){
        return orderList.size();
    }

    public ArrayList<Order> getAllOpenOrdersList(){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (Order order : this.orderList){
            if (order.getFulfilmentTime() == null){
                openOrders.add(order);
            }
        }
        return openOrders;
    }
    //TODO Možnost seřadit objednávky podle číšníka nebo času zadání.
    //TODO ukladani a nacitani z txt
    //TODO dobrovolne
    //Možnost přidávat či odebírat kategorie jídel.
    //Jídlo může být ve více kategoriích zároveň.
    //Objednávku pro stůl lze uzavřít (close) až v okamžiku, kdy jsou všechny položky (item) zaplaceny.
    //Objednávky od jednoho stolu by měly jít převést k jinému stolu,
    // když si hosté přesednou. Dokonce si mohou přisednout k hostům u jiného stolu.
    // (Dále už nemusí jít v systému rozlišit, které objednávky jsou převedeny, přesun tedy nemusí být vratná operace.)

    public String getOrdersPricePerWaiter(){
        Map<Waiter, Integer> waiterPricesMap = new HashMap<>();
        Map<Waiter, Integer> waiterOrdersNumberMap = new HashMap<>();

        for (Order order : orderList){
            if (! waiterPricesMap.containsKey(order.getWaiter())){
                waiterPricesMap.put(order.getWaiter(), order.getDish().getPrice());
                waiterOrdersNumberMap.put(order.getWaiter(), 1);
            } else {
                int subCount = waiterPricesMap.get(order.getWaiter());
                subCount += order.getDish().getPrice();
                waiterPricesMap.put(order.getWaiter(),subCount);

                int count = waiterOrdersNumberMap.get(order.getWaiter());
                count ++;
                waiterOrdersNumberMap.put(order.getWaiter(), count);
            }
        }
        String text1 = "Waiter : sum of all prices of his orders";
        String text2 = "****";
        String text3="";
        String text4="";
        String text5="Waiter : number of his orders";
        String result;

        for (Map.Entry<Waiter,Integer> entry : waiterPricesMap.entrySet()){
            Waiter key = entry.getKey();
            Integer value = entry.getValue();
            text3 += key + " : " + value+"\n";
        }
        for (Map.Entry<Waiter,Integer> entry : waiterOrdersNumberMap.entrySet()){
            Waiter key = entry.getKey();
            Integer value = entry.getValue();
            text4 += key + " : " + value+"\n";
        }
        result = text2+"\n"
                +text1+"\n"
                +text2+"\n"
                +text3+"\n"
                +text2+"\n"
                +text5+"\n"
                +text2+"\n"
                +text4+"\n";
        return result;
    }

    public int averagePreparationTimeInTimeFrame(LocalTime from, LocalTime to) throws DishException {
        int sumOfPreparationTime = 0;
        int dishNumber = 0;
        int result;
        for (Order order : orderList){
            if (order.getOrderedTime().isAfter(from) && order.getOrderedTime().isBefore(to)){
                sumOfPreparationTime += order.getDish().getPreparationTime();
                dishNumber++;
            }
        }
        try {
            result = (int)(sumOfPreparationTime/dishNumber);
        } catch (ArithmeticException e) {
            throw new DishException("There are no ordered dishes in this time frame "
                    + from + " - " + to + " " + e.getLocalizedMessage());
        }
        return result;
    }


    public Set<Dish> getDishSet(){
        Set<Dish> dishSet = new HashSet<>();
        for (Order order : orderList){
            dishSet.add(order.getDish());
        }
        return dishSet;
    }


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
