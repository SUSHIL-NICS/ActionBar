package com.example.sushil.actionbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushil on 30-07-2017.
 */

public class Page2Adpter extends RecyclerView.Adapter<Page2Adpter.MyviewHolder1>{
    Context context;
    ArrayList<ActionbarDto> list_item;

    public Page2Adpter(ArrayList<ActionbarDto> items, Context context) {
        this.context=context;
        list_item=items;
    }

    @Override
    public MyviewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pagelistitem, null);
        MyviewHolder1 myViewHolder = new MyviewHolder1(view,context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder1 holder, int position) {
       final ActionbarDto actionbarDto=list_item.get(position);
       //final ActionbarDto actionbarDto=list_item.get(position);
        holder.textView.setText(actionbarDto.getCountry());
        holder.imageView.setImageResource(actionbarDto.getImage());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,actionbarDto.getCountry(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    public class MyviewHolder1 extends RecyclerView.ViewHolder {
        public TextView textView;
        ImageView imageView;
        Context context;
        //List<ActionbarDto> list;
        public MyviewHolder1(View itemView,Context context) {
            super(itemView);
            this.context=context;
           // this.list=list;
            textView=(TextView) itemView.findViewById(R.id.text1);
            imageView=(ImageView)itemView.findViewById(R.id.imageView4);
        }
    }
}
