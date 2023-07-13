package com.example.anangkur.ensiklopediatekmira;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SearchActivity2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        WebView webViewSearch = (WebView) findViewById(R.id.webview_search);
        webViewSearch.loadUrl("http://192.168.137.1:80/wikimedia/index.php?search=&title=Istimewa%3APencarian&go=Lanjut");
        WebSettings webViewSearchSetting = webViewSearch.getSettings();
        webViewSearchSetting.setJavaScriptEnabled(true);
        webViewSearch.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onClick(View v) {

    }
}
