package com.engeto.restaurant;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TableNotes {
    private static Map<Table,String> allTablesNotes = new HashMap<>();

    public static Map<Table, String> getAllTablesNotes() {
        return allTablesNotes;
    }

    public static void setAllTablesNotes(Map<Table, String> allTablesNotes) {
        TableNotes.allTablesNotes = allTablesNotes;
    }

    public static void addNote(Table table, String note){
        allTablesNotes.put(table,note);
    }

    public static void readFromTxt(String filename, String delimeter3) throws DishException {

        int lineNumber = 0;
        String line = "";
        String[] items;
        String imagesAll = "";
        String[] images = new String[0];

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))){
            while(scanner.hasNextLine()){
                lineNumber++;
                line = scanner.nextLine();
                items = line.split(delimeter3);

                if (items.length != 2) throw new DishException("Wrong number of items in Menu on row: " + lineNumber);
                allTablesNotes.put(new Table(Integer.parseInt(items[0])),items[1]);
            }
        } catch (FileNotFoundException e) {
            throw new DishException("Can not find a file: "+ filename
                    +"\n"+e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new DishException(
                    "There is a wrong number format on line: " + lineNumber + "\nRow: " + line + "\n"
                            + e.getLocalizedMessage() + "\nPlease fix in the \"" + Settings.getFilenameNotes() + "\"");
        }
    }

    public static void saveToTxt(String filename, String delimeter ) throws DishException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Map.Entry<Table,String> entry : allTablesNotes.entrySet()){
                String key = String.valueOf(entry.getKey().getTableNumber());
                String value = entry.getValue();
                String record = key + delimeter + value;
                writer.println(record);
            }
        } catch (IOException e) {
            throw new DishException("There is problem with file writing " + e.getLocalizedMessage());
        }
    }
}
