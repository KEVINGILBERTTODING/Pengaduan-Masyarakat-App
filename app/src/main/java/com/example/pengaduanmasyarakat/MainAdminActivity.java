package com.example.pengaduanmasyarakat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pengaduanmasyarakat.Fragment.user.HomeFragment;
import com.example.pengaduanmasyarakat.Fragment.user.ProfileUserFragment;
import com.example.pengaduanmasyarakat.Fragment.user.allPengaduanFragment;
import com.example.pengaduanmasyarakat.FragmentAdmin.AdminHomeFragment;
import com.example.pengaduanmasyarakat.FragmentAdmin.ProfileAdminFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainAdminActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        bottomNavigationView = findViewById(R.id.bottom_app_bar);

        replace(new AdminHomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               if (item.getItemId() == R.id.menuBeranda) {
                  replace(new allPengaduanFragment());
                   return true;
               } else if (item.getItemId() == R.id.menuHome) {
                   replace(new AdminHomeFragment());
                   return true;
               } else if (item.getItemId() == R.id.menuProfile) {
                   replace(new ProfileAdminFragment());
                   return true;
               }


                return false;


            }
        });







    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_admin_container,fragment);
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