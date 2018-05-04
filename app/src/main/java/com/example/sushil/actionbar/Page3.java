package com.example.sushil.actionbar;

/**
 * Created by sushil on 30-07-2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sushil.actionbar.Database.DataBaseHelper;
import java.util.ArrayList;

public class Page3 extends Fragment {
    Page3Adapter page3Adapter;
    RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper ;
    ArrayList<ActionbarDto1> actionbarDtos1=new ArrayList<>();
    String[] medicine={"cipla","q","w","e","r","t","cipla","q","w","e","r","t"};
    private static final int[] IMAGESS= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java,R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java,R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_page3,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseHelper=new DataBaseHelper(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        for(int i=0;i<=medicine.length-1;i++)
        {
            ActionbarDto1 list=new ActionbarDto1();
            list.setCountry(medicine[i]);
            list.setImage(IMAGESS[i]);
            //actionbarDtos.add(list);
            dataBaseHelper.insertHistory(list);
        }
        actionbarDtos1=dataBaseHelper.getCartItemFavorite();
        page3Adapter = new Page3Adapter(actionbarDtos1,getContext());
        recyclerView.setAdapter(page3Adapter);
    }
}
