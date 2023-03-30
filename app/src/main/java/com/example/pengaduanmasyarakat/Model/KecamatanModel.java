package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KecamatanModel implements Serializable {

    @SerializedName("id_kecamatan")
    String idKecamtan;
    @SerializedName("kecamatan")
    String kecamatan;

    public KecamatanModel(String idKecamtan, String kecamatan) {
        this.idKecamtan = idKecamtan;
        this.kecamatan = kecamatan;
    }

    public String getIdKecamtan() {
        return idKecamtan;
    }

    public void setIdKecamtan(String idKecamtan) {
        this.idKecamtan = idKecamtan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }
}
