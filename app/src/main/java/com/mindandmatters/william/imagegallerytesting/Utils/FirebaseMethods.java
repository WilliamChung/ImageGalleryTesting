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
import com.mindandmatters.william.imagegallerytesting.Models.User;

/**
 * Created by lappy on 2018-04-25.
 */

public class FirebaseMethods {

    private static final String TAG = "Firebase Methods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context){
        mAuth = FirebaseAuth.getInstance();
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
                            userID = mAuth.getCurrentUser().getUid();
                            Log.w(TAG, "createUserWithEmail:success  User: " + userID);
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
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

        for(DataSnapshot ds : dataSnapshot.getChildren()){
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


    public void updateUI(FirebaseUser user){

    }
}
