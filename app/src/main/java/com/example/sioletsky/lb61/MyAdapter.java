package com.example.sioletsky.lb61;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    String [] name;
    String [] tall;
    String [] weigh;
    public MyAdapter(Context c, String[] key, String[] value, String[] weigh){ //建構子
        inflater = LayoutInflater.from(c);
        this.name=key;
        this.tall=value;
        this.weigh=weigh;
    }
    @Override
    public int getCount(){
        return name.length;
    }
    @Override
    public Object getItem(int i){
        return null;
    }
    @Override
    public long getItemId(int i){
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewgroup){
        view=inflater.inflate(R.layout.text,viewgroup,false);
        TextView name2,tall2,weigh2;
        name2=(TextView) view.findViewById(R.id.textView6);
        tall2=(TextView)view.findViewById(R.id.textView7);
        weigh2=(TextView) view.findViewById(R.id.textView8);
        name2.setText(name[i]);
        tall2.setText(tall[i]);
        weigh2.setText(weigh[i]);
        return view;
    }

}
