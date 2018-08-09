package com.example.halcyonjuly7.zooassignment.db;

public class Animal {
    static String TABLE_NAME = "Animals";
    static String COLUMN_ID = "id";
    static String COLUMN_CATEGORY = "category";
    static String COLUMN_NAME = "name";
    static String COLUMN_SCIENTIFIC_NAME = "scientific_name";
    static String COLUMN_DESCRIPTION = "description";
    static String COLUMN_IMAGE_URL = "image_url";


    String category;

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    String name;
    String scientific_name;
    String description;
    String image_url;
   
    
    
    public Animal(String category,
            String name,
            String scientific_name,
            String description,
            String image_url) {

        this.category = category;
        this.name = name;
        this.scientific_name = scientific_name;
        this.description = description;
        this.image_url = image_url;
        
    }
}
