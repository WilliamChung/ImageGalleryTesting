package com.mindandmatters.william.imagegallerytesting.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mindandmatters.william.imagegallerytesting.Models.User;
import com.mindandmatters.william.imagegallerytesting.Models.UserAccountSettings;
import com.mindandmatters.william.imagegallerytesting.Models.UserSettings;
import com.mindandmatters.william.imagegallerytesting.R;

/**
 * Created by lappy on 2018-04-25.
 */

public class FirebaseMethods {

    private static final String TAG = "Firebase Methods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mContext = context;

        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    //register a new email and password to firebase
    public void registerNewEmail(final String email, String password, final String username){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            sendVerificationEmail();

                            userID = mAuth.getCurrentUser().getUid();
                            Log.w(TAG, "createUserWithEmail:success  User: " + userID);
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else if(!task.isSuccessful()) {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot){
        Log.d(TAG, "checkIfUsernameExists: check if " + username + " already exists");

        User user = new User();

        for(DataSnapshot ds : dataSnapshot.child(userID).getChildren()){
             Log.d(TAG, "checkIfUsernameExists: dataSnapshot: " + ds);

             user.setUsername(ds.getValue(User.class).getUsername());
             Log.d(TAG, "checkIfUsernameExists: username: " + user.getUsername());

             if(StringManipulation.condenseUsername(user.getUsername()).equals(username)){
                 Log.d(TAG, "checkIfUsernameExists: FOUND A MATCH: " + user.getUsername());
                 return true;
             }
        }
        return false;
    }

    public void sendVerificationEmail(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                //Toast.makeText(mContext, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(mContext, "Failed to Authenticate", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    //add information to the users node
    //add information to the user_account_settings node
    public void addNewUser(String email, String username, String description, String website, String profile_photo){
        User user = new User(userID, 1, StringManipulation.condenseUsername(username), email);

        myRef.child(mContext.getString(R.string.dbname_users))
            .child(userID)
            .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(
                description, username, profile_photo, StringManipulation.condenseUsername(username),
                website, 0, 0, 0
        );

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(settings);
    }


    public void updateUI(FirebaseUser user){

    }

    //retrieves the account settings for the currently signed in user
    //database: user_account_settings node
    public UserSettings getUserSettings(DataSnapshot dataSnapshot){
        Log.d(TAG, "getUserAccountSettings: retrieving user account settings from firebase");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();

        for(DataSnapshot ds : dataSnapshot.getChildren()){

            //user_account_settings node
            if(ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings))) {
                Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                try {
                    settings.setDisplay_name(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getDisplay_name()
                    );

                    settings.setUsername(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getUsername()
                    );

                    settings.setWebsite(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getWebsite()
                    );

                    settings.setDescription(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getDescription()
                    );

                    settings.setProfile_photo(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getProfile_photo()
                    );

                    settings.setPosts(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getPosts()
                    );

                    settings.setFollowing(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getFollowing()
                    );

                    settings.setFollowers(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getFollowers()
                    );
                    Log.d(TAG, "getUserAccountSettings: retrieved user_account_settings info " + settings.toString());
                }
                catch (NullPointerException e){
                    Log.e(TAG, "getUserAccountSettings: NullPointerException " + e.getMessage());
                }
            }

            //user node
            if(ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings))) {
                Log.d(TAG, "getUserAccountSettings: datasnapshot: " + ds);

                user.setUsername(
                        ds.child(userID)
                                .getValue(User.class)
                                .getUsername()
                );

                user.setEmail(
                        ds.child(userID)
                                .getValue(User.class)
                                .getEmail()
                );

                user.setPhone_number(
                        ds.child(userID)
                                .getValue(User.class)
                                .getPhone_number()
                );

                user.setUser_id(
                        ds.child(userID)
                                .getValue(User.class)
                                .getUser_id()
                );

                Log.d(TAG, "getUserAccountSettings: retrieved user info " + user.toString());
            }
        }
        return new UserSettings(user, settings);
    }
}
