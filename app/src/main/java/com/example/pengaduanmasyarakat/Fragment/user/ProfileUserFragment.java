package com.example.pengaduanmasyarakat.Fragment.user;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AuthInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileUserFragment extends Fragment {

    CardView btnUbahProfile, btnUbahPass, btnTentang;
    SharedPreferences sharedPreferences;
    TextView txtUsername;
    UserModel userModel;
    List<UserModel> userModelList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_user, container, false);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        btnUbahProfile = view.findViewById(R.id.btn_ubah_profile);
        btnUbahPass = view.findViewById(R.id.btnUbhPass);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtUsername.setText("Profil " + username);
        btnTentang = view.findViewById(R.id.btnTentang);
        String userId = sharedPreferences.getString("user_id", null);




        btnUbahProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_ubah_profile);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                final EditText etNamaLengkap, etNoTelp, etAlamat;
                Button btnSubmit;
                etNamaLengkap = dialog.findViewById(R.id.etu_nama_lengkap);
                etNoTelp = dialog.findViewById(R.id.etu_no_telp);
                etAlamat = dialog.findViewById(R.id.etu_alamat);
                btnSubmit =  dialog.findViewById(R.id.btnSimpanUbahProfile);
                dialog.show();

                // tampilkan data user ke dalam edittext

                AuthInterface ai = DataApi.getClient().create(AuthInterface.class);
                ai.getUserById(userId).enqueue(new Callback<List<UserModel>>() {
                    @Override
                    public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                        userModelList = response.body();
                        if (userModelList.size() != 0){
                            etNamaLengkap.setText(userModelList.get(0).getNamaLengkap());
                            etNoTelp.setText(userModelList.get(0).getNoTelp());
                            etAlamat.setText((userModelList.get(0).getAlamat()));
                            btnSubmit.setEnabled(true);

                        } else {
                            Toasty.error(getContext(), "Gagal mengambil data", Toasty.LENGTH_SHORT).show();
                            btnSubmit.setEnabled(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<UserModel>> call, Throwable t) {

                        Log.e("test", "onFailure: ", t);
                    }
                });

                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AuthInterface ai = DataApi.getClient().create(AuthInterface.class);
                        ai.updateProfile(
                                etNamaLengkap.getText().toString(),
                                etNoTelp.getText().toString(),
                                etAlamat.getText().toString(),
                                userId

                        ).enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                userModel  = response.body();

                                if (userModel.getStatus().equals("success")) {
                                    dialog.dismiss();
                                    Toasty.success(getContext(), "Berhasil mengubah profile", Toasty.LENGTH_SHORT).show();
                                }else {
                                    Toasty.error(getContext(), "Gagal mengubah profile", Toasty.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<UserModel> call, Throwable t) {
                                Log.e("text", "error update profile: ", t);

                            }
                        });
                    }
                });

                


            }




        });
        btnUbahPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog2 = new Dialog(getContext());
                dialog2.setContentView(R.layout.layout_ubah_password);
                dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnSubmitPassword;
                final EditText etOldPw, etNewPw, etValidatePw;
                etOldPw = dialog2.findViewById(R.id.etu_oldPassword);
                etNewPw = dialog2.findViewById(R.id.etu_passNew);
                etValidatePw = dialog2.findViewById(R.id.etu_passValid);
                btnSubmitPassword = dialog2.findViewById(R.id.btnUbahPw);
                dialog2.show();

                btnSubmitPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etNewPw.getText().toString().equals(etValidatePw.getText().toString())) {
                            AuthInterface ai = DataApi.getClient().create(AuthInterface.class);
                            ai.updatePasswod(
                                    etOldPw.getText().toString(),
                                    etValidatePw.getText().toString(),
                                    userId).enqueue(new Callback<UserModel>() {
                                @Override
                                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                    userModel = response.body();
                                    if (userModel.getStatus().equals("success")) {
                                        Toasty.success(getContext(), userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                        dialog2.dismiss();

                                    } else {
                                        Toasty.error(getContext(), userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserModel> call, Throwable t) {
                                    Log.e("Frgament", "onFailure: ", t);

                                }
                            });
                        }else {
                            Toasty.error(getContext(), "Password tidak sesuai", Toasty.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_about);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnAboutOk = dialog.findViewById(R.id.btn_about_ok);
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnAboutOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }


        });


        return view;
    }
}