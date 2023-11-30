package com.example.altsvcwebview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = findViewById(R.id.webview);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.loadUrl("https://http3.is/");

        findViewById(R.id.reload_webview_button).setOnClickListener(view -> {
            webView.reload();
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("WEB_VIEW_TEST", "onPageStarted url:" + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.evaluateJavascript("performance.getEntriesByType('navigation')[0].nextHopProtocol", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.i("WEB_VIEW_TEST", "Protocol used was " + value);
                    }
                });
                super.onPageFinished(view, url);
            }
        });
    }
}