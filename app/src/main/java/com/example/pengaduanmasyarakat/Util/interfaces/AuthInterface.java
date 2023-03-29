package com.example.pengaduanmasyarakat.Util.interfaces;

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


    // login
    @FormUrlEncoded
    @POST("auth/login")
    Call<UserModel>login(
            @Field("username") String username,
            @Field("password") String password
    );

//    Ubah profile
    @FormUrlEncoded
    @POST("auth/updateProfile")
    Call<UserModel> updateProfile(
        @Field("nama") String namaLengkap,
        @Field("no_telp") String noTelp,
        @Field("alamat") String alamat,
        @Field("id_masyarakat") String id_masyarakat
    );

    @FormUrlEncoded
    @POST("auth/updatePassword")
    Call<UserModel>updatePasswod(
            @Field("password_old") String passwordOld,
            @Field("password_new") String passwordNew,
            @Field("id_masyarakat") String idMasyarakat
    );




}
