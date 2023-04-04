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

public class UpdateKecamatanFragment extends Fragment {

    EditText etKecamatan;
    Button btnUpdate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_kecamatan, container, false);

        etKecamatan = view.findViewById(R.id.etNamaKecamatan);
        btnUpdate = view.findViewById(R.id.btnUpdate);

        etKecamatan.setText(getArguments().getString("kecamatan"));


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etKecamatan.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field kecamatan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                }else {
                    ProgressDialog pd = new ProgressDialog(getContext());
                    pd.setTitle("Menyimpan data...");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();

                    KecamatanInterface ki = DataApi.getClient().create(KecamatanInterface.class);
                    ki.updateKecamatan(getArguments().getString("id_kecamatan"),
                            etKecamatan.getText().toString()
                            ).enqueue(new Callback<KecamatanModel>() {
                        @Override
                        public void onResponse(Call<KecamatanModel> call, Response<KecamatanModel> response) {
                            KecamatanModel kecamatanModel = response.body();
                            if (response.isSuccessful() && kecamatanModel.getStatus().equals("success")) {
                                Toasty.success(getContext(), "Berhasil mengubah data kecamatan", Toasty.LENGTH_SHORT).show();
                                pd.dismiss();
                                getFragmentManager().popBackStack();
                            }else {
                                Toasty.error(getContext(), "Gagal mengubah data kecamatan", Toasty.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<KecamatanModel> call, Throwable t) {
                            Toasty.error(getContext(), "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();
                            pd.dismiss();

                        }
                    });
                }
            }
        });


        return view;
    }
}