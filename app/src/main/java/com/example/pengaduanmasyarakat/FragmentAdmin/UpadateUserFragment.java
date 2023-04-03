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

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpadateUserFragment extends Fragment {

    String userId;
    EditText etUsername, etName, etNoTlp, etJabatan;
    Button btnUpdate;
    UserInterface userInterface;
    AdminUserModel adminUserModel;
    List<AdminUserModel>adminUserModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upadate_user, container, false);
        etUsername = view.findViewById(R.id.etUserName);
        etName =  view.findViewById(R.id.etNama);
        etNoTlp = view.findViewById(R.id.etNoTelp);
        etJabatan = view.findViewById(R.id.etJabatan);
        btnUpdate =view.findViewById(R.id.btnUpdate);

        etUsername.setEnabled(false);
        etJabatan.setEnabled(false);

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Memuat data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        userInterface = DataApi.getClient().create(UserInterface.class);
        userInterface.getUserByUserId(getArguments().getString("id_user")).enqueue(new Callback<List<AdminUserModel>>() {
            @Override
            public void onResponse(Call<List<AdminUserModel>> call, Response<List<AdminUserModel>> response) {
                adminUserModelList = response.body();
                if (adminUserModelList.size() > 0) {
                    etUsername.setText(adminUserModelList.get(0).getUserName());
                    etName.setText(adminUserModelList.get(0).getNamaLengkap());
                    etNoTlp.setText(adminUserModelList.get(0).getNoTelp());
                    etJabatan.setText(adminUserModelList.get(0).getJabatan());
                    btnUpdate.setEnabled(true);
                    progressDialog.dismiss();

                }else {
                    Toasty.error(getContext(), "Gagal memuat data", Toasty.LENGTH_SHORT).show();
                    btnUpdate.setEnabled(false);
                    progressDialog.dismiss();
                    getFragmentManager().popBackStack();
                }

            }

            @Override
            public void onFailure(Call<List<AdminUserModel>> call, Throwable t) {
                Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();

                getFragmentManager().popBackStack();


            }
        });

        btnUpdate.setOnClickListener(View ->{
            if (etName.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Field nama tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            } else if (etNoTlp.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Field no telepon tidak boleh kosong", Toasty.LENGTH_SHORT).show();

            }else {
                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Mengirim data...");
                pd.show();
                userInterface = DataApi.getClient().create(UserInterface.class);
                userInterface.updateUser(
                        getArguments().getString("id_user"),
                        etName.getText().toString(),
                        etNoTlp.getText().toString()
                ).enqueue(new Callback<AdminUserModel>() {
                    @Override
                    public void onResponse(Call<AdminUserModel> call, Response<AdminUserModel> response) {
                        adminUserModel = response.body();
                        if (response.isSuccessful()) {
                            if (adminUserModel.getStatus().equals("success")) {
                                Toasty.success(getContext(), "Berhasil mengubah user", Toasty.LENGTH_SHORT).show();
                                getFragmentManager().popBackStack();
                                pd.dismiss();
                            }else {
                                Toasty.error(getContext(), "Gagal mengubah user", Toasty.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AdminUserModel> call, Throwable t) {
                        Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                });
            }
        });








        return view;




    }
}