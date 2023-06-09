package com.example.pengaduanmasyarakat.Fragment.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    FloatingActionButton fabAdd;
    TextView tvUsername, tvTotalBlmTanggap, tvTotalProses, tvTotalValid, tvTotalPengerjaan, tvTotalSelesai,
            tvTotalTidakValid;
    SharedPreferences sharedPreferences;
    PengaduanInterface pengaduanInterface;
    List<PengaduanModel> pengaduanModelList;
    CardView cvMenuProses, cvMenuSelesai, cvMenuValid, cvMenuPengerjaan, cvMenuBlmDitgp, cvMnuTdkValid, cvMenuLaporan;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvTotalSelesai = view.findViewById(R.id.tvTotalSelesai);
        tvTotalBlmTanggap = view.findViewById(R.id.tvTotalBlmTanggap);
        tvTotalProses = view.findViewById(R.id.tvTotalProses);
        tvTotalValid = view.findViewById(R.id.tvTotalValid);
        tvTotalPengerjaan = view.findViewById(R.id.tvTotalPengerjaan);
        tvTotalTidakValid = view.findViewById(R.id.tvTotalTidakValid);
        cvMenuProses = view.findViewById(R.id.cvMnuProses);
        cvMenuSelesai = view.findViewById(R.id.cvMnuSelesai);
        cvMenuValid = view.findViewById(R.id.cvMnuValid);
        cvMenuPengerjaan = view.findViewById(R.id.cvMnuPengerjaan);
        cvMenuBlmDitgp =view.findViewById(R.id.cvMnuBlmDitgp);
        cvMnuTdkValid = view.findViewById(R.id.cvMenuTdkalid);
        cvMenuLaporan = view.findViewById(R.id.cvMenuLaporan);


        fabAdd = view.findViewById(R.id.fabAdd);
        tvUsername = view.findViewById(R.id.title1);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        tvUsername.setText("Halo "+ sharedPreferences.getString("username", "null"));

        // settext total pengaduan selesai
        getMyPengaduan(sharedPreferences.getString("user_id", null), "selesai", tvTotalSelesai);
        // settext total pengaduan belum tanggapi
        getMyPengaduan(sharedPreferences.getString("user_id", null), "belum_ditanggapi", tvTotalBlmTanggap);
        // settext total pengaduan proses
        getMyPengaduan(sharedPreferences.getString("user_id", null), "proses", tvTotalProses);
        // settext total pengaduan valid
        getMyPengaduan(sharedPreferences.getString("user_id", null), "valid", tvTotalValid);
        // settext total pengaduan pengerjaan
        getMyPengaduan(sharedPreferences.getString("user_id", null), "pengerjaan", tvTotalPengerjaan);
        // settext total pengaduan pengerjaan
        getMyPengaduan(sharedPreferences.getString("user_id", null), "tidak_valid", tvTotalTidakValid);


        cvMenuProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMyPengaduan("Pengaduan diProses", "proses", new MyPengaduanFragment());




            }
        });

        cvMenuSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Selesai", "selesai", new MyPengaduanFragment());
            }
        });

        cvMenuValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Valid", "valid", new MyPengaduanFragment());
            }
        });

        cvMenuPengerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Pengerjaan", "pengerjaan", new MyPengaduanFragment());
            }
        });
        cvMenuBlmDitgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Belum ditanggapi", "belum_ditanggapi", new MyPengaduanFragment());
            }
        });
        cvMnuTdkValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Tidak Valid", "tidak_valid", new MyPengaduanFragment());
            }
        });

        cvMenuLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, new LaporanFragment())
                        .commit();
            }
        });




        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               getFragmentManager().beginTransaction()
                       .replace(R.id.frame_container, new CreatePengaduanFragment())
                       .addToBackStack(null)
                       .commit();


            }
        });


        return  view;
    }


    public void getMyPengaduan(String userId, String jenis, TextView tvTotal){
        pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getMyPengaduan(userId, jenis).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                pengaduanModelList = response.body();
                if (pengaduanModelList != null) {
                    tvTotal.setText(String.valueOf(pengaduanModelList.size()));
                }else {


                }

            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
//                Toasty.error(getContext(), "Periksa koneksi anda",Toasty.LENGTH_SHORT).show();
                Log.e("adas", "Error ni: ", t);
                tvTotal.setText("0");

            }
        });

    }

    private void showMyPengaduan(String judul, String jenis, Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("jenis", jenis);
        bundle.putString("judul", judul);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}