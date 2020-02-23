package com.mealsharing.myapplication;

public class MealSwipes {
    private String userName;
    private String locations;
    private int requestCount;

    public String getNumberMeals() {
        return numberMeals;
    }

    public void setNumberMeals(String numberMeals) {
        this.numberMeals = numberMeals;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    private String numberMeals;
    private String userImg ;
    private String fromTime;
    private String toTime;
    private String notes;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    private String photoURL;

    public MealSwipes(){

    }

    public MealSwipes(String name, String photoURL, String locations, int requestCount, String numberMeals, String userImg, String notes,
                      int startHour, int startMinute, int endHour, int endMinute){
        this.userName=name;
        this.photoURL=photoURL;
        this.locations=locations;
        this.requestCount = requestCount;
        this.numberMeals=numberMeals;
        this.userImg=userImg;
        this.startHour=startHour;
        this.endHour=endHour;
        this.startMinute=startMinute;
        this.endMinute=endMinute;
        this.notes=notes;

    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
}
