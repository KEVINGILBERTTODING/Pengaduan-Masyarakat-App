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

import com.example.pengaduanmasyarakat.Adapter.AllKecamatanAdapter;
import com.example.pengaduanmasyarakat.Model.KecamatanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.KecamatanInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListKecamatanFragment extends Fragment {

    RecyclerView rvKecamatan;
    List<KecamatanModel> kecamatanModelList;
    LinearLayoutManager linearLayoutManager;
    AllKecamatanAdapter allKecamatanAdapter;
    SearchView searchView;
    FloatingActionButton fabInsert;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_list_kecamatan, container, false);

      searchView = view.findViewById(R.id.searchBar);
      rvKecamatan = view.findViewById(R.id.rvKecamatan);
      fabInsert = view.findViewById(R.id.fabKecamatan);

        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Memuat data...");
        pd.setCanceledOnTouchOutside(false);
        pd.show();


      // display list kecamatan
        KecamatanInterface ki = DataApi.getClient().create(KecamatanInterface.class);
        ki.getKecamatan().enqueue(new Callback<List<KecamatanModel>>() {
            @Override
            public void onResponse(Call<List<KecamatanModel>> call, Response<List<KecamatanModel>> response) {
                if (response.isSuccessful()) {
                    kecamatanModelList = response.body();
                    if (kecamatanModelList.size() > 0) {
                        allKecamatanAdapter = new AllKecamatanAdapter(getContext(), kecamatanModelList);
                        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rvKecamatan.setLayoutManager(linearLayoutManager);
                        rvKecamatan.setAdapter(allKecamatanAdapter);
                        rvKecamatan.setHasFixedSize(true);
                        pd.dismiss();

                    }else {
                        Toasty.error(getContext(), "Gagal memuat data", Toasty.LENGTH_SHORT).show();
                        pd.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<KecamatanModel>> call, Throwable t) {
                pd.dismiss();
                Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();



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
        fabInsert.setOnClickListener(View -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_admin_container, new InsertKecamatanFragment())
                    .addToBackStack(null)
                    .commit();
        });





      return view;
    }

    private void filter(String newText){
        ArrayList<KecamatanModel>filteredList = new ArrayList<>();
        for (KecamatanModel item : kecamatanModelList) {
            if (item.getKecamatan().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        allKecamatanAdapter.filterList(filteredList);
        if (filteredList.isEmpty()) {
            Toasty.normal(getContext(), "Tidak ditemukan", Toasty.LENGTH_SHORT).show();
        }else {
            allKecamatanAdapter.filterList(filteredList);
        }
    }
}