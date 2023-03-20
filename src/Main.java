import com.engeto.restaurant.*;

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
        Menu.addDish(dList.get(0));
        Menu.addDish(dList.get(1));
        Menu.addDish(dList.get(5));
        Menu.clearAll();

    }



}