package com.example.antrianklaim.AntrianNasabah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetailNasabah {
    @Expose
    @SerializedName("id_nasabah") private String id_nasabah;
    @Expose
    @SerializedName("no_rekening") private String no_rekening;
    @Expose
    @SerializedName("nama_lengkap") private String nama_lengkap;
    @Expose
    @SerializedName("no_handphone") private String no_handphone;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getId_nasabah() {
        return id_nasabah;
    }

    public void setId_nasabah(String id_nasabah) {
        this.id_nasabah = id_nasabah;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(String no_rekening) {
        this.no_rekening = no_rekening;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNo_handphone() {
        return no_handphone;
    }

    public void setNo_handphone(String no_handphone) {
        this.no_handphone = no_handphone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
