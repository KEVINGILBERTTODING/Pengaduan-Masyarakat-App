package com.example.pengaduanmasyarakat.FragmentAdmin;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.pengaduanmasyarakat.Fragment.user.AdminDetailPengaduanFragment;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.Model.TanggapanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;
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

public class EditTanggapanFragment extends Fragment {
    EditText etTanggapan;
    Button btnSimpan, btnImagePicker, btnDeleteTanggapan;
    ImageView imgPreview;
    TextView tvDataValid;
    EditText etPathImage;
    private File file1;

    SharedPreferences sharedPreferences;
    String idPengaduan, isiLaporan, namaKelurahan, statusPengaduan, tglPengaduan, foto1, foto2, foto3, jenis;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_tanggapan, container, false);

        etTanggapan = view.findViewById(R.id.etIsiTanggapan);
        btnSimpan = view.findViewById(R.id.btnSubmitTanggapan);
        btnImagePicker = view.findViewById(R.id.btnImgPicker1);
        etPathImage = view.findViewById(R.id.edtImgFile1);
        imgPreview = view.findViewById(R.id.imgPrev1);
        btnDeleteTanggapan = view.findViewById(R.id.btnDeleteTanggapan);


        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);

//        idPengaduan = getArguments().getString("id_pengaduan");
//        isiLaporan = getArguments().getString("isi_laporan");
//        namaKelurahan = getArguments().getString("kelurahan");
//        statusPengaduan = getArguments().getString("status_pengaduan");
//        tglPengaduan = getArguments().getString("tgl_pengaduan");
//        foto1 = getArguments().getString("foto");
//        foto2 = getArguments().getString("foto1");
//        foto3 = getArguments().getString("foto2");
//        jenis = getArguments().getString("jenis");


//        bundle.putString("id_tanggapan", tanggapanModelList.get(getAdapterPosition()).getIdTanggapan());
//        bundle.putString("isi_tanggapan", tanggapanModelList.get(getAdapterPosition()).getIsiTanggapan());
//        bundle.putString("foto", tanggapanModelList.get(getAdapterPosition()).getFotoTanggapan());
//        bundle.putString("status_tanggapan", tanggapanModelList.get(getAdapterPosition()).getStatusTanggapan());
//        bundle.putString("id_pengaduan", tanggapanModelList.get(getAdapterPosition()).getIdPengaduan());


        //        idPengaduan = getArguments().getString("id_pengaduan");
//        isiLaporan = getArguments().getString("isi_laporan");
//        namaKelurahan = getArguments().getString("kelurahan");
//        statusPengaduan = getArguments().getString("status_pengaduan");
//        tglPengaduan = getArguments().getString("tgl_pengaduan");
//        foto1 = getArguments().getString("foto");
//        foto2 = getArguments().getString("foto1");
//        foto3 = getArguments().getString("foto2");
//        jenis = getArguments().getString("jenis");



        // show and hide button delete
        PengaduanInterface pi = DataApi.getClient().create(PengaduanInterface.class);
        pi.getPengaduanById(getArguments().getString("id_pengaduan")).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                List<PengaduanModel>pengaduanModelList = response.body();
                if (pengaduanModelList.get(0).getStatusPengaduan().equals(getArguments().getString("status_tanggapan"))) {
                    btnDeleteTanggapan.setVisibility(View.VISIBLE);
                }else {
                    btnDeleteTanggapan.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {

            }
        });

        btnDeleteTanggapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments().getString("status_tanggapan").equals("selesai")) {
                    statusPengaduan = "pengerjaan";

                }else  if (getArguments().getString("status_tanggapan").equals("pengerjaan")) {
                    statusPengaduan = "valid";

                }else  if (getArguments().getString("status_tanggapan").equals("valid")) {
                    statusPengaduan = "proses";

                }else  if (getArguments().getString("status_tanggapan").equals("proses")) {
                    statusPengaduan = "belum_ditanggapi";

                }else  if (getArguments().getString("status_tanggapan").equals("tidak_valid")) {
                    statusPengaduan = "proses";

                }
                ProgressDialog pd = new ProgressDialog(getContext());
                pd.setTitle("Menghapus data...");
                pd.setCanceledOnTouchOutside(false);
                pd.show();

                TanggapanInterface tanggapanInterface = DataApi.getClient().create(TanggapanInterface.class);
                tanggapanInterface.deleteTanggapan(
                        getArguments().getString("id_tanggapan"),
                        getArguments().getString("id_pengaduan"),
                        statusPengaduan
                ).enqueue(new Callback<TanggapanModel>() {
                    @Override
                    public void onResponse(Call<TanggapanModel> call, Response<TanggapanModel> response) {
                        TanggapanModel tanggapanModel = response.body();

                        if (response.isSuccessful() && tanggapanModel.getStatus().equals("success")) {
                            pd.dismiss();
                            Toasty.success(getContext(), "Berhasil menghapus tanggapan", Toasty.LENGTH_SHORT).show();


                            PengaduanInterface pi = DataApi.getClient().create(PengaduanInterface.class);
                            pi.getPengaduanById(getArguments().getString("id_pengaduan")).enqueue(new Callback<List<PengaduanModel>>() {
                                @Override
                                public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                                    List<PengaduanModel>pengaduanModelList = response.body();
                                        Fragment fragment = new AdminDetailPengaduanFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("isi_laporan", pengaduanModelList.get(0).getIsiLaporan());
                                        bundle.putString("id_pengaduan", pengaduanModelList.get(0).getIdPengaduan());
                                        bundle.putString("kelurahan", pengaduanModelList.get(0).getNamaKelurahan());
                                        bundle.putString("status_pengaduan", pengaduanModelList.get(0).getStatusPengaduan());
                                        bundle.putString("tgl_pengaduan", pengaduanModelList.get(0).getTglPengaduan());
                                        bundle.putString("foto", pengaduanModelList.get(0).getFoto());
                                        bundle.putString("foto1", pengaduanModelList.get(0).getFoto1());
                                        bundle.putString("foto2", pengaduanModelList.get(0).getFoto2());
                                        bundle.putString("jenis", pengaduanModelList.get(0).getJenis());
                                        fragment.setArguments(bundle);
                                        getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();


                                }

                                @Override
                                public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {

                                }
                            });



                        }else {
                            pd.dismiss();
                            Toasty.error(getContext(), "Gagal menghapus tanggapan", Toasty.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<TanggapanModel> call, Throwable t) {
                        pd.dismiss();
                        Toasty.error(getContext(), "Cek koneksi anda", Toasty.LENGTH_SHORT).show();

                    }
                });
            }
        });











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

//        btnSimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (etTanggapan.getText().toString().isEmpty()) {
//                    Toasty.error(getContext(), "Field isi tanggapan tidak boleh kosong", Toasty.LENGTH_SHORT).show();
//                } else if (etPathImage.getText().toString().isEmpty()) {
//                    Toasty.error(getContext(), "Gambar tidak boleh kosong", Toasty.LENGTH_SHORT).show();
//
//                }else {
//                    ProgressDialog pd = new ProgressDialog(getContext());
//                    pd.setTitle("Menyimpan data...");
//                    pd.setCanceledOnTouchOutside(false);
//                    pd.show();
//
//                    Map map = new HashMap();
//                    map.put("isi_tanggapan", RequestBody.create(MediaType.parse("text/plain"), etTanggapan.getText().toString()));
//                    map.put("status_tanggapan", RequestBody.create(MediaType.parse("text/plain"), statusPengaduan));
//                    map.put("id_pengaduan", RequestBody.create(MediaType.parse("text/plain"), idPengaduan));
//                    map.put("id_user", RequestBody.create(MediaType.parse("text/plain"), sharedPreferences.getString("user_id", null)));
//
//                    // membuat requestbody file image
//                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file1);
//                    MultipartBody.Part gambar = MultipartBody.Part.createFormData("foto", file1.getName(), requestBody1);
//
//                    TanggapanInterface ti = DataApi.getClient().create(TanggapanInterface.class);
//                    ti.insertTanggapan(
//                            map,
//                            gambar
//                    ).enqueue(new Callback() {
//                        @Override
//                        public void onResponse(Call call, Response response) {
//
//                            if (response.isSuccessful()) {
//                                pd.dismiss();
//                                Toasty.success(getContext(), "Berhasil menambahkan tanggapan", Toasty.LENGTH_SHORT).show();
//                                Fragment fragment = new AdminDetailPengaduanFragment();
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString("id_pengaduan", idPengaduan);
//                                bundle.putString("isi_laporan", isiLaporan);
//                                bundle.putString("kelurahan", namaKelurahan);
//                                bundle.putString("status_pengaduan", statusPengaduan);
//                                bundle.putString("tgl_pengaduan", tglPengaduan);
//                                bundle.putString("foto", foto1);
//                                bundle.putString("foto1", foto2);
//                                bundle.putString("foto2", foto3);
//                                bundle.putString("jenis", jenis);
//                                fragment.setArguments(bundle);
//                                getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();
//                            }else {
//                                pd.dismiss();
//                                Toasty.error(getContext(), "gagal", Toasty.LENGTH_SHORT).show();
//
//                                Fragment fragment = new AdminDetailPengaduanFragment();
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString("id_pengaduan", idPengaduan);
//                                bundle.putString("isi_laporan", isiLaporan);
//                                bundle.putString("kelurahan", namaKelurahan);
//                                bundle.putString("status_pengaduan", getArguments().getString("status_pengaduan"));
//                                bundle.putString("tgl_pengaduan", tglPengaduan);
//                                bundle.putString("foto", foto1);
//                                bundle.putString("foto1", foto2);
//                                bundle.putString("foto2", foto3);
//                                bundle.putString("jenis", jenis);
//                                fragment.setArguments(bundle);
//                                getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call call, Throwable t) {
//                            pd.dismiss();
//                            Toasty.error(getContext(), "gagal", Toasty.LENGTH_SHORT).show();
//                            Fragment fragment = new AdminDetailPengaduanFragment();
//                            Bundle bundle = new Bundle();
//
//                            bundle.putString("id_pengaduan", idPengaduan);
//                            bundle.putString("isi_laporan", isiLaporan);
//                            bundle.putString("kelurahan", namaKelurahan);
//                            bundle.putString("status_pengaduan", getArguments().getString("status_pengaduan"));
//                            bundle.putString("tgl_pengaduan", tglPengaduan);
//                            bundle.putString("foto", foto1);
//                            bundle.putString("foto1", foto2);
//                            bundle.putString("foto2", foto3);
//                            bundle.putString("jenis", jenis);
//                            fragment.setArguments(bundle);
//                            getFragmentManager().beginTransaction().replace(R.id.frame_admin_container, fragment).commit();
//
//
//                        }
//                    });
//
//
//                }
//            }
//        });




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