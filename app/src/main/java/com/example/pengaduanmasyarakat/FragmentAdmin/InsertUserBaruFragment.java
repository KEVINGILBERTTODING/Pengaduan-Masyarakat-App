package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengaduanmasyarakat.Model.AdminUserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.UserInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InsertUserBaruFragment extends Fragment {

    Button btnInsert;
    EditText etUsername, etName, etNoTelp, etPassword, etValidatePass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_user_baru, container, false);

        etName = view.findViewById(R.id.etNama);
        etUsername = view.findViewById(R.id.etUserName);
        etNoTelp = view.findViewById(R.id.etNoTelp);
        etPassword = view.findViewById(R.id.etPassword);
        etValidatePass = view.findViewById(R.id.etPasswordValidate);
        btnInsert = view.findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(View -> {


            if (etUsername.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Username tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            } else if (etName.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Nama tidak boleh kosong", Toasty.LENGTH_SHORT).show();

            } else if (etNoTelp.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "No telepon tidak boleh kosong", Toasty.LENGTH_SHORT).show();

            } else if (etPassword.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Password tidak boleh kosong", Toasty.LENGTH_SHORT).show();

            } else if (etValidatePass.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Validasi password tidak boleh kosong", Toasty.LENGTH_SHORT).show();



            }else   {

                if (etPassword.getText().toString().equals(etValidatePass.getText().toString())) {
                    ProgressDialog progressDialog  = new ProgressDialog(getContext());
                    progressDialog.setTitle("Mengirim data ke server..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    UserInterface userInterface = DataApi.getClient().create(UserInterface.class);
                    userInterface.inserUser(
                            etName.getText().toString(),
                            etNoTelp.getText().toString(),
                            etUsername.getText().toString(),
                            etPassword.getText().toString()

                    ).enqueue(new Callback<AdminUserModel>() {
                        @Override
                        public void onResponse(Call<AdminUserModel> call, Response<AdminUserModel> response) {
                            AdminUserModel adminUserModel = response.body();
                            if (response.isSuccessful()) {
                                if (adminUserModel.getStatus().equals("success")) {
                                    Toasty.success(getContext(), adminUserModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                    getFragmentManager().popBackStack();
                                }else {
                                    Toasty.error(getContext(), adminUserModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                    progressDialog.dismiss();


                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<AdminUserModel> call, Throwable t) {
                            Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }else {
                    Toasty.error(getContext(), "Password tidak sama", Toasty.LENGTH_SHORT).show();
                }


            }

        });

        return view;
    }
}