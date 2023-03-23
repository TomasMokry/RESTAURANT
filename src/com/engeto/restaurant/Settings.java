package com.engeto.restaurant;

public class Settings {
    private static final String FILENAME = "Dishes.txt"; // "Dishes_not_correct.txt" // "Dishes.txt"
    private static final String FILENAME_ORDERS = "Orders.txt"; // "Orders_not_correct.txt" // "Orders.txt"
    private static final String FILENAME_MENU = "Menu.txt"; // "Menu_not_correct.txt" // "Menu.txt"
    private static final String FILENAME_NOTES = "Notes.txt";
    private static final String BLANK_IMAGE = "blank";

    private static final String DELIMETER_1 = ";";
    private static final String DELIMETER_2 = ",";
    private static final String DELIMETER_3 = ":";

    public static String getFilename(){return FILENAME;}
    public static String getFilenameOrders(){return FILENAME_ORDERS;}
    public static String getFilenameMenu(){return FILENAME_MENU;}
    public static String getFilenameNotes(){return FILENAME_NOTES;}
    public static String getDelimeter1(){return DELIMETER_1;}
    public static String getDelimeter2(){return DELIMETER_2;}
    public static String getDelimeter3(){return DELIMETER_3;}
    public static String getBlankImage(){return BLANK_IMAGE;}
}
