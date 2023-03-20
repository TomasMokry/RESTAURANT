package com.engeto.restaurant;

public class Settings {
    private static final String FILENAME_IN = "Dishes.txt";
    private static final String FILENAME_OUT = "Dishes_out.txt";
    private static final String BLANK_IMAGE = "blank";

    private static final String DELIMETER_1 = ";";
    private static final String DELIMETER_2 = ",";

    public static String getFilenameIn(){return FILENAME_IN;}
    public static String getFilenameOut(){return FILENAME_OUT;}
    public static String getDelimeter1(){return DELIMETER_1;}
    public static String getDelimeter2(){return DELIMETER_2;}
    public static String getBlankImage(){return BLANK_IMAGE;}
}
