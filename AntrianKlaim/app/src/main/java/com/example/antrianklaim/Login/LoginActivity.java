package com.example.antrianklaim.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.antrianklaim.Dashboard.DashAdminActivity;
import com.example.antrianklaim.Dashboard.DashUserActivity;
import com.example.antrianklaim.LoadingDialog.LoadingDialog;
import com.example.antrianklaim.R;
import com.example.antrianklaim.Register.RegistUserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class LoginActivity extends AppCompatActivity {
    EditText etuser , etpass;
    Button btnRegist,btnLogin;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etuser = findViewById(R.id.etusernameLogin);
        etpass = findViewById(R.id.etpasswordLogin);
        btnRegist = findViewById(R.id.buttonRegist);
        btnLogin = findViewById(R.id.buttonLogin);
        checkBox = findViewById(R.id.cx_showpass);
        Intent intentRegist = new Intent(LoginActivity.this, RegistUserActivity.class);
        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked())
            {
                etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else
            {
                etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        btnRegist.setOnClickListener(v -> startActivity(intentRegist));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private void login(){
        LoadingDialog loadingDialog = new LoadingDialog(this);
        loadingDialog.startLoadingDialog();
        String username = etuser.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        Call<LoginResponse> call = ApiClient.getApiClient().create(ApiInterface.class).LoginUser(username,password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loadingDialog.dismissDialog();
                Intent intentDashUser = new Intent(LoginActivity.this, DashUserActivity.class);
                Intent intentDashAdmin = new Intent(LoginActivity.this, DashAdminActivity.class);
                if (response.code()==200)
                {
                    if(response.body().getStatus().equals("OK"))
                    {
                        if (response.body().getResultCode()==1)
                        {
                            String level_akses = response.body().getLevel_akses();
                            String nama = response.body().getNama();
                            String id_user = response.body().getId_user();
                            if(response.body().getLevel_akses().equals("Admin"))
                            {
                                intentDashAdmin.putExtra("id_user",id_user);
                                intentDashAdmin.putExtra("nama",nama);
                                intentDashAdmin.putExtra("level_akses",level_akses);
                                startActivity(intentDashAdmin);
                            }
                            else
                            {
                                intentDashUser.putExtra("id_user",id_user);
                                intentDashUser.putExtra("nama",nama);
                                intentDashUser.putExtra("level_akses",level_akses);
                                startActivity(intentDashUser);
                            }
                            finish();

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Pastikan Username dan Password anda benar!",Toast.LENGTH_LONG).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,"Pastikan Username dan Password anda benar!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Pastikan Username dan Password anda benar!",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}