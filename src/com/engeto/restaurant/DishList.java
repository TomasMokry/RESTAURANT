package com.engeto.restaurant;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DishList extends ArrayList<Dish> {
    public void readFromTxt(String filename, String delimeter1, String delimeter2) throws DishException {
        int lineNumber = 0;
        String line = "";
        String[] items = new String[0];
        String imagesAll = "";
        String[] images = new String[0];

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                lineNumber++;
                line = scanner.nextLine();
                items = line.split(delimeter1);

                if (items.length != 5) throw new DishException("Wrong number of items on row: " + lineNumber);

                imagesAll = items[3].substring(1,items[3].length()-1);
                images = imagesAll.split(delimeter2);
                ArrayList<String> imagesList = new ArrayList<>();
                for (int i = 0; i<images.length;i++){imagesList.add(images[i].trim());}


                Dish dish = new Dish(items[0],
                        Integer.parseInt(items[1]),
                        Integer.parseInt(items[2]),
                        imagesList,Category.valueOf(items[4]));
                add(dish);
            }

        } catch (FileNotFoundException e) {
            throw new DishException("Can not find a file: "+ filename
                    +"\n"+e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new DishException(
                    "There is a wrong number format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameIn() + "\"");
        } catch (IllegalArgumentException e) {
            throw new DishException(
                    "There is a wrong category format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameIn() + "\"");
        }
    }

    public void saveToTxt(String filename, String delimeter ) throws DishException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Dish dish : this){
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

}
