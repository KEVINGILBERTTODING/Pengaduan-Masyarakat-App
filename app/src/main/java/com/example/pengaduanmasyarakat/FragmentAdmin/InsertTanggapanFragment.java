package com.example.pengaduanmasyarakat.FragmentAdmin;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Fragment.user.AdminDetailPengaduanFragment;
import com.example.pengaduanmasyarakat.Model.TanggapanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.TanggapanInterface;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertTanggapanFragment extends Fragment {
    EditText etTanggapan;
    Button btnSimpan, btnImagePicker;
    ImageView imgPreview;
    TextView tvDataValid;
    EditText etPathImage;
    private File file1;
    Spinner spinner;
    String []opsi = {
            "valid",
            "tidak_valid"
    };
    SharedPreferences sharedPreferences;
    String idPengaduan, isiLaporan, namaKelurahan, statusPengaduan, tglPengaduan, foto1, foto2, foto3, jenis;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_tanggapan, container, false);

        etTanggapan = view.findViewById(R.id.etIsiTanggapan);
        btnSimpan = view.findViewById(R.id.btnSubmitTanggapan);
        btnImagePicker = view.findViewById(R.id.btnImgPicker1);
        etPathImage = view.findViewById(R.id.edtImgFile1);
        imgPreview = view.findViewById(R.id.imgPrev1);
        spinner = view.findViewById(R.id.spinner);
        tvDataValid = view.findViewById(R.id.tvDataValid);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);

        idPengaduan = getArguments().getString("id_pengaduan");
        isiLaporan = getArguments().getString("isi_laporan");
        namaKelurahan = getArguments().getString("kelurahan");
        statusPengaduan = getArguments().getString("status_pengaduan");
        tglPengaduan = getArguments().getString("tgl_pengaduan");
        foto1 = getArguments().getString("foto");
        foto2 = getArguments().getString("foto1");
        foto3 = getArguments().getString("foto2");
        jenis = getArguments().getString("jenis");

        ArrayAdapter<String>opsiDataValid = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opsi);
        opsiDataValid.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(opsiDataValid);






        if (statusPengaduan.equals("belum_ditanggapi")) {
            statusPengaduan = "proses";
        }else  if (statusPengaduan.equals("proses")) {
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    statusPengaduan = spinner.getSelectedItem().toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner.setVisibility(View.VISIBLE);
            tvDataValid.setVisibility(View.VISIBLE);
        }else if (statusPengaduan.equals("valid")) {
            statusPengaduan = "pengerjaan";
        }else if (statusPengaduan.equals("pengerjaan")) {
            statusPengaduan ="selesai";
        }





        //        Test cek izin mengakses file external
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
        } else {

        }

        btnImagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);


            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTanggapan.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Field isi tanggapan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
                } else if (etPathImage.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Gambar tidak boleh kosong", Toasty.LENGTH_SHORT).show();

                }else {
                    ProgressDialog pd = new ProgressDialog(getContext());
                    pd.setTitle("Menyimpan data...");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();

                    Map map = new HashMap();
                    map.put("isi_tanggapan", RequestBody.create(MediaType.parse("text/plain"), etTanggapan.getText().toString()));
                    map.put("status_tanggapan", RequestBody.create(MediaType.parse("text/plain"), statusPengaduan));
                    map.put("id_pengaduan", RequestBody.create(MediaType.parse("text/plain"), idPengaduan));
                    map.put("id_user", RequestBody.create(MediaType.parse("text/plain"), sharedPreferences.getString("user_id", null)));

                    // membuat requestbody file image
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    MultipartBody.Part gambar = MultipartBody.Part.createFormData("foto", file1.getName(), requestBody1);

                    TanggapanInterface ti = DataApi.getClient().create(TanggapanInterface.class);
                    ti.insertTanggapan(
                            map,
                            gambar
                    ).enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            if (response.isSuccessful()) {
                                pd.dismiss();
                                Toasty.success(getContext(), "Berhasil menambahkan tanggapan", Toasty.LENGTH_SHORT).show();
                                Fragment fragment = new AdminDetailPengaduanFragment();
                                Bundle bundle = new Bundle();

                                bundle.putString("id_pengaduan", idPengaduan);
                                bundle.putString("isi_laporan", isiLaporan);
                                bundle.putString("kelurahan", namaKelurahan);
                                bundle.putString("status_pengaduan", statusPengaduan);
                                bundle.putString("tgl_pengaduan", tglPengaduan);
                                bundle.putString("foto", foto1);
                                bundle.putString("foto1", foto2);
                                bundle.putString("foto2", foto3);
                                bundle.putString("jenis", jenis);
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();
                            }else {
                                pd.dismiss();
                                Toasty.error(getContext(), "gagal", Toasty.LENGTH_SHORT).show();

                                Fragment fragment = new AdminDetailPengaduanFragment();
                                Bundle bundle = new Bundle();

                                bundle.putString("id_pengaduan", idPengaduan);
                                bundle.putString("isi_laporan", isiLaporan);
                                bundle.putString("kelurahan", namaKelurahan);
                                bundle.putString("status_pengaduan", getArguments().getString("status_pengaduan"));
                                bundle.putString("tgl_pengaduan", tglPengaduan);
                                bundle.putString("foto", foto1);
                                bundle.putString("foto1", foto2);
                                bundle.putString("foto2", foto3);
                                bundle.putString("jenis", jenis);
                                fragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();

                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            pd.dismiss();
                            Toasty.error(getContext(), "gagal", Toasty.LENGTH_SHORT).show();
                            Fragment fragment = new AdminDetailPengaduanFragment();
                            Bundle bundle = new Bundle();

                            bundle.putString("id_pengaduan", idPengaduan);
                            bundle.putString("isi_laporan", isiLaporan);
                            bundle.putString("kelurahan", namaKelurahan);
                            bundle.putString("status_pengaduan", getArguments().getString("status_pengaduan"));
                            bundle.putString("tgl_pengaduan", tglPengaduan);
                            bundle.putString("foto", foto1);
                            bundle.putString("foto1", foto2);
                            bundle.putString("foto2", foto3);
                            bundle.putString("jenis", jenis);
                            fragment.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();


                        }
                    });


                }
            }
        });




        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (requestCode == 1) {
                imgPreview.setVisibility(View.VISIBLE);
                imgPreview.setImageURI(uri);
                file1 = new File(getRealPathFromUri(uri));
                etPathImage.setText(file1.getName());
            }

        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = requireActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(columnIndex);
        cursor.close();
        return path;
    }
}