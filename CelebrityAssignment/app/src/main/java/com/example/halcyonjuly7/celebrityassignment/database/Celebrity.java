package com.example.halcyonjuly7.celebrityassignment.database;

public class Celebrity  {
    static String COLUMN_NAME = "name";
    static String COLUMN_JOB_TITLE = "job_title";
    static String COLUMN_DESCRIPTION = "description";
    static String COLUMN_IMAGE = "image";

    String name;
    String job_title;
    String description;
    String image;

    public Celebrity(String name, String job_title, String description, String image) {
        this.name = name;
        this.job_title = job_title;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
