package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.AdminUserModel;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserInterface extends Serializable {

    @GET("admin/manajemendata/getalluser")
    Call<List<AdminUserModel>>getAllUser(
            @Query("id_user") String idUser
    );


    @FormUrlEncoded
    @POST("admin/manajemendata/deleteuserbyuserid")
    Call<AdminUserModel>deleteUser(
            @Field("id_user") String idUser
    );

    @FormUrlEncoded
    @POST("admin/manajemendata/ubahProfileUser")
    Call<AdminUserModel>updateUser(
            @Field("id_user") String idUser,
            @Field("nama") String nama,
            @Field("no_telp") String noTelp
    );

    @GET("admin/manajemendata/getUserByUserId")
    Call<List<AdminUserModel>>getUserByUserId(
            @Query("id_user") String idUser
    );

    @FormUrlEncoded
    @POST("admin/manajemendata/insertuser")
    Call<AdminUserModel>inserUser(
            @Field("nama") String nama,
            @Field ("no_telepon") String noTelepn,
            @Field("username") String username,
            @Field("password") String password
    );
}
