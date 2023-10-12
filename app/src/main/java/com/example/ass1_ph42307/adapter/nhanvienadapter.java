package com.example.ass1_ph42307.adapter;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ass1_ph42307.R;
import com.example.ass1_ph42307.model.nhanvien;
import com.example.ass1_ph42307.model.phongban;

import java.util.ArrayList;

public class nhanvienadapter extends BaseAdapter {
    private final Context context;
//    private final ArrayList<nhanvien> listStudent;
    private ArrayList<nhanvien> listStudent;
    private final ArrayList<nhanvien> list;

    public nhanvienadapter(Context context, ArrayList<nhanvien> list) {
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
        view = inflater.inflate(R.layout.layout_nhanvien, viewGroup, false);
        TextView txtmsv = view.findViewById(R.id.txtmsv);
        TextView txtname = view.findViewById(R.id.txtname);
        TextView txtphongban = view.findViewById(R.id.txtphongban);
        ImageButton btndelete = view.findViewById(R.id.btndelete);
        ImageButton btnupdate = view.findViewById(R.id.btnupdate);
        txtmsv.setText(list.get(i).getMsv());
        txtname.setText(list.get(i).getName());
        txtphongban.setText(list.get(i).getphongban());
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            opendialog(list.get(i));
            }
        });
        return view;

    }
    public void opendialog(nhanvien nv){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_nhanvien_update,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        //anh xa
        EditText txtmnv = view.findViewById(R.id.txtmnv_ud);
        EditText txtten = view.findViewById(R.id.txtname_up);
        EditText txtphongban = view.findViewById(R.id.txtpb_up);
        Button btnupdate=view.findViewById(R.id.btnupd);
        //gán du lieu lên các textview
        txtmnv.setText(nv.getMsv());
        txtten.setText(nv.getName());
        txtphongban.setText(nv.getphongban());
        //click update
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nv.setMsv(txtmnv.getText().toString());
                nv.setName(txtten.getText().toString());
                nv.setphongban(txtphongban.getText().toString());
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });
    }
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String s = constraint.toString();
                if (s.isEmpty()) {
                    listStudent= list ;
                } else {
                    ArrayList<nhanvien> lists = new ArrayList<>();
                    for (nhanvien st : list) {
                        if (st.getName().toLowerCase().contains(s.toLowerCase())) {
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
            protected void publishResults(CharSequence constraint, FilterResults results) {
            listStudent=(ArrayList<nhanvien>)results.values;
            notifyDataSetChanged();
            }
        };
    }
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String s = charSequence.toString();
//                if (s.isEmpty()) {
//                    list= listStudent ;
//                } else {
//                    ArrayList<nhanvien> lists = new ArrayList<>();
//                    for (nhanvien st : list) {
//                        if (st.getName().toLowerCase().contains(s.toLowerCase())) {
//                            lists.add(st);
//                        }
//                    }
//                    list = lists;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = list;
//                return filterResults;
//            }
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                list = (ArrayList<nhanvien>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}

