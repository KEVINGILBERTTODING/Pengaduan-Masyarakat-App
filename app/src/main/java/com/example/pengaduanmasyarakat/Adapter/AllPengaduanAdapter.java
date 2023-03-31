package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;

import java.util.List;


public class AllPengaduanAdapter extends RecyclerView.Adapter<AllPengaduanAdapter.ViewHolder> {

    Context context;
    List<PengaduanModel> pengaduanModelList;

    public AllPengaduanAdapter(Context context, List<PengaduanModel> pengaduanModelList) {
        this.context = context;
        this.pengaduanModelList = pengaduanModelList;
    }

    @NonNull
    @Override
    public AllPengaduanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_all_pengaduan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllPengaduanAdapter.ViewHolder holder, int position) {

        holder.tvIsiLaporan.setText(pengaduanModelList.get(position).getStatusPengaduan());
        holder.tvKelurahan.setText(pengaduanModelList.get(position).getNamaKelurahan());
        holder.tvIsiLaporan.setText(pengaduanModelList.get(position).getIsiLaporan());

        Glide.with(context)
                .load(pengaduanModelList.get(position).getFoto())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(holder.imgPengaduan);

    }

    @Override
    public int getItemCount() {
        return pengaduanModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPengaduan;
        TextView tvStatusPengaduan, tvKelurahan, tvIsiLaporan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPengaduan = itemView.findViewById(R.id.imgPengaduan);
            tvStatusPengaduan = itemView.findViewById(R.id.txtStatus);
            tvKelurahan = itemView.findViewById(R.id.txtKelurahan);
            tvIsiLaporan = itemView.findViewById(R.id.txtisiLaporan);




        }

        @Override
        public void onClick(View v) {

        }
    }
}
