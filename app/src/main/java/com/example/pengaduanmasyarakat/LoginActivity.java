package com.example.pengaduanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    public void btnLogin(View view) {

        AuthInterface authInterface = DataApi.getClient().create(AuthInterface.class);
        authInterface.login(etUsername.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                if (userModel.getStatus().equals("success")){
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
}