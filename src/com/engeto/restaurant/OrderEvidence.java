package com.engeto.restaurant;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

public class OrderEvidence {
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

    public int getAllOrdersNumber(){
        return orderList.size();
    }

    public ArrayList<Order> getAllOpenOrdersList(){
        ArrayList<Order> openOrders = new ArrayList<>();
        for (Order order : this.orderList){
            if (order.getFulfilmentTime().equals(LocalTime.MIN)){
                openOrders.add(order);
            }
        }
        return openOrders;
    }

    public Map<Waiter, Integer> getSumOrdersPricePerWaiter(){
        Map<Waiter, Integer> waiterPricesMap = new HashMap<>();

        for (Order order : orderList){
            if (! waiterPricesMap.containsKey(order.getWaiter())){
                waiterPricesMap.put(order.getWaiter(), order.getDish().getPrice());
            } else {
                int subCount = waiterPricesMap.get(order.getWaiter());
                subCount += order.getDish().getPrice();
                waiterPricesMap.put(order.getWaiter(),subCount);
            }
        }
        return waiterPricesMap;
    }

    public Map<Waiter, Integer> getNumberOfOrdersPerWaiter(){
        Map<Waiter, Integer> waiterOrdersNumberMap = new HashMap<>();

        for (Order order : orderList){
            if (! waiterOrdersNumberMap.containsKey(order.getWaiter())){
                waiterOrdersNumberMap.put(order.getWaiter(), 1);
            } else {
                int count = waiterOrdersNumberMap.get(order.getWaiter());
                count ++;
                waiterOrdersNumberMap.put(order.getWaiter(), count);
            }
        }
        return waiterOrdersNumberMap;
    }

    public int getAveragePreparationTimeInTimeFrame(LocalTime from, LocalTime to) throws DishException {
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
            result = sumOfPreparationTime/dishNumber;
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


    public String getAllOrdersForTable(Table table){
        String tableNumberString;
        if (table.getTableNumber() > 0 && table.getTableNumber() < 10) {
            tableNumberString = " " + table.getTableNumber();
        } else {
            tableNumberString = "" + table.getTableNumber();
        }

        String text1 = "** Orders for table no. " + tableNumberString + " **";
        String text2 = "****";
        StringBuilder text3 = new StringBuilder();
        String text4 = "******";
        for (Order order : orderList){
            if (order.getTable().getTableNumber() == table.getTableNumber()){
                text3.append("\n").append(order);
            }
        }
        return text1 + "\n" + text2 + "\n" + text3 + "\n\n" + text4;
    }

    public void readFromTxt(String filename, String delimeter1, String delimeter2) throws DishException {

        int lineNumber = 0;
        String line = "";
        String[] items;
        String imagesAll;
        String[] images;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                lineNumber++;
                line = scanner.nextLine();
                items = line.split(delimeter1);

                if (items.length != 11) throw new DishException("Wrong number of items on row: " + lineNumber);

                imagesAll = items[6].substring(1,items[6].length()-1);
                images = imagesAll.split(delimeter2);
                ArrayList<String> imagesList = new ArrayList<>();
                for (String image : images) {
                    imagesList.add(image.trim());
                }

                Table table = new Table(Integer.parseInt(items[0]));
                Waiter waiter = new Waiter(Integer.parseInt(items[1]),items[2]);
                Dish dish = new Dish(items[3],
                        Integer.parseInt(items[4]),
                        Integer.parseInt(items[5]),
                        imagesList,Category.valueOf(items[7]));
                Order order = new Order(table,waiter,dish,Integer.parseInt(items[8]),LocalTime.parse(items[9]));

                if (!Objects.equals(items[10], "00:00")) {order.setFulfilmentTime(LocalTime.parse(items[10]));}
                orderList.add(order);


            }

        } catch (FileNotFoundException e) {
            throw new DishException("Can not find a file: "+ filename
                    +"\n"+e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new DishException(
                    "There is a wrong number format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameOrders() + "\"");
        } catch (IllegalArgumentException e) {
            throw new DishException(
                    "There is a wrong category format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameOrders() + "\"");
        }catch (DateTimeParseException e) {
            throw new DishException(
                    "There is a wrong time format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameOrders() + "\"");
        }
    }

    public void saveToTxt(String filename, String delimeter ) throws DishException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Order order : this.orderList){
                String record = order.getTable().getTableNumber() + delimeter
                        + order.getWaiter().getWaiterNum() + delimeter
                        + order.getWaiter().getName() + delimeter
                        + order.getDish().getTitle() + delimeter
                        + order.getDish().getPrice() + delimeter
                        + order.getDish().getPreparationTime() + delimeter
                        + order.getDish().getImages() + delimeter
                        + order.getDish().getCategory() + delimeter
                        + order.getDishNumber() + delimeter
                        + order.getOrderedTime() + delimeter
                        + order.getFulfilmentTime() + delimeter;
                writer.println(record);
            }
        } catch (IOException e) {
            throw new DishException("There is problem with file writing " + e.getLocalizedMessage());
        }
    }


}
