package com.example.jinhwan.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Jinhwan on 2017-04-13.
 */

public class RestAdapter extends BaseAdapter implements Filterable {
    ArrayList<Information> data = new ArrayList<Information>();
    ArrayList<Information> filteredData = data;
    Context context;
    Filter listFilter;
    public RestAdapter(ArrayList<Information> data, Context context) {
        this.data = data;
        this.filteredData = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        }
        TextView t1 = (TextView) convertView.findViewById(R.id.tvName);
        TextView t2 = (TextView) convertView.findViewById(R.id.tvTel);
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        CheckBox c1 = (CheckBox) convertView.findViewById(R.id.check);
        Information one = filteredData.get(position);
        one.setCheckBox(c1);
        filteredData.set(position, one);
        data.set(position, one);


        t1.setText(filteredData.get(position).getName());
        t2.setText(filteredData.get(position).getCall());
        img.setImageResource(filteredData.get(position).getCategory());
        return convertView;
    }

    Comparator<Information> nameAsc = new Comparator<Information>() {
        @Override
        public int compare(Information o1, Information o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    Comparator<Information> categoryAsc = new Comparator<Information>() {
        @Override
        public int compare(Information o1, Information o2) {
            if (o1.getCategory() < o2.getCategory())
                return -1;
            else if (o1.getCategory() > o2.getCategory())
                return 1;
            else
                return 0;
        }
    };

    public void setNameAscSort() {
        Collections.sort(filteredData, nameAsc);
        this.notifyDataSetChanged();
    }

    public void setCategoryAscSort() {
        Collections.sort(filteredData, categoryAsc);
        this.notifyDataSetChanged();
    }

    public void showCheckBox() {
        for (int i = 0; i < filteredData.size(); i++) {
            filteredData.get(i).getCheckBox().setVisibility(View.VISIBLE);
        }
        this.notifyDataSetChanged();
    }

    public void deleteData() {
        for (int i = filteredData.size() - 1; i >= 0; i--) {
            final int index = i;
            if (filteredData.get(i).getCheckBox().isChecked()) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("경고")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMessage("선택한 맛집을 정말 삭제합니까")
                        .setNegativeButton("취소", null)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                filteredData.get(index).getCheckBox().setChecked(false);
                                filteredData.get(index).getCheckBox().setVisibility(View.GONE);
                                filteredData.remove(index);
                                notifyDataSetChanged();
                            }
                        })
                        .show();
            } else {
                filteredData.get(i).getCheckBox().setVisibility(View.GONE);
            }
        }
    }
    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter=new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = data;
                results.count = data.size();
            } else {
                ArrayList<Information> itemList = new ArrayList<Information>();
                for (Information item : data) {
                    System.out.println("item : " + item.getName());
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }
                results.values = itemList;
                results.count = itemList.size();

            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Information>) results.values;
            if(results.count>0) {
                notifyDataSetChanged();
            }else{
                notifyDataSetInvalidated();
            }
        }

    }
}

