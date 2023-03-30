package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KelurahanModel implements Serializable {

    @SerializedName("id_kelurahan")
    String idKelurahan;
    @SerializedName("kelurahan")
    String kelurahan;

    public KelurahanModel(String idKelurahan, String kelurahan) {
        this.idKelurahan = idKelurahan;
        this.kelurahan = kelurahan;
    }

    public String getIdKelurahan() {
        return idKelurahan;
    }

    public void setIdKelurahan(String idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }
}
