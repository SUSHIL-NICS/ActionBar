package com.example.sushil.actionbar;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder>{
    private List<String> list_item;
    private List<String> list_item1;
    public Context context;
    private static final int[] IMAGES= {R.drawable.exam,R.drawable.plate,R.drawable.splash,R.drawable.java};

    public SimpleAdapter(List<String> listt,List<String> list_item1,Context context) {
        list_item = listt;
        this.list_item1=list_item1;
        this.context = context;
    }

    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.

    @Override
    public SimpleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view,context,list_item,list_item1);
        return myViewHolder;
    }


    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(final SimpleAdapter.MyViewHolder myViewHolder, final int position ) {
        final String l=list_item.get(position);
        final String k=list_item1.get(position);
        myViewHolder.country_name.setText(k);
        myViewHolder.country_name1.setText(l);
        myViewHolder.imageView.setImageResource(IMAGES[position]);

        myViewHolder.country_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,l, Toast.LENGTH_LONG).show();
            }
        });


        myViewHolder.textViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a popup menu
                PopupMenu popup = new PopupMenu(context, myViewHolder.textViewOptions);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu_second);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_settings:
                                //handle menu1 click
                                break;
                            case R.id.action_search:
                                //handle menu2 click
                                break;
                            case R.id.action_delete:

                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });

        myViewHolder.remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String theRemovedItem = list_item.get(position);
                // remove your item from data base
                list_item.remove(position);  // remove the item from list
                notifyItemRemoved(position); // notify the adapter about the removed item
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }

    // initializes some private fields to be used by RecyclerView.
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        public TextView country_name;
        public TextView country_name1;
        public ImageView imageView;
        public  TextView textViewOptions;
        Button remove;
        Context context1;
        List<String> list_item;
        List<String> list_item1;

        public MyViewHolder(View itemLayoutView, final Context context, List<String> list_item, List<String> list_item1) {
            super(itemLayoutView);
            this.context1=context;
            this.list_item=list_item;
            this.list_item1=list_item1;
            itemLayoutView.setOnClickListener(this);
            itemLayoutView.setOnCreateContextMenuListener(this);
            //for context menu
            /*itemLayoutView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    menu.add("delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                               Toast.makeText(context,"check",Toast.LENGTH_SHORT).show();
                            //do what u want
                            return true;
                        }
                    });
                }
            });*/
            itemLayoutView.setSelected(true);//for cardview color change

            country_name = (TextView) itemLayoutView.findViewById(R.id.country_name);
            country_name1 = (TextView) itemLayoutView.findViewById(R.id.country_name1);
            imageView=(ImageView)itemLayoutView.findViewById(R.id.imageView);
            textViewOptions=(TextView)itemLayoutView.findViewById(R.id.textViewOptions);
            remove=(Button)itemLayoutView.findViewById(R.id.remove);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            String l=this.list_item.get(position);
            Toast.makeText(context1,list_item.get(position), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(view.getContext(),ShowActivity.class);
            i.putExtra("x",IMAGES[position]);
            i.putExtra("y",list_item.get(position));
            this.context1.startActivity(i);
        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("logo");

            MenuItem share=menu.add(0,0,0,"share");
            share.setOnMenuItemClickListener(onEditMenu);

            menu.add(0,1,0,"rate").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(context1,"rate", Toast.LENGTH_SHORT).show();
                    //do what u want
                    return true;
                }
            });
            menu.add(0,2,0,"delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(context1,"delete",Toast.LENGTH_SHORT).show();
                    //do what u want
                    return true;
                }
            });
        }

        //ADD AN ONMENUITEM LISTENER TO EXECUTE COMMANDS ONCLICK OF CONTEXT MENU TASK
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 0:
                        Toast.makeText(context1,"share", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        };

    }

}

