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

import com.example.pengaduanmasyarakat.Adapter.ListMasyarakatAdapter;
import com.example.pengaduanmasyarakat.Model.UserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.MasyarakatInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListMasyarakatFragment extends Fragment {


    SearchView searchView;
    FloatingActionButton fabInsertMasyarakat;
    RecyclerView rvMasyarakat;
    List<UserModel>userModelList;
    ListMasyarakatAdapter listMasyarakatAdapter;
    LinearLayoutManager linearLayoutManager;
    MasyarakatInterface  masyarakatInterface;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_masyarakat, container, false);

        searchView = view.findViewById(R.id.searchBar);
        fabInsertMasyarakat = view.findViewById(R.id.fabInsertMasyarakat);
        rvMasyarakat = view.findViewById(R.id.rvListMasyarakat);
        masyarakatInterface = DataApi.getClient().create(MasyarakatInterface.class);
        ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Memuat data...");
        pd.setCanceledOnTouchOutside(false);
        pd.show();

        // display masyarakat
        masyarakatInterface.getAllMasyarakat().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if (response.isSuccessful()) {
                    userModelList = response.body();
                    if (userModelList.size() >0 ) {
                        listMasyarakatAdapter = new ListMasyarakatAdapter(getContext(), userModelList);
                        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rvMasyarakat.setLayoutManager(linearLayoutManager);
                        rvMasyarakat.setAdapter(listMasyarakatAdapter);
                        rvMasyarakat.setHasFixedSize(true);
                        pd.dismiss();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toasty.error(getContext(), "Cek koneksi internet anda", Toasty.LENGTH_SHORT).show();
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
        ArrayList<UserModel>filteredList = new ArrayList<>();
        for (UserModel item : userModelList) {
            if (item.getUserName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        listMasyarakatAdapter.filterList(filteredList);
        if (filteredList.isEmpty()) {
            Toasty.normal(getContext(), "Tidak ada data", Toasty.LENGTH_SHORT).show();
        }else {
            listMasyarakatAdapter.filterList(filteredList);
        }
    }
}