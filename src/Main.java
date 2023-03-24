//první projekt do Engeto Online Java Akademie
//author: Tomáš Mokrý
//email: tomas.mokry@gmail.com
//discord: Tomas M#0922

import com.engeto.restaurant.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        DishList dishesList = new DishList();
        OrderEvidence orders = new OrderEvidence();

        // Reading the dishesList ,menu, table notes and orders from txt files.
        try {
            dishesList.readFromTxt(Settings.getFilename(),Settings.getDelimeter1(),Settings.getDelimeter2());
            Menu.readFromTxt(Settings.getFilenameMenu(),Settings.getDelimeter1(),Settings.getDelimeter2());
            orders.readFromTxt(Settings.getFilenameOrders(),Settings.getDelimeter1(),Settings.getDelimeter2());
            TableNotes.readFromTxt(Settings.getFilenameNotes(),Settings.getDelimeter3());

        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // Create 2 waiters
        Waiter waiter1 = new Waiter(1,"Tom");
        Waiter waiter2 = new Waiter(2,"Lenka");

        // Create 3 tables
        Table table1 = new Table(1);
        Table table2 = new Table(2);
        Table table15 = new Table(15);

        // Create images for each food
        ArrayList<String> dishImages1 = new ArrayList<>();
        ArrayList<String> dishImages2 = new ArrayList<>();
        ArrayList<String> dishImages3 = new ArrayList<>();
        ArrayList<String> dishImages4 = new ArrayList<>();
        ArrayList<String> dishImages5 = new ArrayList<>();
        ArrayList<String> dishImages6 = new ArrayList<>();

        dishImages1.add("kureci_rizek_obalovany_1");
        dishImages1.add("kureci_rizek_obalovany_2");
        dishImages2.add("Hranolky_1");
        dishImages2.add("Hranolky_2");
        dishImages2.add("Hranolky_3");
        dishImages3.add("Pstruh_na_vine_1");
        dishImages3.add("Pstruh_na_vine_2");
        dishImages3.add("Pstruh_na_vine_3");
        dishImages4.add("Cesnecka_1");
        dishImages6.add("Gulas_1");
        dishImages6.add("Gulas_2");

        // Create 3 dishes and adding them into dishesList
        dishesList.add(new Dish("Kuřecí řízek obalovaný 150 g",
                BigDecimal.valueOf(156),20,dishImages1,Category.MAIN_DISH));
        dishesList.add(new Dish("Hranolky 150 g",
                BigDecimal.valueOf(50),15,dishImages2,Category.SIDE));
        dishesList.add(new Dish("Pstruh na víně 200 g",
                BigDecimal.valueOf(195),35,dishImages3,Category.MAIN_DISH));
        dishesList.add(new Dish("Česnečka",
                BigDecimal.valueOf(65),5,dishImages4,Category.SOUP));
        dishesList.add(new Dish("Jablečný závin 110 g",
                BigDecimal.valueOf(95),10,dishImages5,Category.DESSERT));
        dishesList.add(new Dish("Guláš 150 g",
                BigDecimal.valueOf(145),25,dishImages6,Category.MAIN_DISH ));

        // Create menu and Add first and third dish into menu
        try {
            Menu.addDish(dishesList.get(0));
            Menu.addDish(dishesList.get(2));
            Menu.addDish((dishesList.get(5)));
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // Create 3 orders for table 15 and 1 for table 2 and 2 orders for table 1
        try {
            orders.addOrder(new Order(table15, waiter1,
                    Menu.getDishFromMenu(0), 2, LocalTime.of(13,45)));
            orders.addOrder(new Order(table15, waiter1,
                    Menu.getDishFromMenu(1),2, LocalTime.of(13,55)));
            orders.addOrder(new Order(table15, waiter1,
                    Menu.getDishFromMenu(2),3, LocalTime.of(13,58)));
            orders.addOrder(new Order(table2, waiter2,
                    Menu.getDishFromMenu(1),5, LocalTime.of(14,30)));
            orders.addOrder(new Order(table1, waiter1,
                    Menu.getDishFromMenu(1),5, LocalTime.of(12,30)));
            orders.addOrder(new Order(table1, waiter1,
                    Menu.getDishFromMenu(1),5, LocalTime.of(11,45)));

            orders.addOrder(new Order(table2, waiter2, dishesList.get(1),5, LocalTime.of(14,30)));
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // Some orders are finished
        orders.getOrderList().get(0).setFulfilmentTime(LocalTime.of(14,5));
        orders.getOrderList().get(2).setFulfilmentTime(LocalTime.of(14,35));

        // Some tables has notes
        TableNotes.addNote(table15,"Great meal and " + waiter1.getName() + " was supper friendly");
        TableNotes.addNote(table2,"We love this restaurant, food great but " + waiter2.getName()+ " was mean.");

        // Print today's Menu
        System.out.println("---\n");
        System.out.println("Today's menu is: ");
        Menu.getMenuList().forEach(System.out::println);


        // How many orders all together
        System.out.println("---\n");
        System.out.println("Number of all orders:");
        System.out.println(orders.getAllOrdersNumber());

        // How many currently open orders?
        System.out.println("---\n");
        System.out.println("Number of currently open ( not finished ) orders:");
        System.out.println(orders.getAllOpenOrdersList().size());

        // List of currently open orders
        System.out.println("---\n");
        System.out.println("Currently open orders:");
        orders.getAllOpenOrdersList().forEach(System.out::println);

        // Sort orders by waiter
        System.out.println("---\n");
        System.out.println("Sort orders by waiter:");
        ArrayList<Order> ordersSortedByWaiter = orders.getOrderList();
        ordersSortedByWaiter.sort(new OrderWaiterNumberComparator());
        ordersSortedByWaiter.forEach(System.out::println);

        // Sort orders by ordered time
        System.out.println("---\n");
        System.out.println("Sort orders by ordered time:");
        ArrayList<Order> ordersSortedByOrderTime = orders.getOrderList();
        ordersSortedByOrderTime.sort(new OrderOrderedTimeComparator());
        ordersSortedByOrderTime.forEach(System.out::println);

        // Total price for all orders for each waiter
        System.out.println("---\n");
        System.out.println("Total price for all orders for each waiter:");
        Map<Waiter,BigDecimal> sumOrdersPricePerWaiter = orders.getSumOrdersPricePerWaiter();
        for (Map.Entry<Waiter,BigDecimal> entry : sumOrdersPricePerWaiter.entrySet()){
            Waiter key = entry.getKey();
            BigDecimal value = entry.getValue();
            System.out.println(key + " : " + value + ",- Kč");
        }

        // Number of orders for each waiter
        System.out.println("---\n");
        System.out.println("Number of orders for each waiter:");
        Map<Waiter,Integer> numberOfOrdersPerWaiter = orders.getNumberOfOrdersPerWaiter();
        for (Map.Entry<Waiter,Integer> entry : numberOfOrdersPerWaiter.entrySet()){
            Waiter key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " : " + value );
        }

        // Average process time for all orders in specific time frame
        System.out.println("---\n");
        System.out.println("Average process time for all orders in specific time frame ( 8:00 - 16:00 )");
        try {
            System.out.println(orders.getAveragePreparationTimeInTimeFrame(LocalTime.of(8,0),
                    LocalTime.of(16,0)));
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        // Dishes that were ordered during day (set)
        System.out.println("---\n");
        System.out.println("Dishes that were ordered during day:");
        orders.getDishSet().forEach(System.out::println);

        // Orders for specific table number
        System.out.println("---\n");
        System.out.println("Orders for specific table (example table 15):\n");
        System.out.println(orders.getAllOrdersForTable(table15));
        System.out.println("Notes for table "+table15.getTableNumber()+" :");
        System.out.println(TableNotes.getAllTablesNotes().get(table15));

        // Write all data to txt
        try {
            dishesList.saveToTxt(Settings.getFilename(),Settings.getDelimeter1());
            Menu.saveToTxt(Settings.getFilenameMenu(),Settings.getDelimeter1());
            orders.saveToTxt(Settings.getFilenameOrders(),Settings.getDelimeter1());
            TableNotes.saveToTxt(Settings.getFilenameNotes(),Settings.getDelimeter3());
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}