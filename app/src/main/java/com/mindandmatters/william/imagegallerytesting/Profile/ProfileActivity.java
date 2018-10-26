package com.mindandmatters.william.imagegallerytesting.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mindandmatters.william.imagegallerytesting.Models.Photo;
import com.mindandmatters.william.imagegallerytesting.R;
import com.mindandmatters.william.imagegallerytesting.Utils.BottomNavigationViewHelper;
import com.mindandmatters.william.imagegallerytesting.Utils.GridImageAdapter;
import com.mindandmatters.william.imagegallerytesting.Utils.UniversalImageLoader;
import com.mindandmatters.william.imagegallerytesting.Utils.ViewPostFragment;

import java.util.ArrayList;

/**
 * Created by lappy on 2018-03-21.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileFragment.OnGridImageSelectedListener{
    private static final String TAG = "ProfileActivity";

    @Override
    public void onGridImageSelected(Photo photo, int activityNumber) {
        Log.d(TAG, "onGridImageSelected: selected an image gridview: " + photo.toString());

        ViewPostFragment fragment = new ViewPostFragment();
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.photo), photo);
        args.putInt(getString(R.string.activity_number), activityNumber);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.view_post_fragment));
        transaction.commit();
    }


    private Context mContext = ProfileActivity.this;
    private ProgressBar mProgressBar;

    private ImageView profilePhoto;

    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLS = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        init();
    }

    private void init(){
        Log.d(TAG,"init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();
    }

}
