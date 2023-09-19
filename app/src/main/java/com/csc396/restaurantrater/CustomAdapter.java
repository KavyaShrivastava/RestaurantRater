package com.csc396.restaurantrater;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Review> reviewList;

    public CustomAdapter(Context context, ArrayList<Review> reviewList){
        this.context = context;
        this.reviewList = reviewList;

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return reviewList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return reviewList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_view_reviews_row, parent, false);
        }
        Review thisReview = reviewList.get(position);
        TextView restaurantName = convertView.findViewById(R.id.textView_restaurant_name);
        RatingBar isFavorite = convertView.findViewById(R.id.rating_bar_is_favorite_);
        RadioButton radioButton_breakfast = convertView.findViewById(R.id.radioButton_Breakfast);
        RadioButton radioButton_lunch = convertView.findViewById(R.id.radioButton_lunch);
        RadioButton radioButton_dinner = convertView.findViewById(R.id.radioButton_Dinner);
        ProgressBar progressBar_rating = convertView.findViewById(R.id.progressBar_rating);

        restaurantName.setText(thisReview.getRestaurantName());
        if(thisReview.isFavorite()){
            isFavorite.setProgress(100);
        }
        radioButton_breakfast.setChecked(thisReview.getMeal().equals("Breakfast"));
        radioButton_lunch.setChecked(thisReview.getMeal().equals("Lunch"));
        radioButton_dinner.setChecked(thisReview.getMeal().equals("Dinner"));
        progressBar_rating.setProgress(thisReview.getRating());
        return convertView;
    }
}
