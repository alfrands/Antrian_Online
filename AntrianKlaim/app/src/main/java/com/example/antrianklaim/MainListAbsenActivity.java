package com.example.antrianklaim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainListAbsenActivity extends AppCompatActivity implements ListSelesaiView {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    ListAntrianSelesaiAdapter adapter;
    ListSelesaiPresenter presenter;
    ListAntrianSelesaiAdapter.ItemClickListener itemClickListener;
    List<ModelListSelesai> modelListSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_absen);
        recyclerView = findViewById(R.id.recycler_viewAntrianTdy);
        swipeRefresh = findViewById(R.id.swipe_refreshAntrianTdy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new ListSelesaiPresenter(this);
        presenter.getDataAbsen();
        swipeRefresh.setOnRefreshListener(() -> presenter.getDataAbsen());
        itemClickListener = (((view, position) -> {
            String no_antrian = modelListSelesai.get(position).getNo_antrian();
            String nama_lengkap = modelListSelesai.get(position).getNama_lengkap();
            String no_handphone = modelListSelesai.get(position).getNo_handphone();

            Toast.makeText(this, "Antrian " + no_antrian + " Tidak Hadir" , Toast.LENGTH_SHORT).show();

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