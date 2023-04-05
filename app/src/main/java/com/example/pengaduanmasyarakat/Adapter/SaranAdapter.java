package com.example.pengaduanmasyarakat.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.FragmentAdmin.DetailSaranFragment;
import com.example.pengaduanmasyarakat.Model.SaranModel;
import com.example.pengaduanmasyarakat.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SaranAdapter extends RecyclerView.Adapter<SaranAdapter.ViewHolder> {

    Context context;
    List<SaranModel>saranModels;

    public SaranAdapter(Context context, List<SaranModel> saranModels) {
        this.context = context;
        this.saranModels = saranModels;
    }

    @NonNull
    @Override
    public SaranAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_list_saran, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaranAdapter.ViewHolder holder, int position) {
        holder.tvNama.setText(saranModels.get(position).getNamaa());
        holder.tvNoTelp.setText(saranModels.get(position).getNoTelp());

    }

    @Override
    public int getItemCount() {
        return saranModels.size();
    }

    public void filterList(ArrayList<SaranModel>filteredList){
        saranModels = filteredList;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvSaran;
        TextView tvNama, tvNoTelp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            cvSaran = itemView.findViewById(R.id.cvSaran);
            tvNama = itemView.findViewById(R.id.txtUsername);
            tvNoTelp = itemView.findViewById(R.id.txtNoTelp);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Fragment fragment = new DetailSaranFragment();
            Bundle bundle = new Bundle();
            bundle.putString("tanggal", saranModels.get(getAdapterPosition()).getTglSaran());
            bundle.putString("nama", saranModels.get(getAdapterPosition()).getNamaa());
            bundle.putString("no_telp", saranModels.get(getAdapterPosition()).getNoTelp());
            bundle.putString("alamat", saranModels.get(getAdapterPosition()).getAlamat());
            bundle.putString("saran", saranModels.get(getAdapterPosition()).getSaran());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager()
                    .beginTransaction().replace(R.id.frame_admin_container, fragment)
                    .addToBackStack(null)
                    .commit();

        }
    }
}
