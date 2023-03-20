package com.engeto.restaurant;

import java.util.ArrayList;

public class Dish {
    private String title;
    private int price;
    private int preparationTime;
    private ArrayList<String> images;
    private String mainImage;
    private Category category;

    public Dish(String title, int price, int preparationTime, ArrayList<String> images, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.images = images;
        this.mainImage = images.get(0);
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public void addImage(String newImage){
        this.images.add(newImage);
        setMainImage(this.images.get(0));
    }

    public void removeImage(int index) {
        if (this.images.size() == 1) {
            this.images.remove(index);
            setMainImage(Settings.getBlankImage());
        } else {
            this.images.remove(index);
            setMainImage(this.images.get(0));
        }
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
