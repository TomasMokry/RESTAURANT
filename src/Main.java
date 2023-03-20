import com.engeto.restaurant.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DishList dList = new DishList();
        try {
            dList.readFromTxt(Settings.getFilenameIn(),Settings.getDelimeter1(),Settings.getDelimeter2());
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());
        }

        dList.forEach(dish -> System.out.println(dish.getTitle()));
    }



}