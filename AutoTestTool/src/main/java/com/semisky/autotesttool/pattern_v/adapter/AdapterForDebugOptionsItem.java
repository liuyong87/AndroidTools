package com.semisky.autotesttool.pattern_v.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.semisky.autotesttool.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterForDebugOptionsItem extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public AdapterForDebugOptionsItem(Context ctx) {
        this.mContext = ctx;
        this.mList = new ArrayList<String>();
    }

    public void updateList(List<String> datas) {
        if (null != mList && !mList.isEmpty() && null != datas) {
            mList.clear();
            mList.addAll(datas);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(null == convertView){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_debug_menu,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tv_item_name.setText();
        return convertView;
    }

    protected class ViewHolder{
        private TextView tv_item_name;
        ViewHolder(View itemView){
            tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }
}
