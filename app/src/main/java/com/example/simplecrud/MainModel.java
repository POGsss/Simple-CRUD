package com.example.simplecrud;

public class MainModel {
    // Class Property
    String imageUrl, title, description;

    // No Arguments Constructor
    MainModel() {

    }

    // Class Constructor
    public MainModel(String imageUrl, String title, String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }

    // Getters
    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
