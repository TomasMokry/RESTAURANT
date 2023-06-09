package com.engeto.restaurant;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

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
        if (menuList.contains(newDish)) throw new DishException("This Dish " + newDish + " is already in menu");
        menuList.add(newDish);
    }

    public static Dish getDishFromMenu(int index)throws DishException{
        if (index > menuList.size()) throw new DishException("You entered wrong index. Menu has just " + menuList.size());
        return menuList.get(index);
    }

    public static void readFromTxt(String filename, String delimeter1, String delimeter2) throws DishException {

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

                if (items.length != 5) throw new DishException("Wrong number of items on row: "
                        + lineNumber + "Please check the file: " + Settings.getFilenameMenu());

                imagesAll = items[3].substring(1,items[3].length()-1);
                images = imagesAll.split(delimeter2);
                ArrayList<String> imagesList = new ArrayList<>();
                for (String image : images) {
                    imagesList.add(image.trim());
                }


                Dish dish = new Dish(items[0],
                        new BigDecimal(items[1]),
                        Integer.parseInt(items[2]),
                        imagesList,Category.valueOf(items[4]));
                menuList.add(dish);
            }

        } catch (FileNotFoundException e) {
            throw new DishException("Can not find a file: "+ filename
                    +"\n"+e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new DishException(
                    "There is a wrong number format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameMenu() + "\"");
        } catch (IllegalArgumentException e) {
            throw new DishException(
                    "There is a wrong category format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameMenu() + "\"");
        }
    }

    public static void saveToTxt(String filename, String delimeter ) throws DishException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Dish dish : menuList){
                String record = dish.getTitle() + delimeter
                        + dish.getPrice() + delimeter
                        + dish.getPreparationTime() + delimeter
                        + dish.getImages() + delimeter
                        + dish.getCategory();
                writer.println(record);
            }
        } catch (IOException e) {
            throw new DishException("There is problem with file writing " + e.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return "Today's Menu: " + menuList;
    }
}
