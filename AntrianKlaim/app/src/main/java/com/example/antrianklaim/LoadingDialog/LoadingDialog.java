package com.example.antrianklaim.LoadingDialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.example.antrianklaim.R;

public class LoadingDialog {
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    private static AlertDialog alertDialog;

    public LoadingDialog(Activity myActivity)
    {
        activity = myActivity;
    }
    @SuppressLint("InflateParams")
    public void startLoadingDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void dismissDialog()
    {
        alertDialog.dismiss();
    }
}
