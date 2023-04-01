package com.example.pengaduanmasyarakat.Fragment.user;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.R;

public class DetailPengaduanFragment extends Fragment {

    TextView tvIsiLaporan, tvStatusPengaduan1;
    CardView cvStatusPengaduan;
    ImageView ivFoto1, ivFoto2, ivFoto3, icStatus;
    EditText etIsiLaporan, etJenis, etTanggal, etKelurahan;
    String idPengaduan, isiLaporan, namaKelurahan, statusPengaduan, tglPengaduan, foto1, foto2, foto3,
    jenis;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_pengaduan, container, false);

        idPengaduan = getArguments().getString("id_pengaduan");
        isiLaporan = getArguments().getString("isi_laporan");
        namaKelurahan = getArguments().getString("kelurahan");
        statusPengaduan = getArguments().getString("status_pengaduan");
        tglPengaduan = getArguments().getString("tgl_pengaduan");
        foto1 = getArguments().getString("foto");
        foto2 = getArguments().getString("foto1");
        foto3 = getArguments().getString("foto2");
        jenis = getArguments().getString("jenis");

        tvIsiLaporan = view.findViewById(R.id.tvIsiPengaduan);
        tvStatusPengaduan1 = view.findViewById(R.id.tvStatusPengaduan);
        etIsiLaporan = view.findViewById(R.id.etIsiLaporan);
        etJenis = view.findViewById(R.id.etJenisLaporan);
        etKelurahan = view.findViewById(R.id.etKelurahan);
        etTanggal = view.findViewById(R.id.etTanggal);
        cvStatusPengaduan = view.findViewById(R.id.cvStatusPengaduan);
        icStatus = view.findViewById(R.id.icStatus);
        ivFoto1 = view.findViewById(R.id.foto1);
        ivFoto2 = view.findViewById(R.id.foto2);
        ivFoto3 = view.findViewById(R.id.foto3);


        tvIsiLaporan.setText(isiLaporan);

        etIsiLaporan.setText(isiLaporan);
        etTanggal.setText(tglPengaduan);
        etKelurahan.setText(namaKelurahan);
        etJenis.setText(jenis);


        // set card background
        if (statusPengaduan.equals("selesai")){
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.blue));
            tvStatusPengaduan1.setTextColor(getResources().getColor(R.color.white));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.baseline_calendar_month_24));
            tvStatusPengaduan1.setText(statusPengaduan);
        } else if (statusPengaduan.equals("proses")) {
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.main));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.process));
            tvStatusPengaduan1.setText(statusPengaduan);


        }else if (statusPengaduan.equals("valid")) {
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.green));
            tvStatusPengaduan1.setTextColor(getResources().getColor(R.color.white));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.check));
            tvStatusPengaduan1.setText(statusPengaduan);

        }else if (statusPengaduan.equals("pengerjaan")) {
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.orange));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.hammer));
            tvStatusPengaduan1.setText(statusPengaduan);

        }else if (statusPengaduan.equals("belum_ditanggapi")) {
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.gray));
            tvStatusPengaduan1.setTextColor(getResources().getColor(R.color.black));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.close));
            tvStatusPengaduan1.setText("Belum ditanggapi");


        }else if (statusPengaduan.equals("tidak_valid")) {
            cvStatusPengaduan.setCardBackgroundColor(getResources().getColor(R.color.gray));
            tvStatusPengaduan1.setTextColor(getResources().getColor(R.color.black));
            icStatus.setImageDrawable(getResources().getDrawable(R.drawable.close));
            tvStatusPengaduan1.setText("Tidak valid");



        }

        // Set image
        setImage(ivFoto1, foto1);
        setImage(ivFoto2, foto2);
        setImage(ivFoto3, foto3);

        // full screen image
        fullScreenImage(ivFoto1, foto1);
        fullScreenImage(ivFoto2, foto2);
        fullScreenImage(ivFoto3, foto3);



//


        return view;
    }

    private void setImage(ImageView image, String resource) {
        Glide.with(getContext())
                .load(resource)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
                .into(image);

    }

    private void fullScreenImage(ImageView img, String resource) {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new DetailFullScreenImageFragment();
                Bundle b = new Bundle();
                b.putString("image", resource);
                f.setArguments(b);
                getFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack(null).commit();
            }
        });

    }
}