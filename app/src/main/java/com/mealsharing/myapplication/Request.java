package com.mealsharing.myapplication;

public class Request {
    private String requestID;
    private String mealPostID;
    private String numberOfMeals;
    private String notes;
    private int hour;
    private int minute;
    private String userNamefrom;
    private String photoURL;
    private String status="pending";
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Request() {
    }

    public void setMealPostID(String mealPostID) {
        this.mealPostID = mealPostID;
    }

    public String getMealPostID() {
        return mealPostID;
    }

    public Request(String requestID, String mealPostID, String numberOfMeals, String notes, int hour, int minute, String userNamefrom, String photoURL, String status) {
        this.requestID = requestID;
        this.mealPostID = mealPostID;
        this.numberOfMeals = numberOfMeals;

        this.notes = notes;
        this.hour = hour;
        this.minute = minute;
        this.userNamefrom = userNamefrom;
        this.photoURL = photoURL;
        this.status = status;
    }

    public Request(String requestID, String numberOfMeals, String notes, int hour, int minute, String userNamefrom, String photoURL, String status) {
        this.requestID = requestID;
        this.numberOfMeals = numberOfMeals;
        this.notes = notes;
        this.hour = hour;
        this.minute = minute;
        this.userNamefrom = userNamefrom;
        this.photoURL = photoURL;
        this.status = status;
    }



    public String getRequestID() {
        return requestID;
    }

    public String getNumberOfMeals() {
        return numberOfMeals;
    }

    public String getNotes() {
        return notes;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getUserNamefrom() {
        return userNamefrom;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getStatus() {
        return status;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public void setNumberOfMeals(String numberOfMeals) {
        this.numberOfMeals = numberOfMeals;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setUserNamefrom(String userNamefrom) {
        this.userNamefrom = userNamefrom;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
