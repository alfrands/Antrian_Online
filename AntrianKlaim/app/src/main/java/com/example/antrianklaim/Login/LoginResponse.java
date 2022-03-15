package com.example.antrianklaim.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @Expose
    @SerializedName("status") private String status;
    @Expose
    @SerializedName("result_code") private int resultCode;
    @Expose
    @SerializedName("id_user") private String id_user;
    @Expose
    @SerializedName("nama") private String nama;
    @Expose
    @SerializedName("level_akses") private String level_akses;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLevel_akses() {
        return level_akses;
    }

    public void setLevel_akses(String level_akses) {
        this.level_akses = level_akses;
    }
}
