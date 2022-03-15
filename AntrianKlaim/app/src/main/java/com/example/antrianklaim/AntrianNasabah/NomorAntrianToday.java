package com.example.antrianklaim.AntrianNasabah;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NomorAntrianToday {
    @Expose
    @SerializedName("no_antrian") private String no_antrian;
    @Expose
    @SerializedName("no_antrianserv") private String no_antrianserv;
    @Expose
    @SerializedName("waktu") private String waktu;

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNo_antrian() {
        return no_antrian;
    }

    public void setNo_antrian(String no_antrian) {
        this.no_antrian = no_antrian;
    }

    public String getNo_antrianserv() {
        return no_antrianserv;
    }

    public void setNo_antrianserv(String no_antrianserv) {
        this.no_antrianserv = no_antrianserv;
    }
}
