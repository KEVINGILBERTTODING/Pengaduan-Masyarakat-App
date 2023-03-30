package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
