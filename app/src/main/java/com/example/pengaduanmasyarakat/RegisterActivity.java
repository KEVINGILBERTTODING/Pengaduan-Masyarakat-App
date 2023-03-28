package com.example.pengaduanmasyarakat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.iterfaces.AuthInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class RegisterActivity extends AppCompatActivity {

    String TAG = "RegisterActiviy";
    EditText etNamaLengkap, etUsername, etPassword, etNoTelp, etNik, etAlamat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etNamaLengkap = findViewById(R.id.et_nama_lengkap);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etNoTelp = findViewById(R.id.et_no_telp);
        etNik = findViewById(R.id.et_nik);
        etAlamat = findViewById(R.id.et_alamat);




    }

    public void btnRegster(View view) {
        AuthInterface authInterface = DataApi.getClient().create(AuthInterface.class);
        authInterface.register(
                etNamaLengkap.getText().toString(),
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                etNoTelp.getText().toString(),
                etNik.getText().toString(),
                etAlamat.getText().toString()
        ).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel = response.body();
                if (userModel.getStatus().equals("success")) {
                    Toasty.success(RegisterActivity.this, "Berhasil registrasi", Toasty.LENGTH_SHORT).show();
                }else {
                    Toasty.error(RegisterActivity.this, "Gagal registrasi", Toasty.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

//Show error
                Log.e(TAG, "ini errornya: ", t);
            }
        });


    }
}