package com.example.anangkur.ensiklopediatekmira;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Anang kur on 06/30/2017.
 */

public class AdapterArtikel extends RecyclerView.Adapter<AdapterArtikel.MyViewHolder>{
    private List<Artikel> listArtikel;
    private Context contex;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView judulArtikel;
        private ItemClickListener clickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            judulArtikel = (TextView) itemView.findViewById(R.id.txt_judul_artikel);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v,getPosition(),true);
            return true;
        }
    }

    public AdapterArtikel(List<Artikel> listArtikel, Context contex) {
        this.contex = contex;
        this.listArtikel = listArtikel;
    }

    @Override
    public AdapterArtikel.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_artikel, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String judul = listArtikel.get(position).getJudulArtikel();
        holder.judulArtikel.setText(judul);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(contex, "#" + position + " - " + listArtikel.get(position).getJudulArtikel() + " (Long click)",Toast.LENGTH_SHORT).show();
                }else{
                    //contex = view.getContext();
                    //Intent intent = new Intent(contex, ArtikelActivity.class);
                    //contex.startActivity(intent);
                    Toast.makeText(contex, "#" + position + " - " + listArtikel.get(position).getJudulArtikel() + " (short click)",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listArtikel.size();
    }
}
