package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SaranModel implements Serializable {
    @SerializedName("id_saran")
    String idSaran;
    @SerializedName("nama")
    String namaa;
    @SerializedName("no_telepon")
    String noTelp;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("saran")
    String saran;
    @SerializedName("tgl_saran")
    String tglSaran;

    public SaranModel(String idSaran, String namaa, String noTelp, String alamat, String saran, String tglSaran) {
        this.idSaran = idSaran;
        this.namaa = namaa;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.saran = saran;
        this.tglSaran = tglSaran;
    }

    public String getIdSaran() {
        return idSaran;
    }

    public void setIdSaran(String idSaran) {
        this.idSaran = idSaran;
    }

    public String getNamaa() {
        return namaa;
    }

    public void setNamaa(String namaa) {
        this.namaa = namaa;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSaran() {
        return saran;
    }

    public void setSaran(String saran) {
        this.saran = saran;
    }

    public String getTglSaran() {
        return tglSaran;
    }

    public void setTglSaran(String tglSaran) {
        this.tglSaran = tglSaran;
    }
}
