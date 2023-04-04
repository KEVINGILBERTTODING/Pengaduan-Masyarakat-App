package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KecamatanModel implements Serializable {

    @SerializedName("id_kecamatan")
    String idKecamtan;
    @SerializedName("kecamatan")
    String kecamatan;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public KecamatanModel(String idKecamtan, String kecamatan, String status, String message) {
        this.idKecamtan = idKecamtan;
        this.kecamatan = kecamatan;
        this.status = status;
        this.message = message;
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
