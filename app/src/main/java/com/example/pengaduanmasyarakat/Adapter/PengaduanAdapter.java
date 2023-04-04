package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Fragment.user.AdminDetailPengaduanFragment;
import com.example.pengaduanmasyarakat.Fragment.user.DetailPengaduanFragment;
import com.example.pengaduanmasyarakat.Fragment.user.EditPengaduanFragment;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AdminPengaduanInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.ViewHolder> {
    Context context;
    List<PengaduanModel> pengaduanModelList;

    public PengaduanAdapter(Context context, List<PengaduanModel> pengaduanModelList) {
        this.context = context;
        this.pengaduanModelList = pengaduanModelList;
    }

    @NonNull
    @Override


    public PengaduanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_all_pengaduan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengaduanAdapter.ViewHolder holder, int position) {


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


        holder.cvPengaduanItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_option_menu_pengaduan);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnDelete, btnDetailPengaduan;
                btnDelete = dialog.findViewById(R.id.btnDeleteMyPengaduan);
                btnDetailPengaduan = dialog.findViewById(R.id.btnDetailPengaduan);
                dialog.show();


                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        ProgressDialog pd = new ProgressDialog(context);
                        pd.setTitle("Menghapus data...");
                        pd.setCanceledOnTouchOutside(false);
                        pd.show();

                        // call retrofit
                        AdminPengaduanInterface aip = DataApi.getClient().create(AdminPengaduanInterface.class);
                        aip.deletePengaduan(pengaduanModelList.get(position).getIdPengaduan()).enqueue(new Callback<PengaduanModel>() {
                            @Override
                            public void onResponse(Call<PengaduanModel> call, Response<PengaduanModel> response) {
                                PengaduanModel pengaduanModel =  response.body();
                                if (response.isSuccessful() && pengaduanModel.getStatus().equals("success")) {
                                    pd.dismiss();
                                    notifyDataSetChanged();
                                    notifyItemRangeChanged(position, pengaduanModelList.size());
                                    notifyItemRemoved(position);
                                    pengaduanModelList.remove(position);
                                    Toasty.success(context, "Berhasil menghapus pengaduan", Toasty.LENGTH_SHORT).show();
                                }else {
                                    pd.dismiss();
                                    Toasty.error(context, "Gagal menghapus pengaduan", Toasty.LENGTH_SHORT).show();


                                }
                            }

                            @Override
                            public void onFailure(Call<PengaduanModel> call, Throwable t) {
                                pd.dismiss();
                                Toasty.error(context, "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();

                            }
                        });

                    }
                });

                btnDetailPengaduan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Fragment fragment = new AdminDetailPengaduanFragment();
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
                                .replace(R.id.frame_admin_container, fragment)
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

        CardView cvPengaduanItem;
        View vPengaduan;
        ImageView icStatus;
        CardView cvTanggapanStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPengaduan = itemView.findViewById(R.id.imgPengaduan);

            tvKelurahan = itemView.findViewById(R.id.txtKelurahan);
            tvIsiLaporan = itemView.findViewById(R.id.txtisiLaporan);
            cvPengaduanItem = itemView.findViewById(R.id.cvPengaduanItem);
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
