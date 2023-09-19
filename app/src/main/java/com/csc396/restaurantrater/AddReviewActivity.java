package com.csc396.restaurantrater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.csc396.restaurantrater.databinding.ActivityAddReviewBinding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class AddReviewActivity extends AppCompatActivity {

    private ActivityAddReviewBinding binding;
    private String Date;
    private String Time;
    private String restaurantName;
    private String rating;
    private String isFavorite;
    private String meal;
    public static final String EXTRA_DATE = "com.csc396.restaurantrater.EXTRA_DATE";
    public static final String EXTRA_TIME = "com.csc396.restaurantrater.EXTRA_TIME";
    public static final String EXTRA_RESTAURANT_NAME = "com.csc396.restaurantrater.EXTRA_RESTAURANT_NAME";
    public static final String EXTRA_RATING = "com.csc396.restaurantrater.EXTRA_RATING";
    public static final String EXTRA_IS_FAVORITE = "com.csc396.restaurantrater.EXTRA_IS_FAVORITE";
    public static final String EXTRA_MEAL = "com.csc396.restaurantrater.EXTRA_MEAL";


    private View.OnClickListener editText_review_date_clickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           DatePickerDialog dateDialog = new DatePickerDialog(AddReviewActivity.this, datepicker_dateSetListener, LocalDate.now().getYear(),
                   LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
           dateDialog.show();
       }
   };

    private DatePickerDialog.OnDateSetListener datepicker_dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Date = month+"/"+ dayOfMonth+"/"+year;
            binding.edittextReviewDate.setText(Date);

        }
    };

    private View.OnClickListener editText_review_time_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TimePickerDialog timeDialog = new TimePickerDialog(AddReviewActivity.this, timepicker_timeSetListener, LocalTime.now().getHour(), LocalTime.now().getMinute(), false);
            timeDialog.show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timepicker_timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if(hourOfDay==0 && minute == 0)
            {
                Time = String.valueOf(12)+":"+String.valueOf(minute)+"0 AM";
            }
            else if(hourOfDay == 0 && minute>0 && minute<10){
                Time = String.valueOf(12) + ":0" + String.valueOf(minute) + " AM";
            }
            else if(hourOfDay == 0 && minute>=10){
                Time = String.valueOf(12) + ":" + String.valueOf(minute) + " AM";
            }
            else if(hourOfDay <12 && hourOfDay> 0 && minute<10){
                Time = String.valueOf(hourOfDay) + ":0" + minute + " AM";
            }
            else if(hourOfDay<12 & hourOfDay> 0 && minute>=10)
            {
                Time = String.valueOf(hourOfDay)+":"+String.valueOf(minute)+" AM";
            }
            else if(hourOfDay == 12 && minute<10){
                Time = hourOfDay + ":0" + minute + " PM";
            }
            else if(hourOfDay == 12 && minute>=10){
                Time = hourOfDay + ":" + minute + " PM";
            }
            else if(hourOfDay>12 && minute<10)
            {
                Time = String.valueOf((hourOfDay-12))+":0"+String.valueOf(minute)+" PM";
            }
            else{
                Time = String.valueOf((hourOfDay-12))+":"+String.valueOf(minute)+" PM";
            }
            binding.edittextReviewTime.setText(Time);
        }
    };

    private View.OnClickListener button_add_review_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(binding.radiogroupMeals.getCheckedRadioButtonId())
            {
                case R.id.radio_breakfast:
                    meal = binding.radioBreakfast.getText().toString();
                    break;
                case R.id.radio_lunch:
                    meal = binding.radioLunch.getText().toString();
                    break;
                case R.id.radio_dinner:
                    meal = binding.radioDinner.getText().toString();
                    break;
            }
            if(binding.checkboxFavorite.isChecked())
            {
                isFavorite = "1";
            }
            else
            {
                isFavorite= "0";
            }
            restaurantName=binding.edittextRestaurantName.getText().toString();
            rating = String.valueOf(binding.seekbarRating.getProgress());
            String newReviewCsvEntry = restaurantName+","+Date+
                    ","+Time+","+meal+","+rating+","+isFavorite;
            File reviewsFile = new File(getFilesDir(), "reviews.csv");
            try (FileWriter fw = new FileWriter(reviewsFile, true))
            {
                fw.write(newReviewCsvEntry + System.lineSeparator()) ;
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);

            finish();


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonAddReview.setOnClickListener(button_add_review_clickListener);
        binding.edittextReviewDate.setOnClickListener(editText_review_date_clickListener);
        binding.edittextReviewTime.setOnClickListener(editText_review_time_clickListener);
        Intent myIntent = getIntent();

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */

}