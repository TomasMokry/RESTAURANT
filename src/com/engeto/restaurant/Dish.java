package com.engeto.restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Dish {
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private ArrayList<String> images;
    private String mainImage;
    private Category category;

    public Dish(String title, BigDecimal price, int preparationTime, ArrayList<String> images, Category category) {
        this.title = title;
        this.price = price;
        this.preparationTime = preparationTime;
        this.setImages(images);
        this.setMainImageDefault(images);
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
        if (images.isEmpty()){
            this.images = new ArrayList<>();
            this.images.add(Settings.getBlankImage());
        } else {
            this.images = images;
        }
    }

    public void addImage(String newImage){
        this.images.add(newImage);
        setMainImage(this.images.get(0));
    }

    public void removeImage(int index) throws DishException {
        if (this.images.size() == 1) {
            this.images.remove(index);
            setMainImage(Settings.getBlankImage());
        } else if (this.images.size() < 1){
            throw new DishException("You can not remove all images from dish: " + getTitle());
        } else {
            this.images.remove(index);
            setMainImage(this.images.get(0));
        }
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImageDefault(ArrayList<String> image){
        if (images.isEmpty()){
            this.mainImage = Settings.getBlankImage();
        } else {
            this.mainImage = images.get(0);
        }
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

    @Override
    public String toString() {
        return  title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish dish)) return false;
        return getTitle().equals(dish.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
