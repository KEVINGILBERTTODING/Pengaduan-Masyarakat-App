package com.example.pengaduanmasyarakat.Fragment.user;

import static android.app.Activity.RESULT_OK;

import static androidx.fragment.app.FragmentManager.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pengaduanmasyarakat.Adapter.KecamatanAdapter;
import com.example.pengaduanmasyarakat.Adapter.KelurahanAdapter;
import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePengaduanFragment extends Fragment {


    Spinner spJenisKerusakan, spKecamatan, spKelurahan;
    String[] jenisKerusakan = {
            "Retak lelah dan deformasi pada semua lapisan perkerasan aspal",
            "Retak",
            "Lubang-lubang"
    };

    KecamatanAdapter kecamatanAdapter;
    KelurahanAdapter kelurahanAdapter;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_pengaduan, container, false);
        spJenisKerusakan = view.findViewById(R.id.spinnerJenisKerusakan);
        spKecamatan = view.findViewById(R.id.spinnerKecamatan);
        spKelurahan = view.findViewById(R.id.spinnerKelurahan);

        // membuat adapter untuk spinner jenis kerusakan
        ArrayAdapter<String>jenisKerusakanAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, jenisKerusakan);
        jenisKerusakanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenisKerusakan.setAdapter(jenisKerusakanAdapter);

        // membuat adapter untuk spinner kecamatan
        PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getKecamatan().enqueue(new Callback<List<KecamatanModel>>() {
            @Override
            public void onResponse(Call<List<KecamatanModel>> call, Response<List<KecamatanModel>> response) {
                if (response.isSuccessful()) {
                    kecamatanAdapter = new KecamatanAdapter(getContext(), response.body());
                    spKecamatan.setAdapter(kecamatanAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<KecamatanModel>> call, Throwable t) {
                Toasty.error(getContext(), "Gagal load data", Toasty.LENGTH_SHORT).show();
            }
        });


//        spinner kecamatan cliked listener
        spKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long kecamtanId = spKecamatan.getAdapter().getItemId(position);
                pengaduanInterface.getKelurahan(String.valueOf(kecamtanId)).enqueue(new Callback<List<KelurahanModel>>() {
                    @Override
                    public void onResponse(Call<List<KelurahanModel>> call, Response<List<KelurahanModel>> response) {
                        if (response.isSuccessful()) {
                            kelurahanAdapter = new KelurahanAdapter(getContext(), response.body());
                            spKelurahan.setAdapter(kelurahanAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<KelurahanModel>> call, Throwable t) {
                        Toasty.error(getContext(), "Gagal load data", Toasty.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toasty.error(getContext(), "Harap memilih kecamatan", Toasty.LENGTH_SHORT).show();

            }
        });



//        Test cek izin mengakses file external
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
        } else {

        }






        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toasty.success(getContext(), "Berhasil load gambar", Toasty.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String path = getRealPathFromUri(uri);
            File file = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            // Upload file to server using Retrofit


        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 200);
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