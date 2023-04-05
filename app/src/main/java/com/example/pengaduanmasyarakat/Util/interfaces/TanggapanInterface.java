package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.TanggapanModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface TanggapanInterface{

        @GET("user/tanggapan/gettanggapanbyid")
        Call<List<TanggapanModel>> getTanggapanById(
                @Query("id_pengaduan") String idPengaduan
        );

        @GET("admin/tanggapan/getTanggapanById")
        Call<List<TanggapanModel>> getTanggapan(
                @Query("id_pengaduan") String idPengaduan
        );

        @Multipart
        @POST("admin/tanggapan/insertTanggapan")
        Call<TanggapanModel>insertTanggapan(
                @PartMap Map<String, RequestBody>TextData,
                @Part MultipartBody.Part image
                );

        @FormUrlEncoded
        @POST("admin/tanggapan/deleteTanggapan")
        Call<TanggapanModel>deleteTanggapan(
                @Field("id_tanggapan") String idTanggapan,
                @Field("id_pengaduan") String idPengaduan,
                @Field("status_pengaduan") String statusPengaduan
        );

        @FormUrlEncoded
        @POST("admin/tanggapan/updatetanggapan2")
        Call<TanggapanModel>updateTanggapan2(
                @Field("isi_tanggapan") String isiTanggapan,
                @Field("id_user") String idUser,
                @Field("id_tanggapan") String idTanggapan
        );

        @Multipart
        @POST("admin/tanggapan/updatetanggapan1")
        Call<TanggapanModel>updateTanggapan1(
                @PartMap Map<String, RequestBody>textData,
                @Part MultipartBody.Part image
        );

}
