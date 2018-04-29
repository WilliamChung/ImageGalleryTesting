package com.mindandmatters.william.imagegallerytesting.Utils;

/**
 * Created by lappy on 2018-04-28.
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace(".", " ");
    }

    public static String condenseUsername(String username){
        return username.replace( " ", ".");
    }
}
