package com.example.antrianklaim.AntrianNasabah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;
import com.example.antrianklaim.LoadingDialog.LoadingDialog;
import com.example.antrianklaim.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class AntrianTodayActivity extends AppCompatActivity {
Button btnBuatantrian,btnrefresh;
TextView tvLast,tvserv,tvAntrianNas,tvStaNas, tvwaktu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian_today);
        btnrefresh = findViewById(R.id.btnRefresh);

        String id_nasabah = getIntent().getStringExtra("id_nasabah");
        btnBuatantrian = findViewById(R.id.btnBuatAntrian);
        tvAntrianNas = findViewById(R.id.antrianNasabah);
        tvStaNas = findViewById(R.id.statusNasabah);
        tvLast = findViewById(R.id.antrianLast);
        tvserv = findViewById(R.id.antrianServi);
        tvwaktu = findViewById(R.id.antrianServWaktu);

        antrianTerakhirToday();
        antrianServ();
        antrianNasabah(id_nasabah);
        btnrefresh.setOnClickListener(v -> {
            antrianTerakhirToday();
            antrianServ();
            antrianNasabah(id_nasabah);
        });

        btnBuatantrian.setOnClickListener(v -> {
            Intent intentAntrianUser = new Intent(AntrianTodayActivity.this, AmbilAntrianActivity.class);
            intentAntrianUser.putExtra("id_nasabah",id_nasabah);
            startActivity(intentAntrianUser);
        });
    }
    private void antrianTerakhirToday(){
        Call<NomorAntrianToday> call = ApiClient.getApiClient().create(ApiInterface.class).getAntrianToday();
        call.enqueue(new Callback<NomorAntrianToday>() {
            @Override
            public void onResponse(Call<NomorAntrianToday> call, Response<NomorAntrianToday> response) {
                    String nomor_antrianlast =response.body().getNo_antrian();
                    tvLast.setText(nomor_antrianlast);
            }

            @Override
            public void onFailure(Call<NomorAntrianToday> call, Throwable t) {

            }
        });
    }
    private void antrianServ(){
        Call<NomorAntrianToday> call = ApiClient.getApiClient().create(ApiInterface.class).getAntrianServ();
        call.enqueue(new Callback<NomorAntrianToday>() {
            @Override
            public void onResponse(Call<NomorAntrianToday> call, Response<NomorAntrianToday> response) {
                    String nomor_antrianserv = response.body().getNo_antrianserv();
                    String waktu = response.body().getWaktu();
                    if (waktu == null){
                        tvserv.setText(nomor_antrianserv);
                        tvwaktu.setText("JAM : " + "Not Set" + " WIB");
                    } else{
                        tvserv.setText(nomor_antrianserv);
                        tvwaktu.setText("JAM : " + waktu + " WIB");
                    }

            }

            @Override
            public void onFailure(Call<NomorAntrianToday> call, Throwable t) {

            }
        });
    }
    private void antrianNasabah(final String id_nasabah){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        Call<NomorAntrianNasabah> call = ApiClient.getApiClient().create(ApiInterface.class).getAntrianNasabah(id_nasabah);
        call.enqueue(new Callback<NomorAntrianNasabah>() {
            @Override
            public void onResponse(Call<NomorAntrianNasabah> call, Response<NomorAntrianNasabah> response) {
                loadingDialog.dismissDialog();
                    String no_antrianNas = response.body().getNo_antrian();
                    String statusNas = response.body().getStatus_antrian();
                    tvAntrianNas.setText(no_antrianNas);
                    tvStaNas.setText(statusNas);
            }

            @Override
            public void onFailure(Call<NomorAntrianNasabah> call, Throwable t) {

            }
        });

    }
}