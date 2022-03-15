package com.example.antrianklaim.AntrianNasabah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NomorAntrianNasabah {
    @Expose
    @SerializedName("no_antrian") private String no_antrian;
    @Expose
    @SerializedName("status_antrian") private String status_antrian;

    public String getNo_antrian() {
        return no_antrian;
    }

    public void setNo_antrian(String no_antrian) {
        this.no_antrian = no_antrian;
    }

    public String getStatus_antrian() {
        return status_antrian;
    }

    public void setStatus_antrian(String status_antrian) {
        this.status_antrian = status_antrian;
    }
}
