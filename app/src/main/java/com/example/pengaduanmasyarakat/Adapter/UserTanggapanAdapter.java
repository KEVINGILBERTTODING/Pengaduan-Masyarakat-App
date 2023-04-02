package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Fragment.user.FullScreenImageAllFragment;
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

        if (tanggapanModelList.get(position).getStatusTanggapan().equals("proses")) {
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.main));
            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.white));
            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.process));
        } else  if (tanggapanModelList.get(position).getStatusTanggapan().equals("valid")) {

            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.green));

            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.white));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.green));

            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.check));
        } else  if (tanggapanModelList.get(position).getStatusTanggapan().equals("pengerjaan")) {
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.white));
            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.orange));

            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.hammer));
        } else  if (tanggapanModelList.get(position).getStatusTanggapan().equals("selesai")) {
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.white));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.baseline_calendar_month_24));
            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.blue));

        } else  if (tanggapanModelList.get(position).getStatusTanggapan().equals("tidak_valid")) {
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.black));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.close));
            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.gray));

        }else  if (tanggapanModelList.get(position).getStatusTanggapan().equals("belum_ditanggapi")) {
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.tvStatusPengaduan.setTextColor(context.getResources().getColor(R.color.black));
            holder.vTanggapan.setBackgroundColor(context.getResources().getColor(R.color.gray));

            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.close));

        }

    }

    @Override
    public int getItemCount() {
        return tanggapanModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvUsername, tvStatusPengaduan, tvTglPengaduan, tvIsiTgpn;
        ImageView ivTanggapan, icStatus;
        CardView cvTanggapanStatus;
        View vTanggapan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.txtUsername);
            tvStatusPengaduan = itemView.findViewById(R.id.tvStatusTanggapan);
            tvTglPengaduan = itemView.findViewById(R.id.txtTglTanggapan);
            tvIsiTgpn =itemView.findViewById(R.id.txtisiTanggapan);
            ivTanggapan = itemView.findViewById(R.id.imgTanggapan);
            icStatus = itemView.findViewById(R.id.icStatus);
            cvTanggapanStatus = itemView.findViewById(R.id.cvStatusTanggapan);
            vTanggapan = itemView.findViewById(R.id.viewTanggapan);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.layout_det_tanggapan);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            final Button btnOk;
            final TextView tvUsername, tvTgl, tvIsiTanggapan;
            final ImageView imgTanggapan;
            btnOk = dialog.findViewById(R.id.btn_tanggapan_ok);
            tvUsername = dialog.findViewById(R.id.tvUsername);
            tvTgl = dialog.findViewById(R.id.tvTglTanggapan);
            tvIsiTanggapan = dialog.findViewById(R.id.tvIsiTanggapan);
            imgTanggapan = dialog.findViewById(R.id.imgTanggapan);

            tvUsername.setText(tanggapanModelList.get(getAdapterPosition()).getUsername());
            tvTgl.setText(tanggapanModelList.get(getAdapterPosition()).getTglTanggapan());
            tvIsiTanggapan.setText(tanggapanModelList.get(getAdapterPosition()).getIsiTanggapan());

            Glide.with(context)
                    .load(tanggapanModelList.get(getAdapterPosition()).getFotoTanggapan())
                    .override(200,200)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .fitCenter()
                    .dontAnimate()
                    .into(imgTanggapan);

            dialog.show();
            imgTanggapan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new FullScreenImageAllFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("image", tanggapanModelList.get(getAdapterPosition()).getFotoTanggapan());
                    fragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, fragment)
                            .addToBackStack(null)
                            .commit();

                    dialog.dismiss();
                }
            });

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });


        }
    }
}
