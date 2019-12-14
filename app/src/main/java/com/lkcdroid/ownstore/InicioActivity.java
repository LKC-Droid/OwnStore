package com.lkcdroid.ownstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.lkcdroid.ownstore.ui.Tiendas.TiendasFragment;
import com.lkcdroid.ownstore.ui.TiendasDestacadas.DestacadasFragment;
import com.lkcdroid.ownstore.ui.tools.ToolsFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            Toast.makeText(getApplicationContext(),"Funciona", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(getApplicationContext(),"Ups no funciona :^(", Toast.LENGTH_LONG).show();
            goLogIn();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView textName = (TextView) headerView.findViewById(R.id.textName);
        TextView textEmail = (TextView) headerView.findViewById(R.id.textEmail);
        final ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        final ImageButton destacadosbutton = (ImageButton) findViewById(R.id.destacadosButton);
        final ImageButton search = (ImageButton) findViewById(R.id.searchButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim).replace(R.id.nav_host_fragment, new TiendasFragment()).commit();
                    homeButton.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                    destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    search.setImageTintList(getResources().getColorStateList(R.color.buttons));
            }
        });

        destacadosbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim).replace(R.id.nav_host_fragment, new DestacadasFragment()).commit();
                homeButton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                search.setImageTintList(getResources().getColorStateList(R.color.buttons));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                abrirBuscador();
                homeButton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.buttons));
            }
        });





        textName.setText(acct.getDisplayName());
        textEmail.setText(acct.getEmail());



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_shops, R.id.nav_myshop,
                R.id.nav_share, R.id.nav_signout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.nav_host_fragment, new TiendasFragment()).commit();
    }

    private void abrirBuscador() {
        Intent intent = new Intent(this, BuscarActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void goLogIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager fm = getSupportFragmentManager();
        switch (menuItem.getItemId()){
            case R.id.nav_signout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Confirmación");
                builder.setMessage("¿Desea Cerrar sesión?");
                builder.setPositiveButton("Cerrar Sesión", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signOut();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
                break;

            case R.id.nav_home:
                fm.beginTransaction().replace(R.id.nav_host_fragment, new TiendasFragment()).commit();
                break;
            case R.id.nav_shops:
                fm.beginTransaction().replace(R.id.nav_host_fragment, new DestacadasFragment()).commit();
                break;
            case R.id.nav_myshop:
                fm.beginTransaction().replace(R.id.nav_host_fragment, new TiendasFragment()).commit();
                break;
        }
        return true;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        goLogIn();
                    }
                });
    }

}
