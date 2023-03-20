import com.engeto.restaurant.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
            Menu.addDish(dList.get(0));
            Menu.addDish(dList.get(1));
            Menu.addDish(dList.get(5));

        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }


        try {
            Waiter waiter1 = new Waiter(1,"Tom");
            Table table1 = new Table(1);
            Order order1 =new Order(table1,waiter1,Menu.getDishFromMenu(1),4, LocalTime.now());
            Order order25 =new Order(table1,waiter1,Menu.getDishFromMenu(2), 5, LocalTime.now());
            System.out.println(order1);
            order1.setFulfilmentTime(LocalTime.of(15,45));
            System.out.println(order1);
            System.out.println(order25);
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }


    }



}