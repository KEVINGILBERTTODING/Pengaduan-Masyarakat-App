package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Fragment.user.DetailPengaduanFragment;
import com.example.pengaduanmasyarakat.Fragment.user.EditPengaduanFragment;
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
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());

        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("selesai")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.blue));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());

        } else if (pengaduanModelList.get(position).getStatusPengaduan().equals("valid")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.green));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.white));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());

        }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("pengerjaan")){
            holder.containerStatus.setBackgroundColor(holder.itemView.getResources().getColor(R.color.orange));
            holder.tvStatusPengaduan.setTextColor(holder.itemView.getResources().getColor(R.color.black));
            holder.tvStatusPengaduan.setText(pengaduanModelList.get(position).getStatusPengaduan());

         }else if (pengaduanModelList.get(position).getStatusPengaduan().equals("belum_ditanggapi")){
        holder.tvStatusPengaduan.setText("Belum ditanggapi");


    }


        holder.cvPengaduanItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_option_menu);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnEditPengaduan, btnDetailPengaduan;
                btnEditPengaduan = dialog.findViewById(R.id.btnEditMyPengaduan);
                btnDetailPengaduan = dialog.findViewById(R.id.btnDetailPengaduan);
                dialog.show();

                if (pengaduanModelList.get(position).getStatusPengaduan().equals("belum_ditanggapi")) {
                    btnEditPengaduan.setVisibility(View.VISIBLE);
                } else {
                    btnEditPengaduan.setVisibility(View.GONE);
                }

                btnEditPengaduan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Fragment fragment = new EditPengaduanFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id_pengaduan", pengaduanModelList.get(position).getIdPengaduan());
                        bundle.putString("isi_laporan", pengaduanModelList.get(position).getIsiLaporan());
                        bundle.putString("photo", pengaduanModelList.get(position).getFoto());
                        bundle.putString("photo1", pengaduanModelList.get(position).getFoto1());
                        bundle.putString("photo2", pengaduanModelList.get(position).getFoto2());
                        fragment.setArguments(bundle);
                        fragment.setArguments(bundle);
                        FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frame_container, fragment)
                                .addToBackStack(null)
                                .commit();

                    }
                });

                btnDetailPengaduan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Fragment fragment = new DetailPengaduanFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id_pengaduan", pengaduanModelList.get(position).getIdPengaduan());
                        bundle.putString("isi_laporan", pengaduanModelList.get(position).getIsiLaporan());
                        bundle.putString("kelurahan", pengaduanModelList.get(position).getNamaKelurahan());
                        bundle.putString("jenis", pengaduanModelList.get(position).getJenis());
                        bundle.putString("status_pengaduan", pengaduanModelList.get(position).getStatusPengaduan());
                        bundle.putString("tgl_pengaduan", pengaduanModelList.get(position).getTglPengaduan());
                        bundle.putString("foto", pengaduanModelList.get(position).getFoto());
                        bundle.putString("foto1", pengaduanModelList.get(position).getFoto1());
                        bundle.putString("foto2", pengaduanModelList.get(position).getFoto2());
                        fragment.setArguments(bundle);
                        ((FragmentActivity) context).getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, fragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return pengaduanModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPengaduan;
        TextView tvStatusPengaduan, tvKelurahan, tvIsiLaporan;
        RelativeLayout containerStatus;
        CardView cvPengaduanItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPengaduan = itemView.findViewById(R.id.imgPengaduan);
            tvStatusPengaduan = itemView.findViewById(R.id.txtStatus);
            tvKelurahan = itemView.findViewById(R.id.txtKelurahan);
            tvIsiLaporan = itemView.findViewById(R.id.txtisiLaporan);
            containerStatus = itemView.findViewById(R.id.containerStatusPengaduan);
            cvPengaduanItem = itemView.findViewById(R.id.cvPengaduanItem);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
