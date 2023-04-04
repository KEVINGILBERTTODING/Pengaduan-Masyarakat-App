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

import com.example.pengaduanmasyarakat.Adapter.AllKelurahanAdapter;
import com.example.pengaduanmasyarakat.Adapter.KelurahanAdapter;
import com.example.pengaduanmasyarakat.Model.KelurahanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KelurahanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListKelurahanFragment extends Fragment {
    RecyclerView rvKelurahan;
    FloatingActionButton fabInsert;
    KelurahanModel kelurahanModel;
    List<KelurahanModel> kelurahanModelList;
    AllKelurahanAdapter kelurahanAdapter;
    LinearLayoutManager linearLayoutManager;
    SearchView searchView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_kelurahan, container, false);
        rvKelurahan = view.findViewById(R.id.rvKelurahan);
        fabInsert = view.findViewById(R.id.fabKelurahan);
        searchView = view.findViewById(R.id.searchBar);


        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Memuat data...");
        pd.setCanceledOnTouchOutside(false);
        pd.show();


        KelurahanInterface kl = DataApi.getClient().create(KelurahanInterface.class);
        kl.getAllKelurahan().enqueue(new Callback<List<KelurahanModel>>() {
            @Override
            public void onResponse(Call<List<KelurahanModel>> call, Response<List<KelurahanModel>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    kelurahanModelList = response.body();
                    kelurahanAdapter = new AllKelurahanAdapter(getContext(), kelurahanModelList);
                    linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    rvKelurahan.setLayoutManager(linearLayoutManager);
                    rvKelurahan.setAdapter(kelurahanAdapter);
                    rvKelurahan.setHasFixedSize(true);
                    pd.dismiss();

                }else {
                    Toasty.error(getContext(), "Gagal memuat data", Toasty.LENGTH_SHORT).show();
                    pd.dismiss();

                }
            }

            @Override
            public void onFailure(Call<List<KelurahanModel>> call, Throwable t) {
                Toasty.error(getContext(), "Periksa koneksi anda", Toasty.LENGTH_SHORT).show();
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


        fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_admin_container, new InsertKelurahanFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });




        return view;
    }


    private void filter(String newText){
        ArrayList<KelurahanModel>filteredList = new ArrayList<>();
        for (KelurahanModel item : kelurahanModelList) {
            if (item.getKelurahan().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        kelurahanAdapter.filterList(filteredList);
        if (filteredList.isEmpty()) {
            Toasty.normal(getContext(), "Tidak ditemukan", Toasty.LENGTH_SHORT).show();
        }else {
            kelurahanAdapter.filterList(filteredList);
        }
    }
}