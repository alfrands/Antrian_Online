package com.example.antrianklaim.UbasPass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
public class UbahPassActivity extends AppCompatActivity {
    EditText etiduser, etnewpass, etpass;
    Button btnSimpan, btnBatal;
    CheckBox checkBox;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pass);
        etiduser = findViewById(R.id.etIdUser);
        etnewpass = findViewById(R.id.etNewPass);
        etpass = findViewById(R.id.etpasswordPass);
        btnSimpan = findViewById(R.id.buttonUbahPass);
        btnBatal = findViewById(R.id.buttonBatalPass);
        checkBox = findViewById(R.id.cx_showpass);
        String id_user = getIntent().getStringExtra("id_user");
        btnBatal.setOnClickListener(v -> finish());
        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked())
            {
                etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                etnewpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else
            {
                etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                etnewpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etnewpass.getText().toString().trim();
                if (!etnewpass.getText().toString().trim().equals(etpass.getText().toString().trim())){
                    Toast.makeText(UbahPassActivity.this, "Pastikan Password Konfirmasi Sama!", Toast.LENGTH_SHORT).show();
                }else{
                    ubahPass(id_user,password);
                    finish();
                }
            }
        });
    }
    private void ubahPass(final String id_user, final String password){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<UbahPassModel> call = apiInterface.UbahPassword(id_user, password);
        call.enqueue(new Callback<UbahPassModel>() {
            @Override
            public void onResponse(Call<UbahPassModel> call, Response<UbahPassModel> response) {
                loadingDialog.dismissDialog();
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = response.body().getSuccess();
                    if (success) {
                        String pesan = response.body().getMessage();
                        Toast.makeText(UbahPassActivity.this,pesan,Toast.LENGTH_SHORT).show();
                    } else {
                        String pesan = response.body().getMessage();
                        Toast.makeText(UbahPassActivity.this,pesan,Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UbahPassModel> call, Throwable t) {
                Toast.makeText(UbahPassActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}