package com.lkcdroid.ownstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
import com.lkcdroid.ownstore.ui.Notificaciones.ShareFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        } else {
            goLogIn();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView textName =  headerView.findViewById(R.id.textName);
        TextView textEmail =  headerView.findViewById(R.id.textEmail);
        ImageView userPhoto =  headerView.findViewById(R.id.imagProf);
        final ImageButton homeButton =  findViewById(R.id.homeButton);
        final ImageButton destacadosbutton =  findViewById(R.id.destacadosButton);
        final ImageButton search =  findViewById(R.id.searchButton);
        final ImageButton myShop =  findViewById(R.id.miTiendaButton);
        final ImageButton notificaciones = findViewById(R.id.notificationsButton);

        Glide.with(this)
                .load(acct.getPhotoUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(userPhoto);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .setCustomAnimations(R.anim.right_slide, R.anim.left_slide )
                        .replace(R.id.nav_host_fragment, new TiendasFragment())
                        .commit();
                    homeButton.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                    destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    search.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    notificaciones.setImageTintList(getResources().getColorStateList(R.color.buttons));
            }
        });

        destacadosbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().setCustomAnimations(R.anim.right_slide, R.anim.left_slide).replace(R.id.nav_host_fragment, new DestacadasFragment()).commit();
                homeButton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                search.setImageTintList(getResources().getColorStateList(R.color.buttons));
                notificaciones.setImageTintList(getResources().getColorStateList(R.color.buttons));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                abrirBuscador();
            }
        });

        notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    FragmentManager fm = getSupportFragmentManager();
                    fm.beginTransaction()
                            .setCustomAnimations(R.anim.right_slide, R.anim.left_slide)
                            .replace(R.id.nav_host_fragment, new ShareFragment())
                            .commit();
                    homeButton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    destacadosbutton.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    search.setImageTintList(getResources().getColorStateList(R.color.buttons));
                    notificaciones.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));

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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
