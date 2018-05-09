package com.huayou.acc.acc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huayou.acc.acc.R;
import com.huayou.acc.acc.bean.P;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lzx on 2016/12/30.
 */

public class DataAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<P> p;
    public DataAdapter(Context context) {
        this.context=context;
        this.p=new ArrayList<>();
    }
    //添加数据
    public void addList(ArrayList<P> list) {
        if (this.p.containsAll(list)) {
            return;
        }
        this.p.addAll(list);
        notifyDataSetChanged();
    }

    //更新数据
    public void updateList(ArrayList<P> list) {
        this.p = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder{
        TextView name,age;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.age= (TextView) convertView.findViewById(R.id.age);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(p.get(position).getName());
        viewHolder.age.setText(p.get(position).getAge());
        return convertView;
    }
}
