package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface PengaduanInterface {


//    Get kecamatan
    @GET("user/pengaduan/getkecamatan")
    Call<List<KecamatanModel>>getKecamatan();


    // Get kelurahan
    @GET("user/pengaduan/getkelurahan")
    Call<List<KelurahanModel>>getKelurahan(
            @Query("id_kecamatan") String idKecamatan
    );

    @Multipart
    @POST("user/pengaduan/create")
    Call<PengaduanModel>createPengaduan(
            @PartMap Map<String, RequestBody> textData,
            @Part MultipartBody.Part image,
            @Part MultipartBody.Part image1,
            @Part MultipartBody.Part image2

            );

    @GET("user/pengaduan/getAllPengaduan")
    Call<List<PengaduanModel>> getAllPengaduan();

    // get pengaduan ku
    @GET("user/pengaduan/getPengaduanByUserId")
    Call<List<PengaduanModel>> getMyPengaduan(
            @Query("id_masyarakat") String idMasyarakat,
            @Query("jenis") String jenis
    );

    @FormUrlEncoded
    @POST("user/pengaduan/update")
    Call<PengaduanModel> updatePengaduan(

    @Field("isi_laporan") String isiLaporan,
    @Field("id_pengaduan") String idPengaduan,
    @Field("jenis") String jenisPengaduan,
    @Field("id_masyarakat") String idMasyarakat,
    @Field("id_kelurahan") String idKelurahan

    );


    @Multipart
    @POST("user/pengaduan/updateImage")
    Call<PengaduanModel>updateImage(
            @PartMap Map<String, RequestBody> textData,
            @Part MultipartBody.Part image

    );

    @GET("user/pengaduan/getPengaduanById")
    Call<List<PengaduanModel>>getPengaduanById(
            @Query("id_pengaduan") String idPengaduan
    );

    @GET("user/pengaduan/filterpengaduan")
    Call<List<PengaduanModel>> filterPengaduan(
            @Query("id_masyarakat") String idMasyarakat,
            @Query("jenis") String jenis,
            @Query("date_start") String dateStart,
            @Query("date_end") String dateEnd
    );

    @GET("admin/pengaduan/filterpengaduan")
    Call<List<PengaduanModel>> filterPengaduanAdmin(

            @Query("jenis") String jenis,
            @Query("date_start") String dateStart,
            @Query("date_end") String dateEnd
    );


}
