package com.example.anangkur.ensiklopediatekmira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class HasilSearchActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView judulHasilPencarian;
    private View btnBackToSearch;
    private RecyclerView recyclerViewHasilPencarian;
    private AdapterKontenEnsiklopedi adapterKontenEnsiklopedi;
    private List<KontenEnsiklopedi> kontenEnsiklopediList = new ArrayList<>();
    private List<KontenEnsiklopedi> kontenEnsiklopediListCari = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private String hurufAwal;
    private String pesan;
    private String message;
    private StorageReference storageReference;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_search);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        judulHasilPencarian = (TextView) findViewById(R.id.title_hasil_pencarian);
        Intent intent = getIntent();

        pesan = intent.getStringExtra("PESAN");
        hurufAwal = intent.getStringExtra("HURUF_AWAL");

        if (pesan == null){
            judulHasilPencarian.setText(hurufAwal);
        }else{
            judulHasilPencarian.setText(pesan);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("KontenEnsiklopedi");
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ensiklopedia-tekmira.appspot.com");
        imageUrl = "gambarKonten/no_image.png";
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot kontenSnapshot : dataSnapshot.getChildren()){
                    KontenEnsiklopedi KontenEnsiklopedi = kontenSnapshot.getValue(KontenEnsiklopedi.class);
                    //KontenEnsiklopedi.setGambarKonten(imageUrl);
                    if (pesan == null){
                        if (KontenEnsiklopedi.getJudulKonten().isEmpty()){
                            continue;
                        } else {
                            if (KontenEnsiklopedi.getJudulKonten().substring(0,1).toLowerCase().contains(hurufAwal.toLowerCase())){
                                kontenEnsiklopediList.add(KontenEnsiklopedi);
                            }
                        }
                    }else{
                        if (KontenEnsiklopedi.getJudulKonten().toLowerCase().contains(pesan.toLowerCase())){
                            kontenEnsiklopediList.add(KontenEnsiklopedi);
                        }
                    }
                }
                adapterKontenEnsiklopedi.notifyDataSetChanged();
                //databaseReference.setValue(kontenEnsiklopediList);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        btnBackToSearch = findViewById(R.id.btn_backtosearch);
        btnBackToSearch.setOnClickListener(this);

        adapterKontenEnsiklopedi = new AdapterKontenEnsiklopedi(kontenEnsiklopediList, getApplicationContext());
        recyclerViewHasilPencarian = (RecyclerView) findViewById(R.id.recyclerview_hasil_pencarian);
        RecyclerView.LayoutManager hasilSearchActivityLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewHasilPencarian.setLayoutManager(hasilSearchActivityLayoutManager);
        recyclerViewHasilPencarian.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHasilPencarian.setAdapter(adapterKontenEnsiklopedi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_backtosearch:
                hurufAwal = "";
                finish();
                break;
        }
    }
}
