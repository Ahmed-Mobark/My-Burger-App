package com.example.myburger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myburger.R;
import com.example.myburger.SpecialOf_Data;
import com.example.myburger.SpecialOf_RvAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
RecyclerView SpecialOffRecyclerView;
List<SpecialOf_Data> specialList=new ArrayList<>();
SpecialOf_RvAdapter specialOf_rvAdaper;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_menu, container, false);

        SpecialOffRecyclerView=view.findViewById(R.id.SpecialOff_Rv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SpecialOf_Data d1=new SpecialOf_Data("Buff burgerr",80,R.drawable.b3);
        SpecialOf_Data d2=new SpecialOf_Data("Checken burgerr",90,R.drawable.b1);
        SpecialOf_Data d3=new SpecialOf_Data("Meat burgerr",120,R.drawable.b2);
        SpecialOf_Data d4=new SpecialOf_Data("Bomm burgerr",70,R.drawable.b4);
        SpecialOf_Data d5=new SpecialOf_Data("Hot burgerr",120,R.drawable.b5);
        specialList.add(d1);
        specialList.add(d2);
        specialList.add(d3);
        specialList.add(d4);
        specialList.add(d5);
        specialList.add(d3);
        specialList.add(d5);
        specialList.add(d4);
        specialList.add(d3);
        specialList.add(d1);
        specialList.add(d2);

        SpecialOffRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        specialOf_rvAdaper=new SpecialOf_RvAdapter(specialList,requireContext());
        SpecialOffRecyclerView.setAdapter(specialOf_rvAdaper);
    }
}