package com.example.wbveiw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {


    WebView webview;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    String currentUrl = "https://geeksahil.in/";

    @SuppressLint("SetJavaScriptEnabled") //delthisiferrors
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        webview = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://geekjnr.com");

        webview.setWebViewClient(new MyWebViewClient());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webview.loadUrl(currentUrl);
            }
        });
    }


        class MyWebViewClient extends WebViewClient {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false);
                currentUrl = url;
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        }
    @Override
    public void onBackPressed() {
        WebView webView;
        webView = findViewById(R.id.webView);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}


