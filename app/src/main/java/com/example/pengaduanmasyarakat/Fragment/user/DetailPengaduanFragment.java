package com.example.pengaduanmasyarakat.Fragment.user;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

public class DetailPengaduanFragment extends Fragment {


    Spinner spJenisKerusakan, spKecamatan, spKelurahan;
    String[] jenisKerusakan = {
            "Retak lelah dan deformasi pada semua lapisan perkerasan aspal",
            "Retak",
            "Lubang-lubang"
    };

    KecamatanAdapter kecamatanAdapter;
    KelurahanAdapter kelurahanAdapter;
    Button btnImagePicker1, btnImagePicker2, btnImagePicker3, btnSubmit, btnEditImagePicker, btnEditSimpanPengaduan,
    btnCancel;
    EditText etDetailImage1, etPathImage, etIsiLaporan;
    SharedPreferences sharedPreferences;
    List<PengaduanModel> pengaduanModelList;

    private File file1;
    String idKelurahan, jeniKerusakan, id_masyarakat, idPengaduan,
    foto1, foto2, foto3;
    ImageView ivImg1, ivImg2, ivImg3, ivPreviewImg;
    Dialog dialog;
    ProgressDialog progressDialog;








    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_pengaduan, container, false);

        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        id_masyarakat = sharedPreferences.getString("user_id", null);
        idPengaduan = getArguments().getString("id_pengaduan");
        dialog = new Dialog(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Mengunggah data");
        progressDialog.setCanceledOnTouchOutside(false);







        spJenisKerusakan = view.findViewById(R.id.spinnerJenisKerusakan);
        spKecamatan = view.findViewById(R.id.spinnerKecamatan);
        spKelurahan = view.findViewById(R.id.spinnerKelurahan);
        btnImagePicker1 = view.findViewById(R.id.btnImgPicker1);
        btnImagePicker2 = view.findViewById(R.id.btnImgPicker2);
        btnImagePicker3 = view.findViewById(R.id.btnImgPicker3);
        btnSubmit = view.findViewById(R.id.btnSubmitPengaduan);
        etIsiLaporan = view.findViewById(R.id.etIsiLaporan);
        ivImg1 = view.findViewById(R.id.img1);
        ivImg2 = view.findViewById(R.id.img2);
        ivImg3 = view.findViewById(R.id.img3);




        etIsiLaporan.setText(getArguments().getString("isi_laporan"));
        setImage(getArguments().getString("photo"), ivImg1);
        setImage(getArguments().getString("photo1"), ivImg2);
        setImage(getArguments().getString("photo2"), ivImg3);

        // click image untuk zoom in dan zoom out
        fullScreenImage(idPengaduan, ivImg1, "foto");
        fullScreenImage(idPengaduan, ivImg2, "foto1");
        fullScreenImage(idPengaduan, ivImg3, "foto2");

        dialog.setContentView(R.layout.layout_edit_image);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);

        btnEditImagePicker = dialog.findViewById(R.id.btnEditImagePicker);
        etPathImage = dialog.findViewById(R.id.etPathImage);
        ivPreviewImg = dialog.findViewById(R.id.ivPreviewImg);
        btnEditSimpanPengaduan = dialog.findViewById(R.id.btnEditSimpanPengaduan);
        btnCancel = dialog.findViewById(R.id.btnEditCancel);







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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                etPathImage.setText("");
                Glide.with(getContext())
                        .load(R.drawable.baseline_image_24)
                        .into(ivPreviewImg);
            }
        });



        // klik button image picker

        btnImagePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();


                btnEditImagePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);


                    }
                });

                btnEditSimpanPengaduan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etPathImage.getText().toString().isEmpty()) {
                            Toasty.error(getContext(), "Anda belum memilih gambar", Toasty.LENGTH_SHORT).show();
                        }else{
                            progressDialog.show();
                            Map map = new HashMap();
                            map.put("id_pengaduan", RequestBody.create(MediaType.parse("text/plain"), idPengaduan));
                            map.put("field", RequestBody.create(MediaType.parse("text/plain"), "foto"));


                            // membuat request body file
                            RequestBody RequestFile1 = RequestBody.create(MediaType.parse("image/*"), file1);

                            // create multipart body
                            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("foto", file1.getName(), RequestFile1);

                            PengaduanInterface pengaduanInterface1 = DataApi.getClient().create(PengaduanInterface.class);
                            pengaduanInterface1.updateImage(
                                    map,
                                    fileToUpload1
                            ).enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                   if (response.isSuccessful()) {



                                       pengaduanInterface1.getPengaduanById(idPengaduan).enqueue(new Callback<List<PengaduanModel>>() {
                                           @Override
                                           public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                                               pengaduanModelList = response.body();
                                               if (response.isSuccessful()) {
                                                   Glide.with(getContext())
                                                           .load(pengaduanModelList.get(0).getFoto())
                                                           .dontAnimate()
                                                           .skipMemoryCache(true)
                                                           .diskCacheStrategy(DiskCacheStrategy.NONE)
                                                           .override(400, 400)
                                                           .into(ivImg1);

                                                   progressDialog.dismiss();

                                                   Toasty.success(getContext(), "Berhasil mengubah gambar", Toasty.LENGTH_SHORT).show();
                                                   dialog.dismiss();
                                                   etPathImage.setText("");
                                                   Glide.with(getContext())
                                                           .load(R.drawable.baseline_image_24)
                                                           .into(ivPreviewImg);


                                               }

                                           }

                                           @Override
                                           public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                                               Log.e("sdda", "onFailure: ", t);

                                           }
                                       });




                                   }else {
                                       Toasty.error(getContext(), "Gagal mengubah data", Toasty.LENGTH_SHORT).show();
                                   }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toasty.error(getContext(), "Periksa koneksi anda", Toasty.LENGTH_SHORT).show();


                                }
                            });

                        }
                    }
                });




            }
        });
        btnImagePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();


                btnEditImagePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);


                    }
                });

                btnEditSimpanPengaduan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (etPathImage.getText().toString().isEmpty()) {
                            Toasty.error(getContext(), "Anda belum memilih gambar", Toasty.LENGTH_SHORT).show();
                        }else{
                            progressDialog.show();
                            Map map = new HashMap();
                            map.put("id_pengaduan", RequestBody.create(MediaType.parse("text/plain"), idPengaduan));
                            map.put("field", RequestBody.create(MediaType.parse("text/plain"), "foto1"));


                            // membuat request body file
                            RequestBody RequestFile1 = RequestBody.create(MediaType.parse("image/*"), file1);

                            // create multipart body
                            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("foto", file1.getName(), RequestFile1);

                            PengaduanInterface pengaduanInterface1 = DataApi.getClient().create(PengaduanInterface.class);
                            pengaduanInterface1.updateImage(
                                    map,
                                    fileToUpload1
                            ).enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                   if (response.isSuccessful()) {



                                       pengaduanInterface1.getPengaduanById(idPengaduan).enqueue(new Callback<List<PengaduanModel>>() {
                                           @Override
                                           public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                                               pengaduanModelList = response.body();
                                               if (response.isSuccessful()) {
                                                   Glide.with(getContext())
                                                           .load(pengaduanModelList.get(0).getFoto1())
                                                           .dontAnimate()
                                                           .skipMemoryCache(true)
                                                           .diskCacheStrategy(DiskCacheStrategy.NONE)
                                                           .override(400, 400)
                                                           .into(ivImg2);

                                                   progressDialog.dismiss();

                                                   Toasty.success(getContext(), "Berhasil mengubah gambar", Toasty.LENGTH_SHORT).show();
                                                   dialog.dismiss();
                                                   etPathImage.setText("");
                                                   Glide.with(getContext())
                                                           .load(R.drawable.baseline_image_24)
                                                           .into(ivPreviewImg);


                                               }

                                           }

                                           @Override
                                           public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                                               Log.e("sdda", "onFailure: ", t);

                                           }
                                       });




                                   }else {
                                       Toasty.error(getContext(), "Gagal mengubah data", Toasty.LENGTH_SHORT).show();
                                   }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toasty.error(getContext(), "Periksa koneksi anda", Toasty.LENGTH_SHORT).show();


                                }
                            });

                        }
                    }
                });




            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
               pengaduanInterface.updatePengaduan(
                       etIsiLaporan.getText().toString(),
                       idPengaduan,
                       jeniKerusakan,
                       id_masyarakat,
                       idKelurahan


               ).enqueue(new Callback<PengaduanModel>() {
                   @Override
                   public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                       PengaduanModel pengaduanModel = response.body();
                       if (pengaduanModel.getStatus().equals("success")) {
                           Toasty.success(getContext(), "Berhasil mengubah data", Toasty.LENGTH_SHORT).show();
                           getFragmentManager().popBackStack();
                       }else {
                           Toasty.error(getContext(), "Gagal mengubah data", Toasty.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<PengaduanModel> call, Throwable t) {
                       Toasty.error(getContext(), "Periksa koneksi anda", Toasty.LENGTH_SHORT).show();

                   }
               });

                }


        });





        return view;
    }

    private void fullScreenImage(String image, ImageView ivImage, String field){
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new FullScreenImageFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id_pengaduan", image);
                bundle.putString("field", field);
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
                ivPreviewImg.setImageURI(uri);
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