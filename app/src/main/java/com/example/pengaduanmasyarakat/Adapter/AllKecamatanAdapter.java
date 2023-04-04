package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.FragmentAdmin.UpdateKecamatanFragment;
import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KecamatanInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllKecamatanAdapter extends RecyclerView.Adapter<AllKecamatanAdapter.ViewHolder> {

    Context context;
    List<KecamatanModel> kecamatanModelList;

    public AllKecamatanAdapter(Context context, List<KecamatanModel> kecamatanModelList) {
        this.context = context;
        this.kecamatanModelList = kecamatanModelList;
    }

    @NonNull
    @Override
    public AllKecamatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_kecamatan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllKecamatanAdapter.ViewHolder holder, int position) {
        holder.tvKecamtan.setText(kecamatanModelList.get(position).getKecamatan());
        holder.cvKecamatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_option_menu_list_user);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnDelete, btnEdit;
                btnDelete = dialog.findViewById(R.id.btnDeleteUser);
                btnEdit = dialog.findViewById(R.id.btnEditUser);
                dialog.show();


                btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Fragment fragment = new UpdateKecamatanFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id_kecamatan", kecamatanModelList.get(position).getIdKecamtan());
                        bundle.putString("kecamatan", kecamatanModelList.get(position).getKecamatan());
                        fragment.setArguments(bundle);
                        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_admin_container, fragment)
                                .addToBackStack(null)
                                .commit();
                        dialog.dismiss();
                    }
                });


                btnDelete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        MaterialAlertDialogBuilder md = new MaterialAlertDialogBuilder(context);
                        md.setTitle("Peringatan!");
                        md.setMessage("Apakah anda yakin ingin menghapus kecamatan ini?");
                        md.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ProgressDialog pd = new ProgressDialog(context);
                                pd.setTitle("Menghapus data...");
                                pd.show();
                                pd.setCanceledOnTouchOutside(false);

                                KecamatanInterface ki = DataApi.getClient().create(KecamatanInterface.class);
                                ki.deleteKecamatan(kecamatanModelList.get(position).getIdKecamtan())
                                        .enqueue(new Callback<KecamatanModel>() {
                                            @Override
                                            public void onResponse(Call<KecamatanModel> call, Response<KecamatanModel> response) {
                                                if (response.isSuccessful()) {
                                                    KecamatanModel kecamatanModel = response.body();
                                                    if (kecamatanModel.getStatus().equals("success")) {
                                                        pd.dismiss();
                                                        notifyDataSetChanged();
                                                        notifyItemRangeChanged(position, kecamatanModelList.size());
                                                        notifyItemRemoved(position);
                                                        kecamatanModelList.remove(position);
                                                        Toasty.success(context, "Berhasil menghapus data", Toasty.LENGTH_SHORT).show();


                                                    }else {
                                                        Toasty.error(context, kecamatanModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                                        pd.dismiss();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<KecamatanModel> call, Throwable t) {
                                                Toasty.error(context, "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();
                                                pd.dismiss();

                                            }
                                        });

                            }
                        });
                        md.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        md.show();

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return kecamatanModelList.size();
    }


    public void filterList(ArrayList<KecamatanModel>filteredList){
        kecamatanModelList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvKecamatan;
        TextView tvKecamtan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvKecamatan = itemView.findViewById(R.id.cvKecamatan);
            tvKecamtan = itemView.findViewById(R.id.tvKecamatan);
        }
    }
}
