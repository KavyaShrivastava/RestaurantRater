package com.csc396.restaurantrater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.csc396.restaurantrater.databinding.ActivityViewReviewsBinding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ViewReviewsActivity extends AppCompatActivity {

    private ActivityViewReviewsBinding binding;
    private ArrayList<Review> reviewsListView = new ArrayList<>();
    public static final int FROM_ADD_REVIEW_ACTIVITY = 1;


    private AdapterView.OnItemClickListener listView_clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(ViewReviewsActivity.this);
            myBuilder.setTitle("Review Details")
                        .setMessage("This review was created on " + reviewsListView.get(position).getDate() + " at " +  reviewsListView.get(position).getTime());
            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        }
    };

    private View.OnClickListener button_add_review_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(ViewReviewsActivity.this, AddReviewActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(myIntent, FROM_ADD_REVIEW_ACTIVITY);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FROM_ADD_REVIEW_ACTIVITY && resultCode == Activity.RESULT_OK){
            //readReviewsList();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        readReviewsList();
        CustomAdapter reviewAdapter = new CustomAdapter(this, reviewsListView);
        binding.listviewReviews.setAdapter(reviewAdapter);
        binding.listviewReviews.setOnItemClickListener(listView_clickListener);
        binding.buttonAddReview.setOnClickListener(button_add_review_clickListener);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        readReviewsList();

    }


    private void readReviewsList(){
        File myFile = new File(this.getFilesDir(), "reviews.csv");
        try(Scanner sc = new Scanner(myFile))
        {
            while(sc.hasNextLine())
            {
                Review newReview = new Review(sc.nextLine());
                reviewsListView.add(newReview);

            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

    }





}