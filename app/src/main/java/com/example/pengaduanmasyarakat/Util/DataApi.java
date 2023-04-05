package com.example.pengaduanmasyarakat.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataApi {
    public static final String IP = ""; // ISI IP ADDRESS DISINI

    public static final String BASE_URL ="http://" + IP + "/pengaduan/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit;
    }


}
