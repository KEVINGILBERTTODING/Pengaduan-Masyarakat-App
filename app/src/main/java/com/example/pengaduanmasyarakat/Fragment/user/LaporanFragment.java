package com.example.pengaduanmasyarakat.Fragment.user;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.FileDownload;
import com.example.pengaduanmasyarakat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;


public class LaporanFragment extends Fragment {
    Button btnDownload;
    FloatingActionButton fabFilter;
    TextView datePickerFrom, datePickerTo;
    Spinner spinnerStatus;
    String[] statusSpinner = {"Semua", "Belum ditanggapi", "Proses", "valid", "Pengerjaan", "Selesai", "Tidak valid"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_laporan, container, false);
       btnDownload = view.findViewById(R.id.btnDownload);
       fabFilter = view.findViewById(R.id.fabFilter);

        String url = "http://192.168.1.12/pengaduan/laporanpengaduan/";
        String title = "PDF Download";
        String description = "Downloading PDF file";
        String fileName = "my_file.pdf";

        // show permission write external storage




        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        // permission not granted, request it
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        // show popup for runtime permission
                        requestPermissions(permissions, 1000);
                    } else {

                        FileDownload fileDownload = new FileDownload(getContext());
                        fileDownload.downloadFile(url, title, description, fileName);
                    }
                } else {
                    // system os is less then marshmallow
                    // perform download
                    FileDownload fileDownload = new FileDownload(getContext());
                    fileDownload.downloadFile(url, title, description, fileName);
                }

            }
        });

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
                                datePickerFrom.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
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
                                datePickerTo.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        }, year, month, day);

                        datePickerDialog.show();




                    }
                });

                ArrayAdapter<String>adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, statusSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStatus.setAdapter(adapter);

            }
        });



       return view;
    }

}