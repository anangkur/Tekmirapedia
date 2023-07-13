package com.example.anangkur.ensiklopediatekmira;

import android.net.Uri;

/**
 * Created by Anang kur on 07/02/2017.
 */

public class KontenEnsiklopedi {
    private String judulKonten;
    private String deskripsiKonten;
    private String gambarKonten;

    public KontenEnsiklopedi() {
    }

    public KontenEnsiklopedi(String judulKonten, String deskripsiKonten) {
        this.judulKonten = judulKonten;
        this.deskripsiKonten = deskripsiKonten;
    }

    public KontenEnsiklopedi(String judulKonten, String deskripsiKonten, String gambarKonten) {
        this.judulKonten = judulKonten;
        this.deskripsiKonten = deskripsiKonten;
        this.gambarKonten = gambarKonten;
    }

    public String getJudulKonten() {
        return judulKonten;
    }

    public void setJudulKonten(String judulKonten) {
        this.judulKonten = judulKonten;
    }

    public String getDeskripsiKonten() {
        return deskripsiKonten;
    }

    public void setDeskripsiKonten(String deskripsiKonten) {
        this.deskripsiKonten = deskripsiKonten;
    }

    public String getGambarKonten() {
        return gambarKonten;
    }

    public void setGambarKonten(String gambarKonten) {
        this.gambarKonten = gambarKonten;
    }
}
