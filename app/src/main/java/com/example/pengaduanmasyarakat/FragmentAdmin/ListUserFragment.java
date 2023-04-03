package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengaduanmasyarakat.Adapter.DaftarUserAdapter;
import com.example.pengaduanmasyarakat.Model.AdminUserModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.UserInterface;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListUserFragment extends Fragment {

    RecyclerView rvListUser;

    List<AdminUserModel> adminUserModelList;
    LinearLayoutManager linearLayoutManager;
    UserInterface userInterface;
    DaftarUserAdapter daftarUserAdapter;
    SharedPreferences sharedPreferences;
    SearchView searchbar;

    ProgressDialog pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        sharedPreferences = getContext().getSharedPreferences("user_data", Context.MODE_PRIVATE);


        rvListUser = view.findViewById(R.id.rvListUser);
        searchbar = view.findViewById(R.id.searchBar);
        pd = new ProgressDialog(getContext());
        pd.setMessage("Memuat data..");
        pd.show();



        userInterface = DataApi.getClient().create(UserInterface.class);

        // get data user
        userInterface.getAllUser(sharedPreferences.getString("id_user", null))
                .enqueue(new Callback<List<AdminUserModel>>() {
                    @Override
                    public void onResponse(Call<List<AdminUserModel>> call, Response<List<AdminUserModel>> response) {
                        adminUserModelList = response.body();
                        if (adminUserModelList.size() > 0) {
                            daftarUserAdapter = new DaftarUserAdapter(getContext(), adminUserModelList);
                            linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            rvListUser.setLayoutManager(linearLayoutManager);
                            rvListUser.setAdapter(daftarUserAdapter);
                            rvListUser.setHasFixedSize(true);
                            pd.dismiss();
                            daftarUserAdapter.notifyDataSetChanged();




                        }else {
                            Toasty.error(getContext(), "gagal memuat data..", Toasty.LENGTH_SHORT).show();
                            pd.dismiss();
                        }


                    }

                    @Override
                    public void onFailure(Call<List<AdminUserModel>> call, Throwable t) {
                        Toasty.error(getContext(), "Periksa koneksi anda", Toasty.LENGTH_SHORT).show();
                        Log.e("gagal", "ini errornya", t);
                        pd.dismiss();

                    }
                });

        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        ArrayList<AdminUserModel>filteredList = new ArrayList<>();
        for (AdminUserModel item : adminUserModelList) {
            if (item.getUserName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        daftarUserAdapter.filterList(filteredList);
        if (filteredList.isEmpty()) {
            Toasty.normal(getContext(), "Tidak ada data", Toasty.LENGTH_SHORT).show();
        }else {
            daftarUserAdapter.filterList(filteredList);
        }

    }


}