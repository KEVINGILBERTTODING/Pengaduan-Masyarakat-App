package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.R;


public class DetailSaranFragment extends Fragment {
    TextView tvTanggal;
    EditText etNama, etNoTelp, etAlamat, etSaran;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_saran, container, false);

        etNama = view.findViewById(R.id.etNama);
        etNoTelp = view.findViewById(R.id.etNoTelp);
        etAlamat = view.findViewById(R.id.etAlamat);
        etSaran = view.findViewById(R.id.etSaran);
        tvTanggal = view.findViewById(R.id.tvTanggal);



        etNama.setText(getArguments().getString("nama"));
        etNoTelp.setText(getArguments().getString("no_telp"));
        tvTanggal.setText(getArguments().getString("tanggal"));
        etAlamat.setText(getArguments().getString("alamat"));
        etSaran.setText(getArguments().getString("saran"));

        etNama.setEnabled(false);
        etNoTelp.setEnabled(false);
        etAlamat.setEnabled(false);
        etSaran.setEnabled(false);


        return view;
    }
}