package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KecamatanInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InsertKecamatanFragment extends Fragment {
    EditText etKecamatan;
    Button btnSimpan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_insert_kecamatan, container, false);

        etKecamatan = view.findViewById(R.id.etNamaKecamatan);
        btnSimpan = view.findViewById(R.id.btnInsert);

        btnSimpan.setOnClickListener(View ->{

            if (etKecamatan.getText().toString().isEmpty()) {
                Toasty.error(getContext(), "Nama kecamatan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
            }else {

                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Menyimpan data...");
                pd.setCanceledOnTouchOutside(false);
                pd.show();


                // send using retrofit
                KecamatanInterface ki = DataApi.getClient().create(KecamatanInterface.class);
                ki.insertKecamatan(etKecamatan.getText().toString()).enqueue(new Callback<KecamatanModel>() {
                    @Override
                    public void onResponse(Call<KecamatanModel> call, Response<KecamatanModel> response) {
                        KecamatanModel kecamatanModel = response.body();
                        if (response.isSuccessful() && kecamatanModel.getStatus().equals("success")) {
                            Toasty.success(getContext(), "Berhasil menambahkan kecamatan baru", Toasty.LENGTH_SHORT).show();
                            pd.dismiss();
                            getFragmentManager().popBackStack();
                        }else {
                            pd.dismiss();
                            Toasty.error(getContext(), "Gagal menambahkan kecamatan baru", Toasty.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<KecamatanModel> call, Throwable t) {
                        pd.dismiss();
                        Toasty.error(getContext(), "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();

                    }
                });

            }

        });









        return view;
    }
}