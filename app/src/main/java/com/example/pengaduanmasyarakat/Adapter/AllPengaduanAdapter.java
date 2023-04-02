package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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


        holder.tvKelurahan.setText(pengaduanModelList.get(position).getNamaKelurahan());
        holder.tvIsiLaporan.setText(pengaduanModelList.get(position).getIsiLaporan());



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

            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.main));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.main));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.process));


        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("selesai")){

            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.blue));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.blue));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.baseline_calendar_month_24));




        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("valid")){

            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.green));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.green));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.check));




        }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("pengerjaan")){

            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.black));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.orange));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.orange));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.hammer));




        }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("belum_ditanggapi")){
            holder.tvStatusPengaduan.setText("Belum ditanggapi");
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.close));


        }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("tidak_valid")){
            holder.tvStatusPengaduan.setText("Tidak valid");
            holder.vPengaduan.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.cvTanggapanStatus.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.icStatus.setImageDrawable(context.getDrawable(R.drawable.close));

        }
    }

    @Override
    public int getItemCount() {
        return pengaduanModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPengaduan;
        TextView tvStatusPengaduan, tvKelurahan, tvIsiLaporan;
        View vPengaduan;
        ImageView icStatus;
        CardView cvTanggapanStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPengaduan = itemView.findViewById(R.id.imgPengaduan);
            tvKelurahan = itemView.findViewById(R.id.txtKelurahan);
            tvIsiLaporan = itemView.findViewById(R.id.txtisiLaporan);
            vPengaduan = itemView.findViewById(R.id.vPengaduan);
            icStatus = itemView.findViewById(R.id.icStatus);
            cvTanggapanStatus = itemView.findViewById(R.id.cvStatusTanggapan);
            tvStatusPengaduan = itemView.findViewById(R.id.tvStatusPengaduan);




        }

        @Override
        public void onClick(View v) {

        }
    }
}
