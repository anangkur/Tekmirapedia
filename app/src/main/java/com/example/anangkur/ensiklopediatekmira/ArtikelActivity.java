package com.example.anangkur.ensiklopediatekmira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ArtikelActivity extends AppCompatActivity {

    private WebView webViewArtikelActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        webViewArtikelActivity = (WebView) findViewById(R.id.webview_artikel_activity);
        webViewArtikelActivity.loadUrl("https://anangk97.wixsite.com/puslitbangtekmira");
        WebSettings webSettings = webViewArtikelActivity.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
