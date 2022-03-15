package com.example.antrianklaim.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antrianklaim.Login.LoginActivity;
import com.example.antrianklaim.AntrianTdyAdmin.MainAntrianTdyActivity;
import com.example.antrianklaim.MainListAbsenActivity;
import com.example.antrianklaim.MainListSelesaiActivity;
import com.example.antrianklaim.R;
import com.example.antrianklaim.UbasPass.UbahPassActivity;

public class DashAdminActivity extends AppCompatActivity {
    TextView tvid , tvnama;
    CardView cd_antrianTdy,cdLogout,cdUbahPass,cdlistSelesai,cdlistTidak;
    Dialog dialog;
    Button btnKonfirmOk, btnKonfirmCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_admin);
        String id_user = getIntent().getStringExtra("id_user");
        String nama = getIntent().getStringExtra("nama");
        tvid = findViewById(R.id.txtIdUserDashAdmin);
        tvnama = findViewById(R.id.txtNamaDashAdmin);
        cd_antrianTdy = findViewById(R.id.card_DAAntrianTdy);
        cdUbahPass= findViewById(R.id.card_DAUbahPass);
        cdLogout = findViewById(R.id.card_DALogOut);
        cdlistSelesai = findViewById(R.id.card_DAAntrianSelesai);
        cdlistTidak = findViewById(R.id.card_DAAntrianTdk);
        tvid.setText(id_user);
        tvnama.setText(nama);
        setKonfirmasi();
        btnKonfirmCancel.setOnClickListener(v -> {
            dialog.hide();
        });
        Intent intentAntrianTdy = new Intent(DashAdminActivity.this, MainAntrianTdyActivity.class);
        Intent intentUbahPass = new Intent(DashAdminActivity.this, UbahPassActivity.class);
        Intent intentListSelesai = new Intent(DashAdminActivity.this, MainListSelesaiActivity.class);
        Intent intentListAbsen = new Intent(DashAdminActivity.this, MainListAbsenActivity.class);

        cdlistTidak.setOnClickListener(v -> startActivity(intentListAbsen));

        cdlistSelesai.setOnClickListener(v -> startActivity(intentListSelesai));

        cd_antrianTdy.setOnClickListener(v -> {
            intentAntrianTdy.putExtra("id_user",id_user);
            startActivity(intentAntrianTdy);
        });
        cdUbahPass.setOnClickListener(v -> {
            intentUbahPass.putExtra("id_user",id_user);
            startActivity(intentUbahPass);
        });
        cdLogout.setOnClickListener(v -> dialog.show());
        btnKonfirmOk.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("credentials",MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.apply();
            Toast.makeText(DashAdminActivity.this, "Anda Berhasil Logout", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setKonfirmasi(){
        // CUSTOM DIALOG //
        dialog = new Dialog(DashAdminActivity.this);
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