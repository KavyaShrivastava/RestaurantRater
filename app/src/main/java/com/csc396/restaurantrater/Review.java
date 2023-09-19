package com.csc396.restaurantrater;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Review {

    private String restaurantName;
    private String date;
    private String time;
    private int rating;
    private boolean isFavorite;
    private String meal;
    private static final String SHARED_PREF_FILE = "com.csc396.restaurantrater.preferences";

    public Review(String line ){
        String[] reviewItem = line.split(",");
        Log.d("CSC396", Arrays.toString(reviewItem));
        restaurantName = reviewItem[0];
        date = reviewItem[1];
        time = reviewItem[2];
        meal = reviewItem[3];
        rating = Integer.valueOf(reviewItem[4]);
        if(Integer.valueOf(reviewItem[5])==1){
            isFavorite = true;
        }
        else{
            isFavorite = false;
        }

    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }


}
