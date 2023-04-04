package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.PengaduanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AdminPengaduanInterface {


    @GET("admin/pengaduan/getAllPengaduanByStatus")
    Call<List<PengaduanModel>> getAllPengaduanByStatus(
            @Query("status") String status
    );

    @FormUrlEncoded
    @POST("admin/pengaduan/deletepengaduan")
    Call<PengaduanModel> deletePengaduan(
            @Field("id_pengaduan") String idPengaduan
    );
}
