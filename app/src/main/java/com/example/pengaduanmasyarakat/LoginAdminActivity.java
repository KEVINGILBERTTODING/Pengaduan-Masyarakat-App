package com.example.pengaduanmasyarakat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pengaduanmasyarakat.Model.AdminUserModel;
import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AuthInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginAdminActivity extends AppCompatActivity {
    EditText etUsername, etPassword;

    // membuat object sharedpreference
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        if (sharedPreferences.getBoolean("login", false)){
            startActivity(new Intent(getApplicationContext(), MainAdminActivity.class));
            finish();
        }
    }

    public void btnLogin(View view) {

        AuthInterface authInterface = DataApi.getClient().create(AuthInterface.class);
        authInterface.loginAdmin(etUsername.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<AdminUserModel>() {
            @Override
            public void onResponse(Call<AdminUserModel> call, Response<AdminUserModel> response) {
                AdminUserModel userModel = response.body();
                if (userModel.getStatus().equals("success")){
                    editor.putBoolean("login", true);
                    editor.putBoolean("login_admin", true);
                    editor.putString("user_id", userModel.getIdUser());
                    editor.putString("nama_lengkap", userModel.getNamaLengkap());
                    editor.putString("username", userModel.getUserName());
                    editor.putString("jabatan", userModel.getJabatan());
                    editor.commit();
                    startActivity(new Intent(LoginAdminActivity.this, MainAdminActivity.class));
                    finish();
                    Toasty.success(LoginAdminActivity.this, userModel.getMessage(), Toasty.LENGTH_SHORT).show();

                }else {
                    Toasty.error(LoginAdminActivity.this, userModel.getMessage(), Toasty.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<AdminUserModel> call, Throwable t) {
                Log.e("Login Activity", "ini errornya  : ", t );

            }
        });



    }


}