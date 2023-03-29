package com.example.pengaduanmasyarakat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Fragment.user.ProfileUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        bottomNavigationView = findViewById(R.id.bottom_app_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if (item.getItemId() == R.id.menuBeranda) {
                   Toasty.normal(getApplicationContext(), "Menu Beranda", Toasty.LENGTH_LONG).show();
                   return true;
               } else if (item.getItemId() == R.id.menuHome) {
                   Toasty.normal(getApplicationContext(), "Menu Home", Toasty.LENGTH_LONG).show();
                   return false;
               } else if (item.getItemId() == R.id.menuProfile) {
                   replace(new ProfileUserFragment());
               }


                return false;


            }
        });







    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
       if(fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }

    }
}