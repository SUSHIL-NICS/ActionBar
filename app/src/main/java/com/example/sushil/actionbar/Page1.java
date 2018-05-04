package com.example.sushil.actionbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.sushil.actionbar.R;
import com.example.sushil.actionbar.AnimationItem;
/**
 * Created by sushil on 13-03-2017.
 */
public class Page1 extends Fragment   {
   private View view;
     RecyclerView mRecyclerView;
     RecyclerView mRecyclerView1;
    private List<String> list = new ArrayList<String>();
    private List<String> list1 = new ArrayList<String>();
    private List<String> list2 = new ArrayList<String>();
    SimpleAdapter simpleAdapter;
    EditText search;
   private static final int[] IMAGES= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
     View rootview = inflater.inflate(R.layout.fragment_page1, container, false);
                return rootview;

    }

   /* @Override
    protected int getLayoutResId() {
        return R.layout.fragment_page1;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Override
    protected AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Slide from right", R.anim.layout_animation_fall_down),
                new AnimationItem("Slide from right", R.anim.layout_animation_fall_down)
        };
    }

    @Override
    public void onClick(View v) {

    }*/

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search = (EditText) view.findViewById( R.id.search);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview1);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(
                        1, //The number of Columns in the grid
                        LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,true);

        StaggeredGridLayoutManager staggeredGridLayoutManager1 =
                new StaggeredGridLayoutManager(
                        3, //The number of Columns in the grid
                        LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView1.setLayoutManager(staggeredGridLayoutManager1);
        countryList();
        countryList1();

        addTextListener();  //for Search Country
        simpleAdapter=new SimpleAdapter(list,list1,getActivity());
        mRecyclerView.setAdapter(simpleAdapter);
        mRecyclerView1.setAdapter(simpleAdapter);
        //registerForContextMenu((View) list);
        view.setOnCreateContextMenuListener(this);
    }

    /*//used to show context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_second,menu);
    }

    //used for event click of context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.action_delete:
                Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                //list.remove(info.position);
                //simpleAdapter.notifyItemRemoved(info.position);
                return true;
            //default:
                //return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }*/

    public void countryList(){

        list.add("Afghanistan");
        list.add("Albania");
        list.add("Algeria");
        list.add("Bangladesh");
         }
    public void countryList1(){

        list1.add("Afghanistannn");
        list1.add("Albania");
        list1.add("Algeria");
        list1.add("Bangladesh");
    }

    //for Sesrch Country
     public void addTextListener(){

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<String> filteredList = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {

                    final String text = list.get(i).toLowerCase();
                    if (text.contains(query)) {

                        filteredList.add(list.get(i));
                    }
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                mRecyclerView.setLayoutManager(layoutManager);
                simpleAdapter = new SimpleAdapter(filteredList,filteredList, getContext());
                mRecyclerView.setAdapter(simpleAdapter);
                simpleAdapter.notifyDataSetChanged();  // data set changed
            }
        });
    }

    /*@Override
    public void onClick(View v) {

    }*/
}