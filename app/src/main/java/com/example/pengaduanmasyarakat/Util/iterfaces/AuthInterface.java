package com.example.pengaduanmasyarakat.Util.iterfaces;

import com.example.pengaduanmasyarakat.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthInterface

{


    // Registrasi
    @FormUrlEncoded
    @POST("auth/register")
    Call<UserModel> register (
            @Field("nama_lengkap") String namaLengkap,
            @Field("username") String username,
            @Field("password") String password,
            @Field("no_telp") String noTelp,
            @Field("nik") String nik,
            @Field("alamat") String alamat

    );




}
