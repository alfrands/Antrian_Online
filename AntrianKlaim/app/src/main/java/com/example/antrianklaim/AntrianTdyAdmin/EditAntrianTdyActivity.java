package com.example.antrianklaim.AntrianTdyAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antrianklaim.LoadingDialog.LoadingDialog;
import com.example.antrianklaim.R;

public class EditAntrianTdyActivity extends AppCompatActivity implements EditorAntrianView {
Button btnpanggil, btnproses, btnselesai, btntdk, btnkembali;
EditText etid, etidnasabah, etnoantri,etnamalengkap, etnohp, etiduser;
String id , id_nasabah, no_antrian, nama_lengkap, no_handphone , id_user;
EditorAntrianPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_antrian_tdy);
        btnpanggil = findViewById(R.id.btnPanggilAntrian);
        btnproses = findViewById(R.id.btnProsesAntrian);
        btnselesai = findViewById(R.id.btnSelesaiAntrian);
        btntdk = findViewById(R.id.btnTidakHadir);
        btnkembali = findViewById(R.id.btnKembali);
        etid = findViewById(R.id.etIdAntrian);
        etidnasabah = findViewById(R.id.etIdNasabah);
        etnoantri = findViewById(R.id.etNoAntri);
        etnamalengkap = findViewById(R.id.etNamaLengkap);
        etnohp = findViewById(R.id.etNoHp);
        etiduser = findViewById(R.id.etIdUser);
        presenter = new EditorAntrianPresenter(this);

        btnkembali.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        id_nasabah = intent.getStringExtra("id_nasabah");
        no_antrian = intent.getStringExtra("no_antrian");
        nama_lengkap = intent.getStringExtra("nama_lengkap");
        no_handphone = intent.getStringExtra("no_handphone");
        id_user = intent.getStringExtra("id_user");
        setDataFromIntentExtra();
        btnpanggil.setOnClickListener(v -> {
            if (id.isEmpty()){
                Toast.makeText(EditAntrianTdyActivity.this, "Something Wrong This Action", Toast.LENGTH_SHORT).show();
            } else{
                presenter.panggil_antri(id,id_user);
            }
        });
        btnproses.setOnClickListener(v -> {
            if (id.isEmpty()){
                Toast.makeText(EditAntrianTdyActivity.this, "Something Wrong This Action", Toast.LENGTH_SHORT).show();
            } else{
                presenter.proses_antri(id,id_user);
            }
        });
        btnselesai.setOnClickListener(v -> {
            if (id.isEmpty()){
                Toast.makeText(EditAntrianTdyActivity.this, "Something Wrong This Action", Toast.LENGTH_SHORT).show();
            } else{
                presenter.selesai_antri(id,id_user);
            }
        });
        btntdk.setOnClickListener(v -> {
            if (id.isEmpty()){
                Toast.makeText(EditAntrianTdyActivity.this, "Something Wrong This Action", Toast.LENGTH_SHORT).show();
            } else{
                presenter.tidak_hadir_antrian(id,id_user);
            }
        });
    }

    private void setDataFromIntentExtra() {
        if (id != null){
            etid.setText(id);
            etidnasabah.setText(id_nasabah);
            etnoantri.setText(no_antrian);
            etnamalengkap.setText(nama_lengkap);
            etnohp.setText(no_handphone);
            etiduser.setText(id_user);
        }
    }

    @Override
    public void showProgress() {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
    }

    @Override
    public void hideProgress() {
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.dismissDialog();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditAntrianTdyActivity.this,message,Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditAntrianTdyActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}