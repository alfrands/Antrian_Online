package com.example.antrianklaim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelListSelesai {
    @Expose
    @SerializedName("no_antrian") private String no_antrian;
    @Expose
    @SerializedName("nama_lengkap") private String nama_lengkap;
    @Expose
    @SerializedName("no_handphone") private String no_handphone;
    @Expose
    @SerializedName("status_antrian") private String status_antrian;

    public String getNo_antrian() {
        return no_antrian;
    }

    public void setNo_antrian(String no_antrian) {
        this.no_antrian = no_antrian;
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

    public String getStatus_antrian() {
        return status_antrian;
    }

    public void setStatus_antrian(String status_antrian) {
        this.status_antrian = status_antrian;
    }
}
