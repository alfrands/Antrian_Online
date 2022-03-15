package com.example.antrianklaim.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;
import com.example.antrianklaim.LoadingDialog.LoadingDialog;
import com.example.antrianklaim.Login.LoginActivity;
import com.example.antrianklaim.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistUserActivity extends AppCompatActivity {
    EditText etnorek, etnama, etnohp, etusername, etpass;
    Button btnregist, btncancel;
    CheckBox checkBox;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_user);
        etnorek = findViewById(R.id.etNoRek);
        etnama = findViewById(R.id.etNamaLengkap);
        etnohp = findViewById(R.id.etNoHp);
        etusername = findViewById(R.id.etUsername);
        etpass = findViewById(R.id.etPassword);
        checkBox = findViewById(R.id.cx_showpass);
        btnregist = findViewById(R.id.btnBuatAkun);
        btncancel = findViewById(R.id.btnBatalBuatAkun);
        Intent intentLogin = new Intent(RegistUserActivity.this, LoginActivity.class);


        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked())
            {
                etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else
            {
                etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        btncancel.setOnClickListener(v -> {
            startActivity(intentLogin);
            finish();
        });
        btnregist.setOnClickListener(v -> {
            String no_rekening = etnorek.getText().toString().trim();
            String nama_lengkap = etnama.getText().toString().trim();
            String no_handphone = etnohp.getText().toString().trim();
            String username = etusername.getText().toString().trim();
            String password = etpass.getText().toString().trim();
            if(no_rekening.isEmpty() && nama_lengkap.isEmpty() && no_handphone.isEmpty() && username.isEmpty() && password.isEmpty())
            {
                Toast.makeText(RegistUserActivity.this, "Harap Lengkapi Semua Data Anda", Toast.LENGTH_SHORT).show();
            }else{
                register_nasabah(no_rekening,nama_lengkap,no_handphone,username,password);
                startActivity(intentLogin);
                finish();
            }
        });
    }
   private void register_nasabah(final String no_rekening, final String nama_lengkap, final String no_handphone, final String username , final String password){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ModelRegister> call = apiInterface.Register(no_rekening,nama_lengkap,no_handphone,username,password);
        call.enqueue(new Callback<ModelRegister>() {
            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<ModelRegister>call, Response<ModelRegister> response) {
                loadingDialog.dismissDialog();
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = response.body().getSuccess();
                    if (success) {
                        String pesan = response.body().getMessage();
                        Toast.makeText(RegistUserActivity.this,pesan,Toast.LENGTH_SHORT).show();
                    } else {
                        String pesan = response.body().getMessage();
                        Toast.makeText(RegistUserActivity.this,pesan,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<ModelRegister> call, Throwable t) {
                Toast.makeText(RegistUserActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}