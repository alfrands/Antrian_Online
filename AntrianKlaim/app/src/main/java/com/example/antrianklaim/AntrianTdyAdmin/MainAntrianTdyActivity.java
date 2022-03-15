package com.example.antrianklaim.AntrianTdyAdmin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.antrianklaim.R;

import java.util.List;

public class MainAntrianTdyActivity extends AppCompatActivity implements AmtrianTdyAdminView{
    private static final int INTENT_EDITANTRIAN = 100 ;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    AntrianTdyAdminPresenter presenter;
    AntrianTdyAdminAdapter adapter;
    AntrianTdyAdminAdapter.ItemClickListener itemClickListener;
    List<AntrianTdyModel> antrianTdyModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_antrian_tdy);
        recyclerView = findViewById(R.id.recycler_viewAntrianTdy);
        swipeRefresh = findViewById(R.id.swipe_refreshAntrianTdy);
        String id_user = getIntent().getStringExtra("id_user");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new AntrianTdyAdminPresenter(this);
        presenter.getDataAntrian();
        swipeRefresh.setOnRefreshListener(() -> presenter.getDataAntrian());
        itemClickListener = (((view, position) -> {
            String id = antrianTdyModel.get(position).getId();
            String id_nasabah = antrianTdyModel.get(position).getId_nasabah();
            String no_antrian = antrianTdyModel.get(position).getNo_antrian();
            String nama_lengkap = antrianTdyModel.get(position).getNama_lengkap();
            String no_handphone = antrianTdyModel.get(position).getNo_handphone();
            String status_antrian = antrianTdyModel.get(position).getStatus_antrian();

            Intent intent = new Intent(this, EditAntrianTdyActivity.class);
            intent.putExtra("id_user", id_user);
            intent.putExtra("id", id);
            intent.putExtra("id_nasabah", id_nasabah);
            intent.putExtra("no_antrian", no_antrian);
            intent.putExtra("nama_lengkap", nama_lengkap);
            intent.putExtra("no_handphone", no_handphone);

            startActivityForResult(intent,INTENT_EDITANTRIAN);
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INTENT_EDITANTRIAN && resultCode== RESULT_OK){
            presenter.getDataAntrian();
        } else {
            presenter.getDataAntrian();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<AntrianTdyModel> antrianTdyModels) {
        adapter = new AntrianTdyAdminAdapter(this,antrianTdyModels,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        antrianTdyModel = antrianTdyModels;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}