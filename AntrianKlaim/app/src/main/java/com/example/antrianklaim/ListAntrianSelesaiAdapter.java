package com.example.antrianklaim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class ListAntrianSelesaiAdapter extends RecyclerView.Adapter <ListAntrianSelesaiAdapter.RecyclerViewAdapter> {
    private Context context;
    private List<ModelListSelesai> modelListSelesais;
    private ItemClickListener itemClickListener;

    public ListAntrianSelesaiAdapter(Context context, List<ModelListSelesai> modelListSelesais, ItemClickListener itemClickListener) {
        this.context = context;
        this.modelListSelesais = modelListSelesais;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_antrian_selesai,parent,false);
        return new ListAntrianSelesaiAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        ModelListSelesai modelListSelesai = modelListSelesais.get(position);
        holder.tvnoantrian.setText(modelListSelesai.getNo_antrian());
        holder.tvnamanasabah.setText(modelListSelesai.getNama_lengkap());
        holder.tvnoHp.setText(modelListSelesai.getNo_handphone());
        holder.tvstatus.setText(modelListSelesai.getStatus_antrian());
    }

    @Override
    public int getItemCount() {
        return modelListSelesais.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView item_antrian;
        TextView tvnoantrian, tvnamanasabah, tvnoHp, tvstatus;
        ItemClickListener itemClickListener;

        public RecyclerViewAdapter(@NonNull View itemView , ItemClickListener itemClickListener) {
            super(itemView);
            item_antrian = itemView.findViewById(R.id.card_itemAntrianSelesai);
            tvnoantrian = itemView.findViewById(R.id.txtNoAntrianTdy);
            tvnamanasabah = itemView.findViewById(R.id.txtNamaNasabah);
            tvnoHp = itemView.findViewById(R.id.txtNoHpNasabah);
            tvstatus = itemView.findViewById(R.id.txtStatusAntrian);
            this.itemClickListener = itemClickListener;
            item_antrian.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
