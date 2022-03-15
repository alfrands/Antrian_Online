package com.example.antrianklaim.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelRegister {
    @Expose
    @SerializedName("no_rekening") private String no_rekening;
    @Expose
    @SerializedName("nama_lengkap") private String nama_lengkap;
    @Expose
    @SerializedName("no_handphone") private String no_handphone;
    @Expose
    @SerializedName("username") private String username;
    @Expose
    @SerializedName("password") private String password;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
