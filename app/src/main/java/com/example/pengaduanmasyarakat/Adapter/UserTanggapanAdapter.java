package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Model.TanggapanModel;
import com.example.pengaduanmasyarakat.R;

import java.util.List;

public class UserTanggapanAdapter extends RecyclerView.Adapter<UserTanggapanAdapter.ViewHolder> {

    Context context;
    List<TanggapanModel> tanggapanModelList;

    public UserTanggapanAdapter(Context context, List<TanggapanModel> tanggapanModelList) {
        this.context = context;
        this.tanggapanModelList = tanggapanModelList;
    }

    @NonNull
    @Override
    public UserTanggapanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_user_tanggapan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserTanggapanAdapter.ViewHolder holder, int position) {
        holder.tvUsername.setText(tanggapanModelList.get(position).getUsername());
        holder.tvTglPengaduan.setText(tanggapanModelList.get(position).getTglTanggapan());
        holder.tvIsiTgpn.setText(tanggapanModelList.get(position).getIsiTanggapan());
        holder.tvStatusPengaduan.setText(tanggapanModelList.get(position).getStatusTanggapan());

        Glide.with(context)
                .load(tanggapanModelList.get(position).getFotoTanggapan())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .override(200, 200)
                .centerCrop()
                .fitCenter()
                .thumbnail(0.5f)
                .into(holder.ivTanggapan);

    }

    @Override
    public int getItemCount() {
        return tanggapanModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvStatusPengaduan, tvTglPengaduan, tvIsiTgpn;
        ImageView ivTanggapan, icStatus;
        CardView cvTanggapanStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.txtUsername);
            tvStatusPengaduan = itemView.findViewById(R.id.tvStatusTanggapan);
            tvTglPengaduan = itemView.findViewById(R.id.txtTglTanggapan);
            tvIsiTgpn =itemView.findViewById(R.id.txtisiTanggapan);
            ivTanggapan = itemView.findViewById(R.id.imgTanggapan);
            icStatus = itemView.findViewById(R.id.icStatus);
        }
    }
}
