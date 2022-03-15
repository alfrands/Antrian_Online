package com.example.antrianklaim;
import java.util.List;

public interface ListSelesaiView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<ModelListSelesai> modelListSelesais);
    void onErrorLoading(String message);
}
