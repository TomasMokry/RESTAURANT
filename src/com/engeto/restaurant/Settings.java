package com.engeto.restaurant;

public class Settings {
    private static final String FILENAME = "Dishes.txt";
    private static final String FILENAME_ORDERS = "Orders.txt";
    private static final String BLANK_IMAGE = "blank";

    private static final String DELIMETER_1 = ";";
    private static final String DELIMETER_2 = ",";

    public static String getFilename(){return FILENAME;}
    public static String getFilenameOrders(){return FILENAME_ORDERS;}
    public static String getDelimeter1(){return DELIMETER_1;}
    public static String getDelimeter2(){return DELIMETER_2;}
    public static String getBlankImage(){return BLANK_IMAGE;}
}
