package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.Model.AdminUserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.UserInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarUserAdapter extends RecyclerView.Adapter<DaftarUserAdapter.ViewHolder> {
    Context context;
    List<AdminUserModel> adminUserModelList;
    UserInterface userInterface;

    public DaftarUserAdapter(Context context, List<AdminUserModel> adminUserModelList) {
        this.context = context;
        this.adminUserModelList = adminUserModelList;
    }



    @NonNull
    @Override
    public DaftarUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarUserAdapter.ViewHolder holder, int position) {
        userInterface = DataApi.getClient().create(UserInterface.class);


        holder.tvUsername.setText(adminUserModelList.get(position).getUserName());
        holder.tvNoTelp.setText(adminUserModelList.get(position).getNoTelp());
        holder.cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.layout_option_menu_list_user);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                final Button btnDelete, btnEdit;
                btnDelete = dialog.findViewById(R.id.btnDeleteUser);
                btnEdit = dialog.findViewById(R.id.btnEditUser);
                dialog.show();


                // btn delete listener
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(context);
                        materialAlertDialogBuilder.setTitle("Peringatan");
                        materialAlertDialogBuilder.setMessage("Apakah anda yakin akan menghapus user ini?");

                        // Jika setuju
                        materialAlertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userInterface.deleteUser(adminUserModelList.get(position).getIdUser())
                                        .enqueue(new Callback<AdminUserModel>() {
                                            @Override
                                            public void onResponse(Call<AdminUserModel> call, Response<AdminUserModel> response) {
                                                AdminUserModel adminUserModel = response.body();
                                                if (adminUserModel.getStatus().equals("success")) {

                                                    notifyItemRemoved(position);
                                                    notifyDataSetChanged();
                                                    adminUserModelList.remove(position);
                                                    notifyItemRangeChanged(position, adminUserModelList.size());
                                                    Toasty.success(context, adminUserModel.getMessage(), Toasty.LENGTH_SHORT).show();



                                                }else {
                                                    Toasty.error(context, adminUserModel.getMessage(), Toasty.LENGTH_SHORT).show();

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<AdminUserModel> call, Throwable t) {
                                                Toasty.error(context, "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();
                                                Log.e("DELETE"  , "onFailure: ", t);


                                            }
                                        });



                            }
                        });

                        //Jika tidak setuju
                        materialAlertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        materialAlertDialogBuilder.show();

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return adminUserModelList.size();
    }

    public void filterList(ArrayList<AdminUserModel> filteredList) {
        adminUserModelList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvNoTelp;
        CardView cvListUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.txtUsername);
            tvNoTelp = itemView.findViewById(R.id.txtNoTelp);
            cvListUser = itemView.findViewById(R.id.cvListUser);
        }
    }
}
