package com.example.pengaduanmasyarakat.FragmentAdmin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pengaduanmasyarakat.R;


public class AdminHomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_admin_home, container, false);


       return view;
    }
}