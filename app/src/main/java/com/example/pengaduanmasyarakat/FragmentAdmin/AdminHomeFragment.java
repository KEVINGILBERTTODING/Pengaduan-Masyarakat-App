package com.example.pengaduanmasyarakat.FragmentAdmin;

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
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Fragment.user.CreatePengaduanFragment;
import com.example.pengaduanmasyarakat.Fragment.user.LaporanFragment;
import com.example.pengaduanmasyarakat.Fragment.user.MyPengaduanFragment;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AdminPengaduanInterface;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AdminHomeFragment extends Fragment {

    TextView tvUsername, tvTotalBlmTanggap, tvTotalProses, tvTotalValid, tvTotalPengerjaan, tvTotalSelesai,
            tvTotalTidakValid;
    SharedPreferences sharedPreferences;
    PengaduanInterface pengaduanInterface;
    List<PengaduanModel> pengaduanModelList;
    CardView cvMenuProses, cvMenuSelesai, cvMenuValid, cvMenuPengerjaan, cvMenuBlmDitgp, cvMnuTdkValid, cvMenuLaporan, cvMnjmData, cvSaran;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_admin_home, container, false);
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
        cvMnjmData = view.findViewById(R.id.cvMnuMnjmData);
        cvSaran = view.findViewById(R.id.cvMenuSaran);



        tvUsername = view.findViewById(R.id.title1);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        tvUsername.setText("Halo "+ sharedPreferences.getString("username", "null"));

        // settext total pengaduan selesai
        displayTotalPengaduan( "selesai", tvTotalSelesai);
        // settext total pengaduan belum tanggapi
        displayTotalPengaduan( "belum_ditanggapi", tvTotalBlmTanggap);
        // settext total pengaduan proses
        displayTotalPengaduan( "proses", tvTotalProses);
        // settext total pengaduan valid
        displayTotalPengaduan( "valid", tvTotalValid);
        // settext total pengaduan pengerjaan
        displayTotalPengaduan( "pengerjaan", tvTotalPengerjaan);
        // settext total pengaduan pengerjaan
        displayTotalPengaduan( "tidak_valid", tvTotalTidakValid);



        cvMnjmData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_option_menu_manajemen_data);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnUser, btnMasyarakat, btnKecamatan, btnKelurahan;
                btnUser = dialog.findViewById(R.id.btnUser);
                btnMasyarakat = dialog.findViewById(R.id.btnMasyarakat);
                btnKecamatan = dialog.findViewById(R.id.btnKecamatan);
                btnKelurahan = dialog.findViewById(R.id.btnKelurahan);
                dialog.show();

                // btnUser click
                btnUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        manajemenData(new ListUserFragment());
                        dialog.dismiss();
                    }
                });

                btnMasyarakat.setOnClickListener(View ->{

                    getFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_admin_container, new ListMasyarakatFragment())
                            .commit();

                    dialog.dismiss();

                });

                btnKecamatan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.frame_admin_container, new ListKecamatanFragment())
                                .addToBackStack(null)
                                .commit();
                    }
                });

                btnKelurahan.setOnClickListener(View->{
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_admin_container, new ListKelurahanFragment())
                            .addToBackStack(null)
                            .commit();
                    dialog.dismiss();
                });
            }
        });
        cvMenuProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showMyPengaduan("Pengaduan diProses", "proses", new PengaduanFragment());




            }
        });

        cvMenuSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Selesai", "selesai", new PengaduanFragment());
            }
        });

        cvMenuValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Valid", "valid", new PengaduanFragment());
            }
        });

        cvMenuPengerjaan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Pengerjaan", "pengerjaan", new PengaduanFragment());
            }
        });
        cvMenuBlmDitgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Belum ditanggapi", "belum_ditanggapi", new PengaduanFragment());
            }
        });
        cvMnuTdkValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyPengaduan("Pengaduan Tidak Valid", "tidak_valid", new PengaduanFragment());
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

        cvSaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_admin_container, new ListSaranFragment())
                        .commit();
            }
        });

        if (sharedPreferences.getString("jabatan", null).equals("administrator")) {
            cvMnjmData.setVisibility(View.VISIBLE);
        }else {
            cvMnjmData.setVisibility(View.GONE);
        }










       return view;
    }


    private void displayTotalPengaduan(String status, TextView tvTotal) {
        AdminPengaduanInterface aip = DataApi.getClient().create(AdminPengaduanInterface.class);
        aip.getAllPengaduanByStatus(status).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    tvTotal.setText(String.valueOf(response.body().size()));
                }else {
                    tvTotal.setText("0");
                }
            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                tvTotal.setText("Terjadi kesalahan");


            }
        });
    }

    private void showMyPengaduan(String judul, String jenis, Fragment fragment) {

        Bundle bundle = new Bundle();
        bundle.putString("jenis", jenis);
        bundle.putString("judul", judul);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_admin_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void manajemenData(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_admin_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}