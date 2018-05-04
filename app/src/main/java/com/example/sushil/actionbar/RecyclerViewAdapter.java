package com.example.sushil.actionbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by anupamchugh on 05/10/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.TextItemViewHolder> {
    Context context;
    String[] items;

    public RecyclerViewAdapter(String[] items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.TextItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_item, parent, false);
        return new TextItemViewHolder(view,context,items);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.TextItemViewHolder holder, final int position) {
        holder.bind(items[position]);
        holder.setLongClickListener(new LongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                Toast.makeText(context, items[pos], Toast.LENGTH_LONG).show();
                final CharSequence[] items = {"Supprimer", "etc", "etc1"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Select The Action");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item==0){
                            Toast.makeText(context, "Supprimer", Toast.LENGTH_LONG).show();

                        }else if (item==1){
                            Toast.makeText(context, "etc", Toast.LENGTH_LONG).show();

                        }else if (item==2){
                            Toast.makeText(context, "etc1", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return items.length;
    }


    public static class TextItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener {
        private TextView textView;
        private Context context;
        private String[] items;
        LongClickListener longClickListener;

        public TextItemViewHolder(View itemView, Context context, String[] items) {
            super(itemView);
            this.context=context;
            this.items=items;
            textView = (TextView) itemView.findViewById(R.id.list_item);
            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(String text) {
            textView.setText(text);
        }

        public void setLongClickListener(LongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }

        @Override
        public boolean onLongClick(View view) {
            this.longClickListener.onItemLongClick(getLayoutPosition());
            return false;
        }

        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("logo");
            menu.add(0, 0, 0, "share").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(context,items[getPosition()],Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
            menu.add(0, 1, 0, "rate");
            menu.add(0, 2, 0, "delete");

        }


    }
}
