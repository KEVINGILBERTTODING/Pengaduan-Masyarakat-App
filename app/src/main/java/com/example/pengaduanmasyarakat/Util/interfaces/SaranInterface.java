package com.example.pengaduanmasyarakat.Util.interfaces;

import com.example.pengaduanmasyarakat.Model.SaranModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SaranInterface {
    @GET("admin/saran/getallsaran")
    Call<List<SaranModel>>getAllSaran();
}
