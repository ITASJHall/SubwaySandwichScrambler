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
 * Created by Johnathan on 4/1/2015.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, String> _listDataChild;
    private HashMap<String, Bitmap> _listImageChild;
    private HashMap<String, Float> _listRateHeader;
    private HashMap<String, ShareOpenGraphContent> _listShareHeader;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,HashMap<String,Float> listRateHeader,
                                 HashMap<String, String> listChildData,HashMap<String, Bitmap> listImageChild, HashMap<String, ShareOpenGraphContent> listShareHeader) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listRateHeader = listRateHeader;
        this._listDataChild = listChildData;
        this._listImageChild = listImageChild;
        this._listShareHeader = listShareHeader;
    }
    public Object getRating(int groupPosition, int ratePosition){
        return this._listRateHeader.get(this._listDataHeader.get(groupPosition).toString());
    }

    public Object getShareContent(int groupPosition, int sharePosition){
        return this._listShareHeader.get(this._listDataHeader.get(groupPosition));
    }

    public Object getImage(int groupPosition, int imagePosition){
        return this._listImageChild.get(this._listDataHeader.get(groupPosition));
    }

        @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).toString();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        final Bitmap image = (Bitmap) getImage(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.imageSub);

        imageView.setImageBitmap(image);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
       // return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        final Float ratingFloat = Float.parseFloat(getRating(groupPosition, groupPosition).toString());
        ShareOpenGraphContent content = (ShareOpenGraphContent) getShareContent(groupPosition,groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        ShareButton shareButton = (ShareButton)convertView.findViewById(R.id.share);

        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
        LayerDrawable stars = (LayerDrawable)ratingBar.getProgressDrawable();
        stars.getDrawable(1).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0).setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);

       TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);

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
