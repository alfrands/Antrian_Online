package com.example.antrianklaim.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antrianklaim.AntrianNasabah.AntrianTodayActivity;
import com.example.antrianklaim.Login.LoginActivity;
import com.example.antrianklaim.MenuNasabah.PersyaratanActivity;
import com.example.antrianklaim.R;
import com.example.antrianklaim.MenuNasabah.TentangAppActivity;
import com.example.antrianklaim.UbasPass.UbahPassActivity;

public class DashUserActivity extends AppCompatActivity {
    TextView tvid , tvnama;
    CardView cdAntrian,cdPersyaratan,cdTentang,cdLogout,cdUbahPass;
    Dialog dialog;
    Button btnKonfirmOk, btnKonfirmCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);
        String id_user = getIntent().getStringExtra("id_user");
        String nama = getIntent().getStringExtra("nama");
        tvid = findViewById(R.id.txtIdUserDashUser);
        tvnama = findViewById(R.id.txtNamaDashUser);
        tvid.setText(id_user);
        tvnama.setText(nama);
        setKonfirmasi();
        btnKonfirmCancel.setOnClickListener(v -> {
            dialog.hide();
        });
        cdAntrian = findViewById(R.id.card_DUAntrianToday);
        cdPersyaratan = findViewById(R.id.card_DUPersyaratan);
        cdTentang = findViewById(R.id.card_DUTentangKami);
        cdUbahPass= findViewById(R.id.card_DUUbahPass);
        cdLogout = findViewById(R.id.card_DULogOut);

        Intent intentAntrian = new Intent(DashUserActivity.this, AntrianTodayActivity.class);
        Intent intentPersyaratan = new Intent(DashUserActivity.this, PersyaratanActivity.class);
        Intent intentTentang = new Intent(DashUserActivity.this, TentangAppActivity.class);
        Intent intentUbahPass = new Intent(DashUserActivity.this, UbahPassActivity.class);

        cdAntrian.setOnClickListener(v -> {
            intentAntrian.putExtra("id_nasabah",id_user);
            startActivity(intentAntrian);
        });
        cdPersyaratan.setOnClickListener(v -> startActivity(intentPersyaratan));
        cdTentang.setOnClickListener(v -> startActivity(intentTentang));
        cdLogout.setOnClickListener(v -> dialog.show());
        btnKonfirmOk.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.apply();
            Toast.makeText(DashUserActivity.this, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
        cdUbahPass.setOnClickListener(v -> {
            intentUbahPass.putExtra("id_user",id_user);
            startActivity(intentUbahPass);
        });

    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setKonfirmasi(){
        // CUSTOM DIALOG //
        dialog = new Dialog(DashUserActivity.this);
        dialog.setContentView(R.layout.custom_dialog_logout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        btnKonfirmCancel = dialog.findViewById(R.id.btnCancelDialog);
        btnKonfirmOk = dialog.findViewById(R.id.btnOkDialog);
    }
}