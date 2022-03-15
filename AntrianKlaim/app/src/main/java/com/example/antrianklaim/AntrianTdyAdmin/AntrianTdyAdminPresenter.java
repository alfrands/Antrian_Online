package com.example.antrianklaim.AntrianTdyAdmin;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class AntrianTdyAdminPresenter {
    private AmtrianTdyAdminView view;
    public AntrianTdyAdminPresenter(AmtrianTdyAdminView view){this.view= view;}
    void getDataAntrian(){
    view.showLoading();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<AntrianTdyModel>> call = apiInterface.getAntrians();
        call.enqueue(new Callback<List<AntrianTdyModel>>() {
            @Override
            public void onResponse(Call<List<AntrianTdyModel>> call, Response<List<AntrianTdyModel>> response) {
            view.hideLoading();
            if (response.isSuccessful() && response.body() !=null){
                view.onGetResult(response.body());
            }
            }

            @Override
            public void onFailure(Call<List<AntrianTdyModel>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
