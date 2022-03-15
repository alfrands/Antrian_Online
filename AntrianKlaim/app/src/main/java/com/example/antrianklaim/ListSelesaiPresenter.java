package com.example.antrianklaim;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;
import com.example.antrianklaim.AntrianTdyAdmin.AntrianTdyModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class ListSelesaiPresenter {
    private ListSelesaiView view;
    public ListSelesaiPresenter (ListSelesaiView view) {this.view = view;}
    void getDataSelesai(){
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ModelListSelesai>> call = apiInterface.getdataselesai();
        call.enqueue(new Callback<List<ModelListSelesai>>() {
            @Override
            public void onResponse(Call<List<ModelListSelesai>> call, Response<List<ModelListSelesai>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() !=null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelListSelesai>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    void getDataAbsen(){
        view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<ModelListSelesai>> call = apiInterface.getdataabsen();
        call.enqueue(new Callback<List<ModelListSelesai>>() {
            @Override
            public void onResponse(Call<List<ModelListSelesai>> call, Response<List<ModelListSelesai>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() !=null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelListSelesai>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

}
