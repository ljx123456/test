package com.huayou.acc.acc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huayou.acc.acc.R;
import com.huayou.acc.acc.bean.Map.ListBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alu on 2017/4/28.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<ListBean> list;
    public ListAdapter(Context context) {
        this.context=context;
        list=new ArrayList<>();

    }
    //添加数据
    public void addList(List<ListBean> list){
        if (this.list.containsAll(list)){
            return;
        }
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
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
        TextView tv1,tv2,tv3,tv4;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder.tv1= (TextView) convertView.findViewById(R.id.title);
            viewHolder.tv2= (TextView) convertView.findViewById(R.id.tag);
            viewHolder.tv3= (TextView) convertView.findViewById(R.id.address);
            viewHolder.tv4= (TextView) convertView.findViewById(R.id.price);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv1.setText(list.get(position).getTitle());
        viewHolder.tv2.setText(list.get(position).getTags());
        viewHolder.tv3.setText("地址："+list.get(position).getAddress());
        viewHolder.tv4.setText("电话："+list.get(position).getPhone());
        Picasso.with(context).load("http://192.168.3.9:8080"+list.get(position).getHeader()).error(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);

        return convertView;
    }
}
