package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengaduanmasyarakat.Adapter.SaranAdapter;
import com.example.pengaduanmasyarakat.Model.SaranModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.SaranInterface;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListSaranFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    SaranAdapter saranAdapter;
    List<SaranModel>saranModelList;
    SearchView searchView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_saran, container, false);
        recyclerView = view.findViewById(R.id.rvLisSaran);
        searchView = view.findViewById(R.id.searchBar);


        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Memuat data...");
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        // display data
        SaranInterface saranInterface = DataApi.getClient().create(SaranInterface.class);
        saranInterface.getAllSaran().enqueue(new Callback<List<SaranModel>>() {
            @Override
            public void onResponse(Call<List<SaranModel>> call, Response<List<SaranModel>> response) {
                saranModelList = response.body();
                if (response.isSuccessful() && saranModelList.size() > 0) {
                    saranAdapter = new SaranAdapter(getContext(), saranModelList);;
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(saranAdapter);
                    recyclerView.setHasFixedSize(true);
                    pd.dismiss();
                }else {
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<SaranModel>> call, Throwable t) {
                Toasty.error(getContext(), "Periksa koneksi internet anda", Toasty.LENGTH_SHORT).show();
                pd.dismiss();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });






        return view;
    }

    private void filter(String newText) {
        ArrayList<SaranModel>filteredList = new ArrayList<>();
        for (SaranModel item : saranModelList) {
            if (item.getNamaa().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }

        saranAdapter.filterList(filteredList);
        if (filteredList.isEmpty()) {
            Toasty.normal(getContext(), "Tidak ditemukan", Toasty.LENGTH_SHORT).show();
        }else {
            saranAdapter.filterList(filteredList);
        }
    }
}