package com.example.antrianklaim.AntrianTdyAdmin;

public interface EditorAntrianView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
