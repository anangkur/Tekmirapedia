package com.example.anangkur.ensiklopediatekmira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Artikel> artikelList = new ArrayList<>();
    private String judulArtikel;
    private RecyclerView recyclerViewArtikel;
    private AdapterArtikel adapterArtikel;
    private RecyclerView.LayoutManager artikelLayoutManager;
    private ListView listViewNavDrawer;
    private ArrayAdapter<String> arrayAdapterNavDrawer;
    private DrawerLayout mainActivityDrawerLayout;
    private RelativeLayout navDrawer;
    private Window window;
    private String hurufAwal;
    private WebView webviewArtikel;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityDrawerLayout = (DrawerLayout) findViewById(R.id.main_activity);
        listViewNavDrawer = (ListView) findViewById(R.id.listview_navdrawer);
        String[] isiListViewNavDrawer = {"     A","     B", "     C", "     D", "     E","     F","     G","     H","     I","     J","     K","     L","     M","     N","     O","     P","     Q","     R","     S","     T","     U","     V","     W","     X","     Y","     Z"};
        arrayAdapterNavDrawer = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, isiListViewNavDrawer);
        listViewNavDrawer.setAdapter(arrayAdapterNavDrawer);
        listViewNavDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "item " + (position+1) + " clicked", Toast.LENGTH_SHORT).show();
                switch (position){
                    case 0:
                        hurufAwal = "A";
                        break;
                    case 1:
                        hurufAwal = "B";
                        break;
                    case 2:
                        hurufAwal = "C";
                        break;
                    case 3:
                        hurufAwal = "D";
                        break;
                    case 4:
                        hurufAwal = "E";
                        break;
                    case 5:
                        hurufAwal = "F";
                        break;
                    case 6:
                        hurufAwal = "G";
                        break;
                    case 7:
                        hurufAwal = "H";
                        break;
                    case 8:
                        hurufAwal = "I";
                        break;
                    case 9:
                        hurufAwal = "J";
                        break;
                    case 10:
                        hurufAwal = "K";
                        break;
                    case 11:
                        hurufAwal = "L";
                        break;
                    case 12:
                        hurufAwal = "M";
                        break;
                    case 13:
                        hurufAwal = "N";
                        break;
                    case 14:
                        hurufAwal = "O";
                        break;
                    case 15:
                        hurufAwal = "P";
                        break;
                    case 16:
                        hurufAwal = "Q";
                        break;
                    case 17:
                        hurufAwal = "R";
                        break;
                    case 18:
                        hurufAwal = "S";
                        break;
                    case 19:
                        hurufAwal = "T";
                        break;
                    case 20:
                        hurufAwal = "U";
                        break;
                    case 21:
                        hurufAwal = "V";
                        break;
                    case 22:
                        hurufAwal = "W";
                        break;
                    case 23:
                        hurufAwal = "X";
                        break;
                    case 24:
                        hurufAwal = "Y";
                        break;
                    case 25:
                        hurufAwal = "Z";
                        break;
                }

                Intent intentHurufAwal = new Intent(view.getContext(), HasilSearchActivity.class);
                intentHurufAwal.putExtra("HURUF_AWAL", hurufAwal);
                startActivity(intentHurufAwal);
            }
        });

        navDrawer = (RelativeLayout) findViewById(R.id.nav_drawer);

        Toolbar toolbarMainactivity = (Toolbar) findViewById(R.id.toolbar_mainactivity);
        toolbarMainactivity.setOnClickListener(this);

        View buttonMenuSidebar2 = findViewById(R.id.button_menu_sidebar_dua);
        buttonMenuSidebar2.setOnClickListener(this);

        View buttonMenuSidebar = findViewById(R.id.button_menu_sidebar);
        buttonMenuSidebar.setOnClickListener(this);

        EditText inputTextCari = (EditText) findViewById(R.id.inptxt_cari);
        inputTextCari.setOnClickListener(this);

        adapterArtikel = new AdapterArtikel(artikelList, getApplicationContext());

        recyclerViewArtikel = (RecyclerView) findViewById(R.id.recyclerview_artikel);

        RecyclerView.LayoutManager mainActivityLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewArtikel.setLayoutManager(mainActivityLayoutManager);

        recyclerViewArtikel.setItemAnimator(new DefaultItemAnimator());
        recyclerViewArtikel.setAdapter(adapterArtikel);

        artikelList.add(new Artikel("Judul Artikel 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
        artikelList.add(new Artikel("Judul Artikel 9", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

        //getWindow().requestFeature(Window.FEATURE_PROGRESS);
        //setContentView(R.layout.activity_main);
        //getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_webview);

        webviewArtikel = (WebView) findViewById(R.id.webview_artikel);
        webviewArtikel.loadUrl("https://tekmirapedia.wixsite.com/puslitbangtekmira");

        webviewArtikel.setWebViewClient(new ArtikelWebViewClient());
        WebSettings webSettings = webviewArtikel.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private class ArtikelWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //progressBar.setVisibility(View.VISIBLE);
            //webviewArtikel.setVisibility(View.INVISIBLE);
            //progressDialog.show();
            view.loadUrl(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //progressBar.setVisibility(View.GONE);
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && (webviewArtikel.canGoBack())){
            webviewArtikel.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inptxt_cari:
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.button_menu_sidebar:
                mainActivityDrawerLayout.openDrawer(navDrawer);
                break;
            case R.id.button_menu_sidebar_dua:
                mainActivityDrawerLayout.closeDrawer(navDrawer);
                break;
        }
    }
}
