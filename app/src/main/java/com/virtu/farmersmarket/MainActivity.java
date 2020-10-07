package com.virtu.farmersmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tapadoo.alerter.Alerter;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar  = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        webView = (WebView) findViewById(R.id.web_view);
        progressBar   = findViewById(R.id.progress);
        webView.setVisibility(View.GONE);
        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.farmerzmrkt.com");

    /*    try {
            ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null || !netInfo.isConnected()
                    || !netInfo.isAvailable()) {
                Alerter.clearCurrent(MainActivity.this);
                Alerter.create(MainActivity.this)
                        .setTitle("Sorry")
                        .setText("Internet connection not available, please enable your internet and try again..")
                        .setBackgroundColorRes(R.color.colorPrimary)
                        .setIcon(R.drawable.ic_warning_latest)
                        .setDuration(2000)
                        .setIconColorFilter(0)
                        .show();

            } else {

            }

        } catch (Exception e) {
            Log.d("Tag", e.toString());
        }*/

    }


    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
