package com.example.kazukoba.jsonimportcustomlistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kazukoba on 2018/02/14.
 */

public class ShopListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<ListData> shopList;

    public ShopListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setShopList(ArrayList<ListData> shopList) {
        this.shopList = shopList;
    }

    @Override
    public  int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position){
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.list_item,parent,false);
        }
        ((TextView)convertView.findViewById(R.id.shopName)).setText(shopList.get(position).getShopName());
        ((TextView)convertView.findViewById(R.id.area)).setText(shopList.get(position).getArea());

        return  convertView;
    }
}
