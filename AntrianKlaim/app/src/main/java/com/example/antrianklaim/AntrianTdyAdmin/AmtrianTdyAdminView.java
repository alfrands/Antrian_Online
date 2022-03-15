package com.example.antrianklaim.AntrianTdyAdmin;

import java.util.List;

public interface AmtrianTdyAdminView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<AntrianTdyModel> antrianTdyModels);
    void onErrorLoading(String message);
}
