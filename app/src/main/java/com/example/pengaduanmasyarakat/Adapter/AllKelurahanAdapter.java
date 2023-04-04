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
import com.example.pengaduanmasyarakat.FragmentAdmin.UpdateKelurahanFragment;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KecamatanInterface;
import com.example.pengaduanmasyarakat.Util.interfaces.KelurahanInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllKelurahanAdapter extends RecyclerView.Adapter<AllKelurahanAdapter.ViewHolder> {

    Context context;
    List<KelurahanModel> kelurahanmodellist;

    public AllKelurahanAdapter(Context context, List<KelurahanModel> kelurahanmodellist) {
        this.context = context;
        this.kelurahanmodellist = kelurahanmodellist;
    }

    @NonNull
    @Override
    public AllKelurahanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_kelurahan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllKelurahanAdapter.ViewHolder holder, int position) {
        holder.tvKelurahan.setText(kelurahanmodellist.get(position).getKelurahan());
        holder.cvKelurahan.setOnClickListener(new View.OnClickListener() {
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
                        Fragment fragment = new UpdateKelurahanFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("id_kecamatan", kelurahanmodellist.get(position).getIdKecamatan());
                        bundle.putString("kelurahan", kelurahanmodellist.get(position).getKelurahan());
                        bundle.putString("id_kelurahan", kelurahanmodellist.get(position).getIdKelurahan());
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

                                KelurahanInterface ki = DataApi.getClient().create(KelurahanInterface.class);
                                ki.deleteKelurahan(kelurahanmodellist.get(position).getIdKelurahan())
                                        .enqueue(new Callback<KelurahanModel>() {
                                            @Override
                                            public void onResponse(Call<KelurahanModel> call, Response<KelurahanModel> response) {
                                                if (response.isSuccessful()) {
                                                    KelurahanModel kecamatanModel = response.body();
                                                    if (kecamatanModel.getStatus().equals("success")) {
                                                        pd.dismiss();
                                                        notifyDataSetChanged();
                                                        notifyItemRangeChanged(position, kelurahanmodellist.size());
                                                        notifyItemRemoved(position);
                                                        kelurahanmodellist.remove(position);
                                                        Toasty.success(context, "Berhasil menghapus data", Toasty.LENGTH_SHORT).show();


                                                    }else {
                                                        Toasty.error(context, kecamatanModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                                        pd.dismiss();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<KelurahanModel> call, Throwable t) {
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
        return kelurahanmodellist.size();
    }


    public void filterList(ArrayList<KelurahanModel>filteredList){
        kelurahanmodellist = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvKelurahan;
        TextView tvKelurahan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvKelurahan = itemView.findViewById(R.id.cvKelurahan);
            tvKelurahan = itemView.findViewById(R.id.tvKelurahan);
        }
    }
}
