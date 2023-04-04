package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.pengaduanmasyarakat.Adapter.KecamatanAdapter;
import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KelurahanInterface;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateKelurahanFragment extends Fragment {
    EditText etKelurahan;
    Button btnInsert;
    Spinner spinner;
    KecamatanAdapter kecamatanAdapter;
    Long kecamatanId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_kelurahan, container, false);

        etKelurahan = view.findViewById(R.id.etNamaKelurahan);
        btnInsert = view.findViewById(R.id.btnInsert);
        spinner = view.findViewById(R.id.spinnerKecamatan);

        etKelurahan.setText(getArguments().getString("kelurahan"));



        // membuat adapter untuk spinner kecamatan
        PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getKecamatan().enqueue(new Callback<List<KecamatanModel>>() {
            @Override
            public void onResponse(Call<List<KecamatanModel>> call, Response<List<KecamatanModel>> response) {
                if (response.isSuccessful()) {
                    kecamatanAdapter = new KecamatanAdapter(getContext(), response.body());
                    spinner.setAdapter(kecamatanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<KecamatanModel>> call, Throwable t) {
                Toasty.error(getContext(), "Gagal load data", Toasty.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kecamatanId = spinner.getAdapter().getItemId(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etKelurahan.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field nama kelurahan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                }else {
                    ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Mengirim data...");
                pd.show();
                pd.setCanceledOnTouchOutside(false);
                KelurahanInterface ki = DataApi.getClient().create(KelurahanInterface.class);
                ki.updateKelurahan(
                        getArguments().getString("id_kelurahan"),
                        etKelurahan.getText().toString(),
                        String.valueOf(kecamatanId)

                ).enqueue(new Callback<KelurahanModel>() {
                    @Override
                    public void onResponse(Call<KelurahanModel> call, Response<KelurahanModel> response) {
                        KelurahanModel km = response.body();
                        if (response.isSuccessful() && km.getStatus().equals("success")) {
                            pd.dismiss();
                            Toasty.success(getContext(), "Berhasil mengubah kelurahan baru", Toasty.LENGTH_SHORT).show();
                            getFragmentManager().popBackStack();
                        }else{
                            pd.dismiss();
                            Toasty.error(getContext(), "Gagal mengubah kelurahan baru", Toasty.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<KelurahanModel> call, Throwable t) {
                        Toasty.error(getContext(), "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();


                    }
                });

                }
//

//
            }
        });



        return view;
    }
}