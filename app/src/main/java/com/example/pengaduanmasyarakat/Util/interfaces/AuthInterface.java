package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.AdminUserModel;
import com.example.pengaduanmasyarakat.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    // get data user
    @GET("user/user/getUserById")
    Call<List<UserModel>> getUserById(
            @Query("user_id") String userId
    );



    @FormUrlEncoded
    @POST("user/user/insertsaran")
    Call<UserModel>insertSaran (
            @Field("nama") String nama,
            @Field("no_telepon") String noTelp,
            @Field("alamat") String alamat,
            @Field("saran") String saran
    );

    // login
    @FormUrlEncoded
    @POST("auth/loginadmin")
    Call<AdminUserModel>loginAdmin(
            @Field("username") String username,
            @Field("password") String password
    );


    //    Ubah profile
    @FormUrlEncoded
    @POST("auth/updateProfileAdmin")
    Call<AdminUserModel> updateAdminProfile(
            @Field("nama") String namaLengkap,
            @Field("no_telp") String noTelp,
            @Field("id_user") String idUser
    );

    @FormUrlEncoded
    @POST("auth/updatePasswordAdmin")
    Call<AdminUserModel>updateAdminPasswod(
            @Field("password_old") String passwordOld,
            @Field("password_new") String passwordNew,
            @Field("id_user") String idUser
    );

    // get data user
    @GET("admin/user/getUserById")
    Call<List<AdminUserModel>> getAdminById(
            @Query("user_id") String userId
    );












}
