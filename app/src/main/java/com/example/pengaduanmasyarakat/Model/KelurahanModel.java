package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KelurahanModel implements Serializable {

    @SerializedName("id_kelurahan")
    String idKelurahan;
    @SerializedName("kelurahan")
    String kelurahan;
    @SerializedName("id_kecamatan")
    String idKecamatan;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public KelurahanModel(String idKelurahan, String kelurahan, String idKecamatan, String status, String message) {
        this.idKelurahan = idKelurahan;
        this.kelurahan = kelurahan;
        this.idKecamatan = idKecamatan;
        this.status = status;
        this.message = message;
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

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
