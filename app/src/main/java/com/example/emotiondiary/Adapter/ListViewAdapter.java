package com.example.emotiondiary.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emotiondiary.Model.ListViewModel;
import com.example.emotiondiary.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewModel> list;

    public ListViewAdapter(ArrayList<ListViewModel>list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0; //알아서 공부
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_lvitem,null,false
        );
        ListViewModel data = list.get(position);

        ImageView contentImg = view.findViewById(R.id.contentImg);
        TextView contentText = view.findViewById(R.id.contentText);
        TextView contentDate = view.findViewById(R.id.contentDate);

        contentText.setText(data.getContentText());
        contentImg.setImageResource(data.getContentImg());
        contentDate.setText(data.getContentDate());
        return view;
    }

}
