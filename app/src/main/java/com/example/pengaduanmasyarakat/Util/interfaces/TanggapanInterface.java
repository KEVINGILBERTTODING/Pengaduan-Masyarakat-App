package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.TanggapanModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TanggapanInterface{

        @GET("user/tanggapan/gettanggapanbyid")
        Call<List<TanggapanModel>> getTanggapanById(
                @Query("id_pengaduan") String idPengaduan
        );
}
