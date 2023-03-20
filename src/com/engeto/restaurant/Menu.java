package com.engeto.restaurant;

import java.util.ArrayList;
import java.util.Set;

public class Menu {
    private static ArrayList<Dish> menuList = new ArrayList<>();

    public static ArrayList<Dish> getMenuList() {
        return menuList;
    }

    public static void setMenuList(ArrayList<Dish> menuList) {
        Menu.menuList = menuList;
    }

    public static void clearAll(){
        menuList.clear();
    }

    public static void addDish(Dish newDish)throws DishException {
        if (menuList.contains(newDish)) throw new DishException("This Dish "+newDish+ " is already in menu");
        menuList.add(newDish);
    }

    public static Dish getDishFromMenu(int index)throws DishException{
        if (index > menuList.size()) throw new DishException("You entered wrong index. Menu has just " + menuList.size());
        return menuList.get(index);
    }


    @Override
    public String toString() {
        return "Today's Menu: " + this.menuList;
    }
}
