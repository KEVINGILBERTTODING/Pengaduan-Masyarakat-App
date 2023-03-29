package com.example.pengaduanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvUsername;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);

        tvUsername  = findViewById(R.id.tvUsername);

        tvUsername.setText("Welcome" + sharedPreferences.getString("username", null));
    }
}