package com.mealsharing.myapplication;

public class MealSwipes {
    private String name;
    private String locations;


    public MealSwipes(String name, String locations){
        this.name=name;
        this.locations=locations;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
