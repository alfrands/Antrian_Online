package com.example.antrianklaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.antrianklaim.AntrianTdyAdmin.AntrianTdyAdminPresenter;
import com.example.antrianklaim.AntrianTdyAdmin.EditAntrianTdyActivity;

import java.util.List;

public class MainListSelesaiActivity extends AppCompatActivity implements ListSelesaiView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    ListAntrianSelesaiAdapter adapter;
    ListSelesaiPresenter presenter;
    ListAntrianSelesaiAdapter.ItemClickListener itemClickListener;
    List<ModelListSelesai> modelListSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_selesai);
        recyclerView = findViewById(R.id.recycler_viewAntrianTdy);
        swipeRefresh = findViewById(R.id.swipe_refreshAntrianTdy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new ListSelesaiPresenter(this);
        presenter.getDataSelesai();
        swipeRefresh.setOnRefreshListener(() -> presenter.getDataSelesai());
        itemClickListener = (((view, position) -> {
            String no_antrian = modelListSelesai.get(position).getNo_antrian();
            String nama_lengkap = modelListSelesai.get(position).getNama_lengkap();
            String no_handphone = modelListSelesai.get(position).getNo_handphone();

            Toast.makeText(this, "Antrian " + no_antrian + " Sudah Selesai" , Toast.LENGTH_SHORT).show();

        }));
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
    public void onGetResult(List<ModelListSelesai> modelListSelesais) {
        adapter = new ListAntrianSelesaiAdapter(this,modelListSelesais,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        modelListSelesai = modelListSelesais;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}