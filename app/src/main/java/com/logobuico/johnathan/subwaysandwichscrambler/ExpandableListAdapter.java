package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.widget.ShareButton;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Copyied from the ExpandableListAdapter and modified
 * Created by Johnathan on 4/1/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    // Holds the Title
    private List<String> _listDataHeader;
    //added to store the rating info
    private HashMap<String, Float> _listRateHeader;
    //added to store the share button info
    private HashMap<String, ShareOpenGraphContent> _listShareHeader;
    // Holds to Sandwich data
    private HashMap<String, String> _listDataChild;
    //added to store the image data
    private HashMap<String, Bitmap> _listImageChild;


    public ExpandableListAdapter(Context context, List<String> listDataHeader,HashMap<String,Float> listRateHeader,
                                 HashMap<String, String> listChildData,HashMap<String, Bitmap> listImageChild, HashMap<String, ShareOpenGraphContent> listShareHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listRateHeader = listRateHeader;
        this._listDataChild = listChildData;
        this._listImageChild = listImageChild;
        this._listShareHeader = listShareHeader;
    }
    //added to get the rating
    public Object getRating(int groupPosition, int ratePosition){
        return this._listRateHeader.get(this._listDataHeader.get(groupPosition).toString());
    }

    //added to get the share info
    public Object getShareContent(int groupPosition, int sharePosition){
        return this._listShareHeader.get(this._listDataHeader.get(groupPosition));
    }

    //added to get the image data
    public Object getImage(int groupPosition, int imagePosition){
        return this._listImageChild.get(this._listDataHeader.get(groupPosition));
    }

    //grabs the sandwich data
        @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).toString();
    }

    //gets the sandwich id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //populates the child view of the list adapter with the image and the sandwich
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        //getting the sandwich
        final String childText = (String) getChild(groupPosition, childPosition);
        //getting the image
        final Bitmap image = (Bitmap) getImage(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }
        //getting the views
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageSub);
        //setting the views
        imageView.setImageBitmap(image);
        txtListChild.setText(childText);
        return convertView;
    }

    //modified to set to one because there is one one sandwich per save
    @Override
    public int getChildrenCount(int groupPosition) {
       // return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        return 1;
    }

    //gets the title
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    //gets the number of saves
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    //gets the title's ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //populates the headed part of the list view with the title, rating and share button
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        //getting the title
        String headerTitle = (String) getGroup(groupPosition);
        //getting the rating
        final Float ratingFloat = Float.parseFloat(getRating(groupPosition, groupPosition).toString());
        //getting the share info
        ShareOpenGraphContent content = (ShareOpenGraphContent) getShareContent(groupPosition,groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        //getting the views
        ShareButton shareButton = (ShareButton)convertView.findViewById(R.id.share);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
        LayerDrawable stars = (LayerDrawable)ratingBar.getProgressDrawable();
        //setting thew colour of the stars
        stars.getDrawable(1).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);

       TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

        //setting the views
        ratingBar.setRating(ratingFloat);
        shareButton.setShareContent(content);
        ratingBar.setEnabled(false);
        ratingBar.setFocusable(false);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}


