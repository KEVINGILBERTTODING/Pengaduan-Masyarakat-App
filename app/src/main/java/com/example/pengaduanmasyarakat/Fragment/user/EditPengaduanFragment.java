package com.example.pengaduanmasyarakat.Fragment.user;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Adapter.KecamatanAdapter;
import com.example.pengaduanmasyarakat.Adapter.KelurahanAdapter;
import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

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

public class EditPengaduanFragment extends Fragment {


    Spinner spJenisKerusakan, spKecamatan, spKelurahan;
    String[] jenisKerusakan = {
            "Retak lelah dan deformasi pada semua lapisan perkerasan aspal",
            "Retak",
            "Lubang-lubang"
    };

    KecamatanAdapter kecamatanAdapter;
    KelurahanAdapter kelurahanAdapter;
    Button btnImagePicker1, btnImagePicker2, btnImagePicker3, btnSubmit;
    EditText etDetailImage1, etDetailImage2, etDetailImage3, etIsiLaporan;
    SharedPreferences sharedPreferences;

    private File file1, file2, file3;
    String idKelurahan, jeniKerusakan, id_masyarakat, masyarakatId ;
    ImageView ivImg1, ivImg2, ivImg3;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_pengaduan, container, false);

        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        id_masyarakat = sharedPreferences.getString("user_id", null);







        spJenisKerusakan = view.findViewById(R.id.spinnerJenisKerusakan);
        spKecamatan = view.findViewById(R.id.spinnerKecamatan);
        spKelurahan = view.findViewById(R.id.spinnerKelurahan);
        btnImagePicker1 = view.findViewById(R.id.btnImgPicker1);
        btnImagePicker2 = view.findViewById(R.id.btnImgPicker2);
        btnImagePicker3 = view.findViewById(R.id.btnImgPicker3);
        btnSubmit = view.findViewById(R.id.btnSubmitPengaduan);
        etDetailImage1 = view.findViewById(R.id.edtImgFile1);
        etDetailImage2 = view.findViewById(R.id.edtImgFile2);
        etDetailImage3 = view.findViewById(R.id.edtImgFile3);
        etIsiLaporan = view.findViewById(R.id.etIsiLaporan);
        ivImg1 = view.findViewById(R.id.img1);
        ivImg2 = view.findViewById(R.id.img2);
        ivImg3 = view.findViewById(R.id.img3);


        etIsiLaporan.setText(getArguments().getString("isi_laporan"));
        setImage(getArguments().getString("photo"), ivImg1);
        setImage(getArguments().getString("photo1"), ivImg2);
        setImage(getArguments().getString("photo2"), ivImg3);

        // click image untuk zoom in dan zoom out
        fullScreenImage(getArguments().getString("photo"), ivImg1);
        fullScreenImage(getArguments().getString("photo1"), ivImg2);
        fullScreenImage(getArguments().getString("photo2"), ivImg3);







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

        spKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                long kelurahanId = spKelurahan.getAdapter().getItemId(position);
                idKelurahan = String.valueOf(kelurahanId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spJenisKerusakan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jeniKerusakan = spJenisKerusakan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



//        Test cek izin mengakses file external
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
        } else {

        }

        // klik button image picker

        btnImagePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);


            }
        });
        btnImagePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);


            }
        });
        btnImagePicker3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);



            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etDetailImage1.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Harap memilih gambar", Toasty.LENGTH_SHORT).show();
                }else if (etDetailImage2.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Harap memilih gambar", Toasty.LENGTH_SHORT).show();
                }else if (etDetailImage3.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Harap memilih gambar", Toasty.LENGTH_SHORT).show();
                }else if (etIsiLaporan.getText().toString().isEmpty()) {
                    Toasty.error(getContext(), "Harap mengisi laporan", Toasty.LENGTH_SHORT).show();
                }
                else {


                    Map map = new HashMap();
                    map.put("id_masyarakat", RequestBody.create(MediaType.parse("text/plain"), sharedPreferences.getString("user_id", null)));
                    map.put("id_kelurahan", RequestBody.create(MediaType.parse("text/plain"), idKelurahan));
                    map.put("jenis", RequestBody.create(MediaType.parse("text/plain"), jeniKerusakan));
                    map.put("isi_laporan", RequestBody.create(MediaType.parse("text/plain"), etIsiLaporan.getText().toString()));



                    // membuat request body file
                    RequestBody RequestFile1 = RequestBody.create(MediaType.parse("image/*"), file1);
                    RequestBody RequestFile1ile2 = RequestBody.create(MediaType.parse("image/*"), file2);
                    RequestBody RequestFile1ile3 = RequestBody.create(MediaType.parse("image/*"), file3);

                    // create multipart body
                    MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("foto", file1.getName(), RequestFile1);
                    MultipartBody.Part fileToUpload2 = MultipartBody.Part.createFormData("foto1", file2.getName(), RequestFile1ile2);
                    MultipartBody.Part fileToUpload3 = MultipartBody.Part.createFormData("foto2", file3.getName(), RequestFile1ile3);
                    PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
                    pengaduanInterface.createPengaduan(
                            map,
                            fileToUpload1,
                            fileToUpload2,
                            fileToUpload3
                    ).enqueue(new Callback<PengaduanModel>() {
                                  @Override
                                  public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                                      PengaduanModel pengaduanModel = response.body();
                                      if (pengaduanModel.getStatus().equals("success")) {
                                          Toasty.success(getContext(), "Berhasil membuat pengaduan", Toasty.LENGTH_SHORT).show();
                                          getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment())
                                                  .commit();
                                      } else {
                                          Toasty.error(getContext(), "Gagal membuat pengaduan", Toasty.LENGTH_SHORT).show();
                                      }
                                  }

                                  @Override
                                  public void onFailure(Call<PengaduanModel> call, Throwable t) {
                                      Toasty.error(getContext(), "Gagal membuat pengaduan", Toasty.LENGTH_SHORT).show();
                                  }
                              }
                    );









                }




            }
        });



        return view;
    }

    private void fullScreenImage(String image, ImageView ivImage){
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new FullScreenImageFragment();
                Bundle bundle = new Bundle();
                bundle.putString("image", image);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });


    }

    private void setImage(String image, ImageView ivImage){
        Glide.with(getContext())
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(400, 400)
                .dontAnimate()
                .into(ivImage);
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
if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (requestCode == 1) {
//                ivImage1.setImageURI(uri);
                file1 = new File(getRealPathFromUri(uri));
                etDetailImage1.setText(file1.getName());
            } else if (requestCode == 2) {
//                ivImage2.setImageURI(uri);
                file2 = new File(getRealPathFromUri(uri));
                etDetailImage2.setText(file2.getName());
            } else if (requestCode == 3) {
//                ivImage3.setImageURI(uri);
                file3 = new File(getRealPathFromUri(uri));
                etDetailImage3.setText(file3.getName());
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