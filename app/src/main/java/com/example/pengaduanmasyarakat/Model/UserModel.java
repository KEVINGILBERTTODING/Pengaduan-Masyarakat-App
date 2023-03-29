package com.example.pengaduanmasyarakat.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("id_masyarakat")
    String idMasyarakat;
    @SerializedName("nama")
    String namaLengkap;
    @SerializedName("username")
    String userName;
    @SerializedName("password")
    String password;
    @SerializedName("no_telepon")
    String noTelp;
    @SerializedName("alamat")
    String alamat;
    @SerializedName("nik")
    String nik;
    @SerializedName("status")
    String status;
    @SerializedName("message")
    String message;

    public UserModel(String idMasyarakat, String namaLengkap, String userName, String password, String noTelp, String alamat, String nik, String status, String message) {
        this.idMasyarakat = idMasyarakat;
        this.namaLengkap = namaLengkap;
        this.userName = userName;
        this.password = password;
        this.noTelp = noTelp;
        this.alamat = alamat;
        this.nik = nik;
        this.status = status;
        this.message = message;
    }

    public String getIdMasyarakat() {
        return idMasyarakat;
    }

    public void setIdMasyarakat(String idMasyarakat) {
        this.idMasyarakat = idMasyarakat;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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
