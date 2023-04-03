package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.MasyarakatInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InsertDataMasyarakatFragment extends Fragment {

    EditText etNama, etUsername, etPassword, etPasswordVal, etNoTelp, etAlamat,etNik;
    Button btnSimpan;
    UserModel userModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_insert_data_masyarakat, container, false);
        etNama = v.findViewById(R.id.etNama);
        etUsername = v.findViewById(R.id.etUserName);
        etPassword = v.findViewById(R.id.etPassword);
        etPasswordVal = v.findViewById(R.id.etPasswordValidate);
        etNoTelp = v.findViewById(R.id.etNoTelp);
        etAlamat = v.findViewById(R.id.etUserAlamat);
        etNik = v.findViewById(R.id.etNik);
        btnSimpan = v.findViewById(R.id.btnInsert);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etUsername.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field username tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                } else if (etNama.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field nama tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field password tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etPasswordVal.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field password validasi tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etNoTelp.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field no telepon tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etAlamat.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field alamat tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etNik.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field nik tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                }else {
                    if (etPassword.getText().toString().equals(etPasswordVal.getText().toString())){

                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Menyimpan data...");
                pd.setCanceledOnTouchOutside(false);
                pd.show();
                MasyarakatInterface masyarakatInterface = DataApi.getClient().create(MasyarakatInterface.class);
                masyarakatInterface.insertMasyarakat(
                        etUsername.getText().toString(),
                        etNama.getText().toString(),
                        etPasswordVal.getText().toString(),
                        etNoTelp.getText().toString(),
                        etNik.getText().toString(),
                        etAlamat.getText().toString()
                ).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            userModel = response.body();
                            if (userModel.getStatus().equals("success")) {
                                Toasty.success(getContext(), userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                pd.dismiss();
                                getFragmentManager().popBackStack();
                            }else {
                                Toasty.error(getContext(), userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();
                        pd.dismiss();


                    }
                });

                    }else {
                        Toasty.error(getContext(), "Password tidak sesuai", Toasty.LENGTH_SHORT).show();

                    }
                }



            }
        });

        return v;
    }
}