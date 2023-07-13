package com.example.anangkur.ensiklopediatekmira;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText inputSearch;
    private boolean enterPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final View btnBackToMain = findViewById(R.id.btn_backtomain);
        btnBackToMain.setOnClickListener(this);

        View btnDeleteSearch = findViewById(R.id.btn_delete_search);
        btnDeleteSearch.setOnClickListener(this);

        final View btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        inputSearch = (EditText) findViewById(R.id.input_search);
        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    btnSearch.performClick();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_backtomain:
                finish();
                break;
            case R.id.btn_delete_search:
                inputSearch = (EditText) findViewById(R.id.input_search);
                if (inputSearch.getText().equals("")){
                    finish();
                }else{
                    inputSearch.setText("");
                }
                break;
            case R.id.btn_search:
                String pesan = String.valueOf(inputSearch.getText());
                if (pesan.equals("")){
                    Toast.makeText(SearchActivity.this, "Silahkan masukkan kata yang ingin anda cari", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(this, HasilSearchActivity.class);
                    intent.putExtra("PESAN", pesan);
                    startActivity(intent);
                }
        }
    }

}
