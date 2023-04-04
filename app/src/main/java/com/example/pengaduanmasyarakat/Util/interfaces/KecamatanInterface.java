package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.KecamatanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KecamatanInterface {

    @GET("admin/manajemendata/getKecamatan")
    Call<List<KecamatanModel>>getKecamatan();


    @FormUrlEncoded
    @POST("admin/manajemendata/deleteKecamatan")
    Call<KecamatanModel>deleteKecamatan(
            @Field("id_kecamatan") String idKecamatan
    );

    @FormUrlEncoded
    @POST("admin/manajemendata/insertKecamatan")
    Call<KecamatanModel>insertKecamatan(
            @Field("kecamatan") String kecamtan
    );
}
