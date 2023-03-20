import com.engeto.restaurant.*;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        //TODO napsat testovaci scenar

        DishList dList = new DishList();
        try {
            dList.readFromTxt(Settings.getFilename(),Settings.getDelimeter1(),Settings.getDelimeter2());
            dList.saveToTxt(Settings.getFilename(),Settings.getDelimeter1());
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        for (Dish dish : dList) {
            System.out.println(dish.getTitle());
            System.out.println(dish.getPrice());
        }

        Menu menu = new Menu();
        try {
            menu.addDish(dList.get(0));
            menu.addDish(dList.get(1));
            menu.addDish(dList.get(5));

        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }


        try {
            Waiter waiter1 = new Waiter(1,"Tom");
            Table table1 = new Table(1);
            Order order1 =new Order(table1,waiter1,menu.getDishFromMenu(1),4, LocalTime.of(8,45));
            Order order25 =new Order(table1,waiter1,menu.getDishFromMenu(2), 5, LocalTime.of(9,55));
            Order order3 =new Order(table1,waiter1,menu.getDishFromMenu(0), 20, LocalTime.now());
            System.out.println(order1);
            order1.setFulfilmentTime(LocalTime.of(15,45));
            System.out.println(order1);
            System.out.println(order25);

            OrderList orders = new OrderList();
            orders.addOrder(order1);
            orders.addOrder(order25);
            orders.addOrder(order3);

            System.out.println(orders.allOrdersNumber());
            System.out.println(orders.getAllOpenOrdersList());
            System.out.println(orders.allOrdersForTable(table1));
            System.out.println(orders.getDishSet());
            orders.getOrderList().forEach(item ->System.out.println(item));
            System.out.println(orders.averagePreparationTimeInTimeFrame(LocalTime.of(12,42),LocalTime.of(17,42)));
            System.out.println(orders.getOrdersPricePerWaiter());
            List<Order> newOrders = orders.getOrderList();

            Collections.sort(newOrders, new OrderOrderedTimeComparator());
            System.out.println(newOrders);

        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }



    }



}