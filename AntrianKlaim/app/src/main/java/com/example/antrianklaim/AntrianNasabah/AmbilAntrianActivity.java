package com.example.antrianklaim.AntrianNasabah;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;
import com.example.antrianklaim.LoadingDialog.LoadingDialog;
import com.example.antrianklaim.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class AmbilAntrianActivity extends AppCompatActivity {
    String id_nasabah,no_rekening,nama,no_hp,pesan;
    EditText etnorek , etnama , etnohp;
    Button btnAmbil , btnCancel, btnKonfirmOk, btnKonfirmCancel;
    Dialog dialog;

    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambil_antrian);
        etnorek = findViewById(R.id.etNoRekAmbil);
        etnama = findViewById(R.id.etNamaLengkapAmbil);
        etnohp = findViewById(R.id.etNoHpAmbil);
        btnAmbil = findViewById(R.id.btnAmbilAntrian);
        btnCancel = findViewById(R.id.btnBatalAmbilAntrian);
        id_nasabah = getIntent().getStringExtra("id_nasabah");
        DetailNasabah(id_nasabah);
        setKonfirmasi();
        btnKonfirmCancel.setOnClickListener(v -> {
            dialog.hide();
            finish();
        });
        btnCancel.setOnClickListener(v -> finish());
        btnAmbil.setOnClickListener(v -> {
            dialog.show();
        });
        btnKonfirmOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            simpanAntrian(id_nasabah,no_rekening,nama,no_hp);
            }
        });

    }
    private void DetailNasabah(final String id_nasabah){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ModelDetailNasabah> call = apiInterface.DetailNasabah(id_nasabah);
        call.enqueue(new Callback<ModelDetailNasabah>() {
            @Override
            public void onResponse(Call<ModelDetailNasabah> call, Response<ModelDetailNasabah> response) {
                loadingDialog.dismissDialog();
                if (response.body() != null){
                    no_rekening = response.body().getNo_rekening();
                    nama = response.body().getNama_lengkap();
                    no_hp = response.body().getNo_handphone();
                    etnorek.setText(no_rekening);
                    etnama.setText(nama);
                    etnohp.setText(no_hp);
                }else{
                    pesan = response.body().getMessage();
                }
            }

            @Override
            public void onFailure(Call<ModelDetailNasabah> call, Throwable t) {
                Toast.makeText(AmbilAntrianActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void simpanAntrian(final String id_nasabah, final String no_rekening, final String nama_lengkap, final String no_handphone){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        dialog.hide();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ModelDetailNasabah> call = apiInterface.SimpanAntrian(id_nasabah, no_rekening, nama_lengkap, no_handphone);
        call.enqueue(new Callback<ModelDetailNasabah>() {
            @Override
            public void onResponse(Call<ModelDetailNasabah> call, Response<ModelDetailNasabah> response) {
                    loadingDialog.startLoadingDialog();
                if (response.isSuccessful() && response.body() != null){
                    loadingDialog.dismissDialog();
                    boolean success = response.body().getSuccess();
                    String pesan = response.body().getMessage();
                    if (success){
                        Toast.makeText(AmbilAntrianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AmbilAntrianActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDetailNasabah> call, Throwable t) {
                Toast.makeText(AmbilAntrianActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setKonfirmasi(){
        // CUSTOM DIALOG //
        dialog = new Dialog(AmbilAntrianActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnKonfirmCancel = dialog.findViewById(R.id.btnCancelDialog);
        btnKonfirmOk = dialog.findViewById(R.id.btnOkDialog);
    }
}