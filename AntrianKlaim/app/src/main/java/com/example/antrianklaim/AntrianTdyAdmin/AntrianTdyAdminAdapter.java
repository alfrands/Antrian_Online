package com.example.antrianklaim.AntrianTdyAdmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.antrianklaim.R;

import java.util.List;

public class AntrianTdyAdminAdapter extends RecyclerView.Adapter<AntrianTdyAdminAdapter.RecyclerViewAdapter> {
    private Context context;
    private List<AntrianTdyModel> antrianTdyModels;
    private ItemClickListener itemClickListener;

    public AntrianTdyAdminAdapter(Context context, List<AntrianTdyModel> antrianTdyModels, ItemClickListener itemClickListener) {
        this.context = context;
        this.antrianTdyModels = antrianTdyModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_antrian_today,parent,false);
        return new AntrianTdyAdminAdapter.RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        AntrianTdyModel antrianTdyModel = antrianTdyModels.get(position);
        holder.tvid.setText(antrianTdyModel.getId());
        holder.tvidnasabah.setText(antrianTdyModel.getId_nasabah());
        holder.tvnoantrian.setText(antrianTdyModel.getNo_antrian());
        holder.tvnamanasabah.setText(antrianTdyModel.getNama_lengkap());
        holder.tvnoHp.setText(antrianTdyModel.getNo_handphone());
        holder.tvstatus.setText(antrianTdyModel.getStatus_antrian());
    }

    @Override
    public int getItemCount() {
        return antrianTdyModels.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView item_antrian;
        TextView tvid , tvidnasabah, tvnoantrian, tvnamanasabah, tvnoHp, tvstatus;
        ItemClickListener itemClickListener;
        public RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            item_antrian = itemView.findViewById(R.id.card_itemAntrianTdy);
            tvid = itemView.findViewById(R.id.txt_IdAntrian);
            tvidnasabah = itemView.findViewById(R.id.txt_IdNasabah);
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
