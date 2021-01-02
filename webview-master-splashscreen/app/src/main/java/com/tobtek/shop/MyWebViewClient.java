package com.tobtek.shop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

class MyWebViewClient extends WebViewClient {

    private Activity activity;
    private FrameLayout progressBar;

    public MyWebViewClient(Activity activity, FrameLayout progressBar) {
        this.activity = activity;
        this.progressBar = progressBar;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
//        Toast.makeText(activity, "Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //Page load finished
        progressBar.setVisibility(View.GONE);
        super.onPageFinished(view, url);
//        Toast.makeText(activity, "finished", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        // TODO Auto-generated method stub
        super.onReceivedError(view, errorCode, description, failingUrl);
        progressBar.setVisibility(View.GONE);

        new AlertDialog.Builder(activity)
                .setTitle("Error :(")
                .setMessage("Something went wrong please restart the app or check the internet connection")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
//                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
