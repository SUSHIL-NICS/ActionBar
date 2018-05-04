package com.example.sushil.actionbar;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class TextItemViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener{
    private TextView textView;
    LongClickListener longClickListener;

    public TextItemViewHolder(View itemView) {
        super(itemView);
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
        menu.add(0,0,0,"share");
        menu.add(0,1,0,"rate");
        menu.add(0,2,0,"delete");

    }


}
