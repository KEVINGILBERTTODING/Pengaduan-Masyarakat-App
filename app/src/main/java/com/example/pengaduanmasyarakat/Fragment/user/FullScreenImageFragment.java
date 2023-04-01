package com.example.pengaduanmasyarakat.Fragment.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pengaduanmasyarakat.R;

public class FullScreenImageFragment extends Fragment {
    ImageView photoView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        photoView = view.findViewById(R.id.photoView);


        Glide.with(getContext())
                .load(getArguments().getString("image"))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(photoView);



        return view;
    }
}