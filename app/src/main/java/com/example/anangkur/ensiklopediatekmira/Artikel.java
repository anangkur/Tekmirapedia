package com.example.anangkur.ensiklopediatekmira;

/**
 * Created by Anang kur on 06/30/2017.
 */

public class Artikel {
    private String judulArtikel;
    private String deskripsiArtikel;

    public Artikel(String judulArtikel, String deskripsiArtikel) {
        this.judulArtikel = judulArtikel;
        this.deskripsiArtikel = deskripsiArtikel;
    }

    public String getJudulArtikel() {
        return judulArtikel;
    }

    public void setJudulArtikel(String judulArtikel) {
        this.judulArtikel = judulArtikel;
    }

    public String getDeskripsiArtikel() {
        return deskripsiArtikel;
    }

    public void setDeskripsiArtikel(String deskripsiArtikel) {
        this.deskripsiArtikel = deskripsiArtikel;
    }
}
