package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PengaduanModel implements Serializable {
    @SerializedName("id_pengaduan")
    private String idPengaduan;
    @SerializedName("id_masyarakat")
    private String idMasyarakat;
    @SerializedName("id_kelurahan")
    private String idKelurahan;
    @SerializedName("isi_laporan")
    private String isiLaporan;
    @SerializedName("jenis")
    private String jenis;
    @SerializedName("foto")
    private String foto;
    @SerializedName("foto1")
    private String foto1;
    @SerializedName("foto2")
    private String foto2;
    @SerializedName("status")
    private String status;
    @SerializedName("tgl_pengaduan")
    private String tglPengaduan;


    public PengaduanModel(String idPengaduan, String idMasyarakat, String idKelurahan, String isiLaporan, String jenis, String foto, String foto1, String foto2, String status, String tglPengaduan) {
        this.idPengaduan = idPengaduan;
        this.idMasyarakat = idMasyarakat;
        this.idKelurahan = idKelurahan;
        this.isiLaporan = isiLaporan;
        this.jenis = jenis;
        this.foto = foto;
        this.foto1 = foto1;
        this.foto2 = foto2;
        this.status = status;
        this.tglPengaduan = tglPengaduan;

    }

    public String getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(String idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public String getIdMasyarakat() {
        return idMasyarakat;
    }

    public void setIdMasyarakat(String idMasyarakat) {
        this.idMasyarakat = idMasyarakat;
    }

    public String getIdKelurahan() {
        return idKelurahan;
    }

    public void setIdKelurahan(String idKelurahan) {
        this.idKelurahan = idKelurahan;
    }

    public String getIsiLaporan() {
        return isiLaporan;
    }

    public void setIsiLaporan(String isiLaporan) {
        this.isiLaporan = isiLaporan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglPengaduan() {
        return tglPengaduan;
    }

    public void setTglPengaduan(String tglPengaduan) {
        this.tglPengaduan = tglPengaduan;
    }
}
