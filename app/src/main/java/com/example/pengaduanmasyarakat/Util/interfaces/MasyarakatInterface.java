package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MasyarakatInterface {

    @GET("admin/manajemendata/getAllMasyarakaat")
    Call<List<UserModel>>getAllMasyarakat();

    @FormUrlEncoded
    @POST("admin/manajemendata/deleteMasyarakatbyUserId")
    Call<UserModel>deleteMasyarakat(
            @Field("id_masyarakat") String idMasyarakat
    );

    @FormUrlEncoded
    @POST("admin/manajemendata/insertMasyarakat")
    Call<UserModel>insertMasyarakat(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("password") String password,
            @Field("no_telepon") String noTelepon,
            @Field("nik") String nik,
            @Field("alamat") String alamat
    );
}
