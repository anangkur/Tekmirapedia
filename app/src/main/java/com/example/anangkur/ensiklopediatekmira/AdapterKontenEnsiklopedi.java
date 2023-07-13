package com.example.anangkur.ensiklopediatekmira;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by Anang kur on 07/02/2017.
 */

public class AdapterKontenEnsiklopedi extends RecyclerView.Adapter<AdapterKontenEnsiklopedi.MyViewHolder>{
    private List<KontenEnsiklopedi> listKontenEnsiklopedi;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView judulKontenEndiklopedi;
        private ItemClickListener itemClickListener;

        public MyViewHolder(View itemView) {
            super(itemView);
            judulKontenEndiklopedi = (TextView) itemView.findViewById(R.id.txt_judul_hasil_pencarian);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getPosition(), true);
            return true;
        }
    }

    public AdapterKontenEnsiklopedi(List<KontenEnsiklopedi> listKontenEnsiklopedi, Context context) {
        this.listKontenEnsiklopedi = listKontenEnsiklopedi;
        this.context = context;
    }

    @Override
    public AdapterKontenEnsiklopedi.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_hasil_pencarian, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String temp = listKontenEnsiklopedi.get(position).getJudulKonten();
        holder.judulKontenEndiklopedi.setText(temp);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "#" + position + " - " + listKontenEnsiklopedi.get(position).getJudulKonten() + " (Long click)",Toast.LENGTH_SHORT).show();
                }else{
                    context = view.getContext();
                    Intent intent = new Intent(context, KontenEnsiklopediActivity.class);
                    String pesanJudul = String.valueOf(holder.judulKontenEndiklopedi.getText());
                    String pesanDeskripsi = listKontenEnsiklopedi.get(position).getDeskripsiKonten();
                    String pesanGambar = listKontenEnsiklopedi.get(position).getGambarKonten();
                    intent.putExtra("pesanJudul", pesanJudul);
                    intent.putExtra("pesanDeskripsi", pesanDeskripsi);
                    intent.putExtra("pesanGambar", pesanGambar);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKontenEnsiklopedi.size();
    }

}
