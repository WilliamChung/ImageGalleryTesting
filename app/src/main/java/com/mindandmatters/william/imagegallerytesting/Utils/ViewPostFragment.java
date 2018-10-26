package com.mindandmatters.william.imagegallerytesting.Utils;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mindandmatters.william.imagegallerytesting.Models.Photo;
import com.mindandmatters.william.imagegallerytesting.R;

/**
 * Created by lappy on 2018-10-25.
 */

public class ViewPostFragment extends Fragment {

    private static final String TAG = "ViewPostFragment";

    public ViewPostFragment(){
        super();
        setArguments(new Bundle());
    }


    //vars
    private Photo mPhoto;
    private Integer mActivityNumber = 0;

    //widgets
    private SquareImageView mPostImage;
    private BottomNavigationViewEx bottomNavigationView;
    private TextView mBackLabel, mCaption, mUsername, mTimestamp;
    private ImageView mBackArrow, mEllipses, mHeartRed, mHeartWhite, mProfileImage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        mPostImage = (SquareImageView) view.findViewById(R.id.post_image);
        bottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottomNavViewBar);
        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);
        mBackLabel = (TextView) view.findViewById(R.id.tvBackLabel);
        mCaption = (TextView) view.findViewById(R.id.image_caption);
        mUsername = (TextView) view.findViewById(R.id.username);
        mTimestamp = (TextView) view.findViewById(R.id.image_time_posted);
        mEllipses = (ImageView) view.findViewById(R.id.ivEllipses);
        mHeartRed = (ImageView) view.findViewById(R.id.image_heart_red);
        mHeartWhite = (ImageView) view.findViewById(R.id.image_heart);
        mProfileImage = (ImageView) view.findViewById(R.id.profile_photo);

        try{
            mPhoto = getPhotoFromBundle();
            UniversalImageLoader.setImage(mPhoto.getImage_path(), mPostImage, null, "");
            mActivityNumber = getActivityNumberFromBundle();
        }
        catch (NullPointerException e){
            Log.e(TAG, "onCreateView: NullPointerException: " + e.getMessage() );
        }

        setupBottomNavigationView();

        return  view;
    }

    //retrieve photo from incoming bundle from ProfileActivity interface
    private Photo getPhotoFromBundle(){
        Log.d(TAG, "getPhotoFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getParcelable(getString(R.string.photo));
        }
        else {
            return null;
        }
    }

    //retrieve activity number from incoming bundle from ProfileActivity interface
    private int getActivityNumberFromBundle(){
        Log.d(TAG, "getActivityNumberFromBundle: arguments: " + getArguments());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            return bundle.getInt(getString(R.string.activity_number));
        }
        else {
            return 0;
        }
    }

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up bottom navigation view");
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(getActivity(), getActivity(), bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(mActivityNumber);
        menuItem.setChecked(true);
    }
}
