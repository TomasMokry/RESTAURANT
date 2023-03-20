import com.engeto.restaurant.Category;
import com.engeto.restaurant.Dish;
import com.engeto.restaurant.DishException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> imagesFood1 = new ArrayList<>();
        imagesFood1.add("A");
        imagesFood1.add("B");
        imagesFood1.add("C");

        Dish dish1 = new Dish("jidlo01",130,80, imagesFood1, Category.MAIN_DISH );
        System.out.println(dish1.getImages());
        try {
            dish1.removeImage(0);
            dish1.removeImage(0);
            System.out.println(dish1.getImages());
            dish1.removeImage(0);
            dish1.removeImage(0);
            System.out.println(dish1.getMainImage());
            dish1.addImage("V");
            System.out.println(dish1.getMainImage());
        } catch (DishException e) {
            System.err.println(e.getLocalizedMessage());;
        }


    }

}