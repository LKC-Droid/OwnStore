package com.lkcdroid.ownstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class menuActivity extends AppCompatActivity {
    private TextView textNombre;
    private GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Toast.makeText(getApplicationContext(),"Funciona", Toast.LENGTH_LONG).show();

            String nombre = acct.getDisplayName().toString();

        } else {
            Toast.makeText(getApplicationContext(),"Ups no funciona :^(", Toast.LENGTH_LONG).show();
        }

        textNombre.setText(acct.getDisplayName());

    }


}
