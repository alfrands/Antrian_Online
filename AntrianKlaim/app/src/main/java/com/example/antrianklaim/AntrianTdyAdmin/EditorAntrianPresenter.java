package com.example.antrianklaim.AntrianTdyAdmin;

import com.example.antrianklaim.API.ApiClient;
import com.example.antrianklaim.API.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class EditorAntrianPresenter {
    private EditorAntrianView view;
    ApiInterface apiInterface;
    public EditorAntrianPresenter(EditorAntrianView view){this.view = view;}

    void panggil_antri(final String id, final String id_user){
        view.showProgress();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AntrianTdyModel> call = apiInterface.PanggilAntrian(id, id_user);
        call.enqueue(new Callback<AntrianTdyModel>() {
            @Override
            public void onResponse(Call<AntrianTdyModel> call, Response<AntrianTdyModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().isSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<AntrianTdyModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
    void proses_antri(final String id, final String id_user){
        view.showProgress();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AntrianTdyModel> call = apiInterface.ProsesAntrian(id, id_user);
        call.enqueue(new Callback<AntrianTdyModel>() {
            @Override
            public void onResponse(Call<AntrianTdyModel> call, Response<AntrianTdyModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().isSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AntrianTdyModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
    void selesai_antri(final String id , final String id_user){
        view.showProgress();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AntrianTdyModel> call = apiInterface.SelesaiAntrian(id, id_user);
        call.enqueue(new Callback<AntrianTdyModel>() {
            @Override
            public void onResponse(Call<AntrianTdyModel> call, Response<AntrianTdyModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().isSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AntrianTdyModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
    void tidak_hadir_antrian(final String id, final String id_user){
        view.showProgress();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AntrianTdyModel> call = apiInterface.TidakHadirAntrian(id, id_user);
        call.enqueue(new Callback<AntrianTdyModel>() {
            @Override
            public void onResponse(Call<AntrianTdyModel> call, Response<AntrianTdyModel> response) {
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().isSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AntrianTdyModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
}
