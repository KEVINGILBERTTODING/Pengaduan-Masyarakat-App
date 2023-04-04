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

public class EditMasyarakatFragment extends Fragment {

    EditText etNama, etUsername, etNoTelp, etAlamat,etNik;
    Button btnSimpan;
    UserModel userModel;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_masyarakat, container, false);

        etNama = v.findViewById(R.id.etNama);
        etUsername = v.findViewById(R.id.etUserName);
        etNoTelp = v.findViewById(R.id.etNoTelp);
        etAlamat = v.findViewById(R.id.etUserAlamat);
        etNik = v.findViewById(R.id.etNik);
        btnSimpan = v.findViewById(R.id.btnInsert);


        etUsername.setText(getArguments().getString("username"));
        etNama.setText(getArguments().getString("nama"));
        etNoTelp.setText(getArguments().getString("no_telepon"));
        etAlamat.setText(getArguments().getString("alamat"));
        etNik.setText(getArguments().getString("nik"));

        etUsername.setEnabled(false);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNama.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field nama tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                } else if (etNoTelp.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field no telepon tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etAlamat.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field alamat tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                } else if (etNik.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field nik tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                }else {


                        ProgressDialog pd = new ProgressDialog(getContext());
                        pd.setTitle("Menyimpan data...");
                        pd.setCanceledOnTouchOutside(false);
                        pd.show();
                        MasyarakatInterface masyarakatInterface = DataApi.getClient().create(MasyarakatInterface.class);
                        masyarakatInterface.updateMasyarakat(
                                getArguments().getString("id_masyarakat"),
                                etNama.getText().toString(),
                                etNoTelp.getText().toString(),
                                etNik.getText().toString(),
                                etAlamat.getText().toString()
                        ).enqueue(new Callback<UserModel>() {
                            @Override
                            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                if (response.isSuccessful()) {
                                    userModel =response.body();
                                    if (userModel.getStatus().equals("success")) {
                                        Toasty.success(getContext(), "Berhasil mengubah data", Toasty.LENGTH_SHORT).show();
                                        pd.dismiss();
                                        getFragmentManager().popBackStack();
                                    }else {
                                        Toasty.error(getContext(), "Gagal mengubah data", Toasty.LENGTH_SHORT).show();
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
                    }
                }


        });

        return v;
    }
}