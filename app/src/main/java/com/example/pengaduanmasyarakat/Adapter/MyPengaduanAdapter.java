package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;

import java.util.List;

public class MyPengaduanAdapter extends RecyclerView.Adapter<MyPengaduanAdapter.ViewHolder> {
    Context context;
    List<PengaduanModel> pengaduanModelList;

    public MyPengaduanAdapter(Context context, List<PengaduanModel> pengaduanModelList) {
        this.context = context;
        this.pengaduanModelList = pengaduanModelList;
    }

    @NonNull
    @Override


    public MyPengaduanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_all_pengaduan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPengaduanAdapter.ViewHolder holder, int position) {

        holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());
        holder.tvIsiLaporan.setText(pengaduanModelList.get(position).getIsiLaporan());
        holder.tvKelurahan.setText(pengaduanModelList.get(position).getNamaKelurahan());

        Glide.with(context)
                .load(pengaduanModelList.get(position).getFoto())
                .override(200, 200)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .dontAnimate()
                .fitCenter()
                .thumbnail(0.5f)
                .centerCrop()
                .into(holder.imgPengaduan);



        if (pengaduanModelList.get(position).getStatusPengaduan().equals("proses")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.main));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));

        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("selesai")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.blue));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));

        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("valid")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.green));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));

        }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("pengerjaan")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.orange));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.black));

        }
    }

    @Override
    public int getItemCount() {
        return pengaduanModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPengaduan;
        TextView tvStatusPengaduan, tvKelurahan, tvIsiLaporan;
        RelativeLayout containerStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPengaduan = itemView.findViewById(R.id.imgPengaduan);
            tvStatusPengaduan = itemView.findViewById(R.id.txtStatus);
            tvKelurahan = itemView.findViewById(R.id.txtKelurahan);
            tvIsiLaporan = itemView.findViewById(R.id.txtisiLaporan);
            containerStatus = itemView.findViewById(R.id.containerStatusPengaduan);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
