package com.example.halcyonjuly7.databasereceiverassignment.Database;

import java.io.Serializable;

public class Person implements Serializable {
    public static final String TABLE_NAME = "Person";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_WEIGHT = "weight";



    private String name;
    private int age;
    private int weight;
    private String gender;

    public Person(String name, int age, int weight, String gender) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }



}
