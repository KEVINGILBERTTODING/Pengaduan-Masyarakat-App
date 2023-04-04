package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.KelurahanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface KelurahanInterface {

    @GET("admin/manajemendata/getKelurahan")
    Call<List<KelurahanModel>>getAllKelurahan();

    @FormUrlEncoded
    @POST("admin/manajemendata/insertKelurahan")
    Call<KelurahanModel> insertKelurahan(
            @Field("kelurahan") String kelurahan,
            @Field("id_kecamatan") String idKecamtan
    );

    @FormUrlEncoded
    @POST("admin/manajemendata/deleteKelurahan")
    Call<KelurahanModel>deleteKelurahan(
            @Field("id_kelurahan") String idKelurahan
    );


    @FormUrlEncoded
    @POST("admin/manajemendata/updatekelurahan")
    Call<KelurahanModel>updateKelurahan(
            @Field("id_kelurahan") String idKelurahan,
            @Field("kelurahan") String kelurahan,
            @Field("id_kecamatan") String idKecamatan
    );

}
