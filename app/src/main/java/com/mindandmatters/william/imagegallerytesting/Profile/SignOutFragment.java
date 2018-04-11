package com.mindandmatters.william.imagegallerytesting.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mindandmatters.william.imagegallerytesting.R;

/**
 * Created by lappy on 2018-04-11.
 */

public class SignOutFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "EditProfileFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);

        return view;
    }
}
