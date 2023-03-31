package com.example.pengaduanmasyarakat.Fragment.user;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengaduanmasyarakat.Adapter.AllPengaduanAdapter;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class allPengaduanFragment extends Fragment {

    RecyclerView recyclerView;
    List<PengaduanModel> pengaduanModelList;
    LinearLayoutManager linearLayoutManager;

    PengaduanInterface pengaduanInterface;
    ProgressDialog pd;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_all_pengaduan, container, false);

       recyclerView = v.findViewById(R.id.rlAllPengaduan);

       getAllPengaduan();

        pd = new ProgressDialog(getContext());
        pd.setTitle("Memuat data");
        pd.show();

       return v;
    }


    public void getAllPengaduan() {
        pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getAllPengaduan().enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                pengaduanModelList = response.body();
                if (pengaduanModelList.size() > 0) {

                    AllPengaduanAdapter allPengaduanAdapter = new AllPengaduanAdapter(getContext(), pengaduanModelList);

                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(allPengaduanAdapter);
                    recyclerView.setHasFixedSize(true);
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {

            }
        });
    }



}