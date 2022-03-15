package com.example.antrianklaim.UbasPass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UbahPassModel {
    @Expose
    @SerializedName("id_user") private String id_user;
    @Expose
    @SerializedName("password") private String password;
    @Expose
    @SerializedName("success") private Boolean success;
    @Expose
    @SerializedName("message") private String message;

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
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
