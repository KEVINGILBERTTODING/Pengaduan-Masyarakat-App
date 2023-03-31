package com.example.pengaduanmasyarakat.Fragment.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pengaduanmasyarakat.Adapter.MyPengaduanAdapter;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyPengaduanFragment extends Fragment {

    RecyclerView rvMyPengaduan;
    TextView tvTitle;
    String statusPengaduan, idMasyarakat;
    List<PengaduanModel> pengaduanModelList;
    MyPengaduanAdapter myPengaduanAdapter;
    PengaduanInterface pengaduanInterface;
    SharedPreferences sharedPreferences;
    LinearLayoutManager linearLayoutManager;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_pengaduan, container, false);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);



        tvTitle = view.findViewById(R.id.title1);
        rvMyPengaduan = view.findViewById(R.id.rvMyPengaduan);
        statusPengaduan = getArguments().getString("jenis");
        idMasyarakat = sharedPreferences.getString("user_id", null);
        tvTitle.setText(getArguments().getString("judul"));

        getMyPengaduan(statusPengaduan);





        return view;
    }

    public void getMyPengaduan(String jenis){
        pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getMyPengaduan(idMasyarakat, jenis).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                pengaduanModelList = response.body();
                if (pengaduanModelList.size() > 0) {
                    myPengaduanAdapter = new MyPengaduanAdapter(getContext(), pengaduanModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rvMyPengaduan.setLayoutManager(linearLayoutManager);
                    rvMyPengaduan.setAdapter(myPengaduanAdapter);
                    rvMyPengaduan.setHasFixedSize(true);

                }
            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {
                Toasty.normal(getContext(), "Tidak ada pengaduan", Toasty.LENGTH_SHORT).show();

            }
        });

    }
}