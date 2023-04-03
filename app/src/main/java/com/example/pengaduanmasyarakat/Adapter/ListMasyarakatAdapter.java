package com.example.pengaduanmasyarakat.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.MasyarakatInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMasyarakatAdapter extends RecyclerView.Adapter<ListMasyarakatAdapter.ViewHolder> {


    Context context;
    List<UserModel>userModelList;
    MasyarakatInterface masyarakatInterface;
    UserModel userModel;

    public ListMasyarakatAdapter(Context context, List<UserModel> userModelList) {
        this.context = context;
        this.userModelList = userModelList;
    }

    @NonNull
    @Override
    public ListMasyarakatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_masyarakat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMasyarakatAdapter.ViewHolder holder, int position) {

        holder.tvUsername.setText(userModelList.get(position).getUserName());
        holder.tvNoTelp.setText(userModelList.get(position).getNoTelp());
        holder.cvListMasyarakat.setOnClickListener(View ->{

            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.layout_option_menu_list_user);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            final Button btnDelete, btnEdit;
            btnDelete = dialog.findViewById(R.id.btnDeleteUser);
            btnEdit = dialog.findViewById(R.id.btnEditUser);
            dialog.show();

            btnDelete.setOnClickListener(view -> {
                dialog.dismiss();
                MaterialAlertDialogBuilder md = new MaterialAlertDialogBuilder(context);
                md.setTitle("Peringatan!");
                md.setMessage("Apakah anda yakin ingin mengahapus user ini?");
                md.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProgressDialog pd = new ProgressDialog(context);
                        pd.setTitle("Menghapus data...");
                        pd.show();

                        masyarakatInterface = DataApi.getClient().create(MasyarakatInterface.class);
                        masyarakatInterface.deleteMasyarakat(userModelList.get(position).getIdMasyarakat())
                                .enqueue(new Callback<UserModel>() {
                                    @Override
                                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                                        if (response.isSuccessful()) {
                                            userModel = response.body();
                                            if (userModel.getStatus().equals("success")) {
                                                pd.dismiss();
                                                Toasty.success(context, userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                                notifyDataSetChanged();
                                                notifyItemRangeChanged(position, userModelList.size());
                                                notifyItemRemoved(position);
                                                userModelList.remove(position);

                                            }else {
                                                pd.dismiss();
                                                Toasty.error(context, userModel.getMessage(), Toasty.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserModel> call, Throwable t) {
                                        Toasty.error(context, "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();


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
            });

        });

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public void filterList (ArrayList<UserModel>filteredList) {
        userModelList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvListMasyarakat;
        TextView tvUsername, tvNoTelp;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvNoTelp = itemView.findViewById(R.id.txtNoTelp);
            cvListMasyarakat = itemView.findViewById(R.id.cvListMasyarakat);
        }
    }
}
