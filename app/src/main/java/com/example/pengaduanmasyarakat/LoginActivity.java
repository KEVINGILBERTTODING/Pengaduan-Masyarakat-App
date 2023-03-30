package com.example.pengaduanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AuthInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;

    // membuat object sharedpreference
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        if (sharedPreferences.getBoolean("login", false)){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    public void btnLogin(View view) {

        AuthInterface authInterface = DataApi.getClient().create(AuthInterface.class);
        authInterface.login(etUsername.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                if (userModel.getStatus().equals("success")){
                    editor.putBoolean("login", true);
                    editor.putBoolean("login_user", true);
                    editor.putString("user_id", userModel.getIdMasyarakat());
                    editor.putString("nama_lengkap", userModel.getNamaLengkap());

                    editor.putString("username", userModel.getUserName());
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    Toasty.success(LoginActivity.this, userModel.getMessage(), Toasty.LENGTH_SHORT).show();

                }else {
                    Toasty.error(LoginActivity.this, userModel.getMessage(), Toasty.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.e("Login Activity", "ini errornya  : ", t );

            }
        });



    }

    public void btnRegister(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void btnAdminLogin(View view) {
        startActivity(new Intent(LoginActivity.this, SplashScreen.class));
    }
}