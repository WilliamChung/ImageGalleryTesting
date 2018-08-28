package com.mindandmatters.william.imagegallerytesting.Utils;

import android.os.Environment;

/**
 * Created by lappy on 2018-07-26.
 */

public class FilePaths {

    //"storage/emulated/0"
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    //camera
    public String PICTURES = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR = "/DCIM/camera";

    public String FIREBASE_IMAGE_STORAGE = "photos/users/";
}
