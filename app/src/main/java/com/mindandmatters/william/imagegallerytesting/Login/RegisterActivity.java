package com.mindandmatters.william.imagegallerytesting.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mindandmatters.william.imagegallerytesting.R;

/**
 * Created by lappy on 2018-04-19.
 */

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started. ");
    }




}
