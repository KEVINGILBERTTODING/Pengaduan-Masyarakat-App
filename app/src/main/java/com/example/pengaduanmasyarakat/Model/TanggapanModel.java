package com.example.pengaduanmasyarakat.Model;

import com.example.pengaduanmasyarakat.Util.ServerApi;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TanggapanModel implements Serializable {
    @SerializedName("id_tanggapan")
    String idTanggapan;
    @SerializedName("isi_tanggapan")
    String isiTanggapan;
    @SerializedName("tgl_tanggapan")
    String tglTanggapan;
    @SerializedName("foto_tanggapan")
    String fotoTanggapan;
    @SerializedName("status_tanggapan")
    String statusTanggapan;
    @SerializedName("id_pengaduan")
    String idPengaduan;
    @SerializedName("username")
    String username;
    @SerializedName("status")
    String status;

    public TanggapanModel(String idTanggapan, String isiTanggapan, String tglTanggapan, String fotoTanggapan, String statusTanggapan, String idPengaduan, String username, String status) {
        this.idTanggapan = idTanggapan;
        this.isiTanggapan = isiTanggapan;
        this.tglTanggapan = tglTanggapan;
        this.fotoTanggapan = fotoTanggapan;
        this.statusTanggapan = statusTanggapan;
        this.idPengaduan = idPengaduan;
        this.username = username;
        this.status = status;
    }


    public String getIdTanggapan() {
        return idTanggapan;
    }

    public void setIdTanggapan(String idTanggapan) {
        this.idTanggapan = idTanggapan;
    }

    public String getIsiTanggapan() {
        return isiTanggapan;
    }

    public void setIsiTanggapan(String isiTanggapan) {
        this.isiTanggapan = isiTanggapan;
    }

    public String getTglTanggapan() {
        return tglTanggapan;
    }

    public void setTglTanggapan(String tglTanggapan) {
        this.tglTanggapan = tglTanggapan;
    }

    public String getFotoTanggapan() {
        return ServerApi.BASE_URL +"/assets/img/img_tanggapan/" + fotoTanggapan;
    }

    public void setFotoTanggapan(String fotoTanggapan) {
        this.fotoTanggapan = fotoTanggapan;
    }

    public String getStatusTanggapan() {
        return statusTanggapan;
    }

    public void setStatusTanggapan(String statusTanggapan) {
        this.statusTanggapan = statusTanggapan;
    }

    public String getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(String idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
