package com.example.ass1_ph42307.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ass1_ph42307.R;
import com.example.ass1_ph42307.model.phongban;

import java.util.ArrayList;

public class pbadapter extends BaseAdapter {
    private final Context context;
    private ArrayList<phongban> listStudent;
    private final ArrayList<phongban> list;

    public pbadapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        view=inflater.inflate(R.layout.layout_phongban, viewGroup,false);
        view = inflater.inflate(R.layout.layout_phongban, viewGroup, false);
        ImageView imghinh = view.findViewById(R.id.imghinh);
        TextView txtcoso = view.findViewById(R.id.txtcoso);
        imghinh.setImageResource(list.get(i).getHinh());
        txtcoso.setText(list.get(i).getTencs());
        return view;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String s = charSequence.toString();
                if (s.isEmpty()) {
                    listStudent = list;
                } else {
                    ArrayList<phongban> lists = new ArrayList<>();
                    for (phongban st : list) {
                        if (st.getTencs().toLowerCase().contains(s.toLowerCase())) {
                            lists.add(st);
                        }
                    }
                    listStudent = lists;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listStudent;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listStudent = (ArrayList<phongban>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
