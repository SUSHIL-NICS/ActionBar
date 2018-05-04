package com.example.sushil.actionbar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sushil.actionbar.Database.DataBaseHelper;

import java.util.ArrayList;


public class Page2 extends Fragment {
   Page2Adpter page2Adpter;
   RecyclerView recyclerView;
    ArrayList<ActionbarDto> actionbarDtos=new ArrayList<>();
    String[] medicine={"cipla","q","w","e","r","t","cipla","q","w","e","r","t"};
    private static final int[] IMAGESS= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java,R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java,R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        for(int i=0;i<=medicine.length-1;i++)
        {
            //ActionbarDto list=new ActionbarDto(medicine[i],IMAGES[i]);
            ActionbarDto actionbarDto=new ActionbarDto();
            actionbarDto.setCountry(medicine[i]);
            actionbarDto.setImage(IMAGESS[i]);
            actionbarDtos.add(actionbarDto);
        }
        page2Adpter = new Page2Adpter(actionbarDtos,getContext());
        recyclerView.setAdapter(page2Adpter);
    }
}
