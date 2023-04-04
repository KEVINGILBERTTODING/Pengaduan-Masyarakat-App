package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengaduanmasyarakat.Adapter.MyPengaduanAdapter;
import com.example.pengaduanmasyarakat.Adapter.PengaduanAdapter;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.AdminPengaduanInterface;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PengaduanFragment extends Fragment {

    RecyclerView rvMyPengaduan;
    TextView tvTitle;
    String statusPengaduan, idMasyarakat;
    List<PengaduanModel> pengaduanModelList;
    PengaduanAdapter pengaduanAdapter;
    AdminPengaduanInterface pengaduanInterface;
    SharedPreferences sharedPreferences;
    LinearLayoutManager linearLayoutManager;
    ProgressDialog progressDialog;
    TextView tvEmpty;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Memuat data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        tvEmpty = view.findViewById(R.id.titleEmpty);





        tvTitle = view.findViewById(R.id.title1);
        rvMyPengaduan = view.findViewById(R.id.rvMyPengaduan);
        statusPengaduan = getArguments().getString("jenis");
        idMasyarakat = sharedPreferences.getString("user_id", null);
        tvTitle.setText(getArguments().getString("judul"));

        getMyPengaduan(statusPengaduan);





        return view;
    }

    public void getMyPengaduan(String jenis){
        pengaduanInterface = DataApi.getClient().create(AdminPengaduanInterface.class);
        pengaduanInterface.getAllPengaduanByStatus(jenis).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                pengaduanModelList = response.body();
                if (pengaduanModelList.size() > 0) {
                    pengaduanAdapter = new PengaduanAdapter(getContext(), pengaduanModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rvMyPengaduan.setLayoutManager(linearLayoutManager);
                    rvMyPengaduan.setAdapter(pengaduanAdapter);
                    rvMyPengaduan.setHasFixedSize(true);
                    progressDialog.dismiss();
                    tvEmpty.setVisibility(View.GONE);


                }else {
                    progressDialog.dismiss();
                    tvEmpty.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                Toasty.normal(getContext(), "Tidak ada pengaduan", Toasty.LENGTH_SHORT).show();
                progressDialog.dismiss();
                tvEmpty.setVisibility(View.VISIBLE);


            }
        });

    }
}