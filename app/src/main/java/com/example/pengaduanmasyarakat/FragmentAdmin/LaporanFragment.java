package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.Adapter.MyPengaduanAdapter;
import com.example.pengaduanmasyarakat.Adapter.PengaduanAdapter;
import com.example.pengaduanmasyarakat.FileDownload;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LaporanFragment extends Fragment {
    Button btnDownload;
    FloatingActionButton fabFilter;
    TextView datePickerFrom, datePickerTo;
    Spinner spinnerStatus;
    String idMasyarakat;
    RecyclerView rvLaporanPengaduan;
    List<PengaduanModel> pengaduanModelList;
    LinearLayoutManager linearLayoutManager;
    String jenis, dateMulai, dateSelesai;

    PengaduanAdapter myPengaduanAdapter;
    String[] statusSpinner = {"Semua", "Belum ditanggapi", "Proses", "valid", "Pengerjaan", "Selesai", "Tidak valid"};

    SharedPreferences sharedPreferences;
    private String TAG = "Berhasil";
    String status;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_admin_laporan, container, false);
       btnDownload = view.findViewById(R.id.btnDownload);
       fabFilter = view.findViewById(R.id.fabFilter);
       rvLaporanPengaduan = view.findViewById(R.id.rvLaporanPengaduan);
       sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
       idMasyarakat = sharedPreferences.getString("user_id", null);
       btnDownload.setEnabled(false);








        fabFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_filter);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnFilter;
                btnFilter = dialog.findViewById(R.id.btnFilter);
                datePickerFrom = dialog.findViewById(R.id.datePickerFrom);
                datePickerTo = dialog.findViewById(R.id.datePickerTo);
                spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
                dialog.show();

                // show calendar
                datePickerFrom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                String monthFormatted = String.format("%02d", month + 1);
                                String tglFormatted = String.format("%02d", dayOfMonth);

                                if (dayOfMonth < 10) {
                                    tglFormatted = String.format("%02d", dayOfMonth);

                                }
                                datePickerFrom.setText(year + "-" + monthFormatted + "-" + tglFormatted );
                            }
                        }, year, month, day);

                        datePickerDialog.show();


                    }
                });

                datePickerTo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                String monthFormatted = String.format("%02d", month + 1);
                                String tglFormatted = String.format("%02d", dayOfMonth);

                                if (dayOfMonth < 10) {
                                   tglFormatted = String.format("%02d", dayOfMonth);

                                }
                                datePickerTo.setText(year + "-" + monthFormatted + "-" + tglFormatted );
                            }
                        }, year, month, day);

                        datePickerDialog.show();




                    }
                });

                // set adapter into spinner
                ArrayAdapter<String>adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, statusSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStatus.setAdapter(adapter);


                spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        status = statusSpinner[position];

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {



                    }
                });

                btnFilter.setOnClickListener(new View.OnClickListener() {




                    @Override
                    public void onClick(View v) {



                        if (datePickerFrom.getText().toString().isEmpty()) {

                            Toasty.error(getContext(), "Field tanggal mulai tidak boleh kosng", Toasty.LENGTH_SHORT).show();
                        }else if (datePickerTo.getText().toString().isEmpty()) {
                            Toasty.error(getContext(), "Field tanggal berakhir tidak boleh kosng", Toasty.LENGTH_SHORT).show();

                        }else {

                            if (status.equals("Selesai")) {
                                status = "selesai";

                            }else if (status.equals("Valid")) {
                                status = "valid";
                            }else if (status.equals("Belum ditanggapi")) {
                                status = "belum_ditanggapi";
                            }else if(status.equals("Semua")) {
                                status ="semua";
                            }else if (status.equals("Proses")) {
                                status = "proses";
                            }else if (status.equals("Pengerjaan")) {
                                status = "pengerjaan";
                            }else if (status.equals("Tidak valid")) {
                                status = "tidak_valid";
                            }

                            ProgressDialog progressDialog = new ProgressDialog(getContext());
                            progressDialog.setTitle("Memuat data...");
                            progressDialog.show();

                            jenis = status;
                            dateMulai = datePickerFrom.getText().toString();
                            dateSelesai = datePickerTo.getText().toString();


                            PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
                            pengaduanInterface.filterPengaduanAdmin(status,datePickerFrom.getText().toString(), datePickerTo.getText().toString())
                                    .enqueue(new Callback<List<PengaduanModel>>() {
                                        @Override
                                        public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                                            pengaduanModelList = response.body();
                                            if (pengaduanModelList.size() > 0) {
                                                myPengaduanAdapter = new PengaduanAdapter(getContext(), pengaduanModelList);
                                                linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                                rvLaporanPengaduan.setLayoutManager(linearLayoutManager);
                                                rvLaporanPengaduan.setAdapter(myPengaduanAdapter);
                                                rvLaporanPengaduan.setHasFixedSize(true);
                                                dialog.dismiss();

                                                progressDialog.dismiss();
                                                btnDownload.setEnabled(true);
                                                rvLaporanPengaduan.setVisibility(View.VISIBLE);


                                            }else {
                                                dialog.dismiss();
                                                progressDialog.dismiss();
                                                Toasty.normal(getContext(), "Tidak ada pengaduan", Toasty.LENGTH_SHORT).show();
                                                btnDownload.setEnabled(false);
                                                rvLaporanPengaduan.setVisibility(View.GONE);

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                                            Toasty.error(getContext(), "Periksa koneksi anda" , Toasty.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            btnDownload.setEnabled(false);
                                            rvLaporanPengaduan.setVisibility(View.GONE);


                                        }
                                    });

                        }
                    }
                });

            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://"+DataApi.IP+"/pengaduan/laporanpengaduan/cetakLaporanAdmin/"+jenis+"/"+dateMulai+"/"+dateSelesai+"/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });



       return view;
    }

}