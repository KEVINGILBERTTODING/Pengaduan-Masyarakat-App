package com.example.pengaduanmasyarakat.Fragment.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.Model.PengaduanModel;
import com.example.pengaduanmasyarakat.R;
import com.example.pengaduanmasyarakat.Util.DataApi;
import com.example.pengaduanmasyarakat.Util.interfaces.PengaduanInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullScreenImageFragment extends Fragment {
    ImageView photoView;
    List<PengaduanModel> pengaduanModelList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        photoView = view.findViewById(R.id.photoView);

        PengaduanInterface pengaduanInterface = DataApi.getClient().create(PengaduanInterface.class);
        pengaduanInterface.getPengaduanById(getArguments().getString("id_pengaduan")).enqueue(new Callback<List<PengaduanModel>>() {
            @Override
            public void onResponse(Call<List<PengaduanModel>> call, Response<List<PengaduanModel>> response) {
                pengaduanModelList = response.body();

                if (pengaduanModelList.size() > 0) {
                    if (getArguments().getString("field").equals("foto")) {
                        Glide.with(getContext())
                                .load(pengaduanModelList.get(0).getFoto())
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(photoView);
                    }else if (getArguments().getString("field").equals("foto1")) {
                        Glide.with(getContext())
                                .load(pengaduanModelList.get(0).getFoto1())
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(photoView);
                    }else if (getArguments().getString("field").equals("foto2")) {
                        Glide.with(getContext())
                                .load(pengaduanModelList.get(0).getFoto2())
                                .dontAnimate()
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(photoView);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<PengaduanModel>> call, Throwable t) {

            }
        });




        return view;
    }


}