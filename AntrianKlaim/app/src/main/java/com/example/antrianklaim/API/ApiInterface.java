package com.example.antrianklaim.API;

import com.example.antrianklaim.AntrianTdyAdmin.AntrianTdyModel;
import com.example.antrianklaim.Login.LoginResponse;
import com.example.antrianklaim.AntrianNasabah.ModelDetailNasabah;
import com.example.antrianklaim.AntrianNasabah.NomorAntrianNasabah;
import com.example.antrianklaim.AntrianNasabah.NomorAntrianToday;
import com.example.antrianklaim.ModelListSelesai;
import com.example.antrianklaim.Register.ModelRegister;
import com.example.antrianklaim.UbasPass.UbahPassModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Login.php")
    Call<LoginResponse>LoginUser(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("Register.php")
    Call<ModelRegister> Register(
            @Field("no_rekening") String no_rekening,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_handphone") String no_handphone,
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("TampilDetailNasabah.php")
    Call<ModelDetailNasabah>DetailNasabah(
            @Field("id_nasabah") String id_nasabah
    );
    @FormUrlEncoded
    @POST("SimpanAntrian.php")
    Call<ModelDetailNasabah> SimpanAntrian(
            @Field("id_nasabah") String id_nasabah,
            @Field("no_rekening") String no_rekening,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("no_handphone") String no_handphone
    );
    @GET("TampilAntrianToday.php")
    Call<NomorAntrianToday>getAntrianToday();
    @GET("TampilAntrianDilayani.php")
    Call<NomorAntrianToday>getAntrianServ();
    @FormUrlEncoded
    @POST("TampilAntrianNasabah.php")
    Call<NomorAntrianNasabah>getAntrianNasabah(
            @Field("id_nasabah") String id_nasabah
    );

    @GET("ListAntrianTdy.php")
    Call<List<AntrianTdyModel>> getAntrians();
    @FormUrlEncoded
    @POST("PanggilAntrian.php")
    Call<AntrianTdyModel> PanggilAntrian(
            @Field("id") String id,
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("ProsesAntrian.php")
    Call<AntrianTdyModel> ProsesAntrian(
            @Field("id") String id,
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("SelesaiAntrian.php")
    Call<AntrianTdyModel> SelesaiAntrian(
            @Field("id") String id,
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("TidakHadirAntrian.php")
    Call<AntrianTdyModel> TidakHadirAntrian(
            @Field("id") String id,
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("UbahPassword.php")
    Call<UbahPassModel> UbahPassword(
            @Field("id_user") String id_user,
            @Field("password") String password
    );
    @GET("ListAntrianSelesai.php")
    Call<List<ModelListSelesai>> getdataselesai();
    @GET("ListAntrianTidakHadir.php")
    Call<List<ModelListSelesai>> getdataabsen();
}
