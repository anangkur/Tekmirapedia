package com.example.anangkur.ensiklopediatekmira;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class KontenEnsiklopediActivity extends AppCompatActivity implements View.OnClickListener{

    private View btnBackToHasilSearch;
    private TextView judulKontenEnsiklopedi;
    private TextView txtJudulKontenEnsiklopedi;
    private TextView txtDeskripsiKontenEnsiklopedi;
    private ImageView imgKontenEnsiklopedi;
    private View btnToSearch;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konten_ensiklopedi);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        btnBackToHasilSearch = findViewById(R.id.btn_backtohasilsearch);
        btnBackToHasilSearch.setOnClickListener(this);

        btnToSearch = findViewById(R.id.btn_tosearch);
        btnToSearch.setOnClickListener(this);

        judulKontenEnsiklopedi = (TextView) findViewById(R.id.txt_judul_kontenEnsiklopedi);
        txtDeskripsiKontenEnsiklopedi = (TextView) findViewById(R.id.txt_deskripsikontenensiklopedi);
        txtJudulKontenEnsiklopedi = (TextView) findViewById(R.id.txt_judul_kontenEnsiklopedi2);
        imgKontenEnsiklopedi = (ImageView) findViewById(R.id.img_kontenensiklopedi);
        judulKontenEnsiklopedi.setOnClickListener(this);
        Intent intent = getIntent();
        String pesanJudul = intent.getStringExtra("pesanJudul");
        String pesanDeskripsi = intent.getStringExtra("pesanDeskripsi");
        String pesanGambar = intent.getStringExtra("pesanGambar");

        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://ensiklopedia-tekmira.appspot.com").child(pesanGambar);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(KontenEnsiklopediActivity.this).load(uri).fit().centerCrop().into(imgKontenEnsiklopedi);
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        //Picasso.with(KontenEnsiklopediActivity.this).load(uri).into(imgKontenEnsiklopedi);
        judulKontenEnsiklopedi.setText(pesanJudul);
        txtJudulKontenEnsiklopedi.setText(pesanJudul);
        txtDeskripsiKontenEnsiklopedi.setText(pesanDeskripsi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_backtohasilsearch:
                finish();
                break;
            case R.id.btn_tosearch:
                finish();
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_judul_kontenEnsiklopedi:
                finish();
                Intent intent2 = new Intent(this, SearchActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
