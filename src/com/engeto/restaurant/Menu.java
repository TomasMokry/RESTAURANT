package com.engeto.restaurant;

import java.util.HashSet;
import java.util.Set;

public class Menu {
    private static Set<Dish> menu = new HashSet<>();

    public static Set<Dish> getMenu() {
        return menu;
    }

    public static void setMenu(Set<Dish> menu) {
        Menu.menu = menu;
    }

    public static void clearAll(){
        menu.clear();
    }

    public static void addDish(Dish newDish){
        menu.add(newDish);
    }

    @Override
    public String toString() {
        return "Today's Menu: " + this.menu;
    }
}
