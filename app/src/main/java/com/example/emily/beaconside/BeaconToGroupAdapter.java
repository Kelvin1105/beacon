package com.example.emily.beaconside;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/9/26.
 */

public class BeaconToGroupAdapter extends BaseAdapter {

    private LayoutInflater mLayInf;
    ArrayList<HashMap<String, Object>> mItemList;
    List<Boolean> mChecked;
    //    List<Person> listPerson;
    HashMap<Integer,View> map = new HashMap<Integer,View>();

    public BeaconToGroupAdapter(Context context, ArrayList<HashMap<String, Object>> itemList)
    {
        mChecked = new ArrayList<Boolean>();
        for(int i=0;i<itemList.size();i++){
            mChecked.add(false);
        }
        mLayInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemList = itemList;
    }

    @Override
    public int getCount() {
        //取得 ListView 列表 Item 的數量
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        //取得 ListView 列表於 position 位置上的 Item
        return mItemList.get(position).get("macAddress");
    }

    @Override
    public long getItemId(int position) {
        //取得 ListView 列表於 position 位置上的 Item 的 ID
        return position;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = mLayInf.inflate(R.layout.beacon_list, parent, false);
        ViewHolder holder = null;
        holder = new ViewHolder();
        holder.selected = (CheckBox) v.findViewById(R.id.MyAdapter_CheckBox_checkBox);
        holder.name = (TextView)v.findViewById(R.id.name);
        holder.imgView = (ImageView)v.findViewById(R.id.pic);
        holder.macAddress =(TextView) v.findViewById(R.id.macAddress);


        final int p = position;
        map.put(position, v);
        holder.selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox)v;
                mChecked.set(p, cb.isChecked());
            }
        });
        v.setTag(holder);

        holder.name.setText(mItemList.get(position).get("bName").toString());
        holder.macAddress.setText(mItemList.get(position).get("macAddress").toString());

        if (mItemList.get(position).get("bPic").toString()=="key")
            holder.imgView.setImageResource(R.drawable.key);
        if (mItemList.get(position).get("bPic").toString()=="wallet")
            holder.imgView.setImageResource(R.drawable.wallet);
        if (mItemList.get(position).get("bPic").toString()=="headphones")
            holder.imgView.setImageResource(R.drawable.headphones);
        if (mItemList.get(position).get("bPic").toString()=="headphones2")
            holder.imgView.setImageResource(R.drawable.headphones2);
        if (mItemList.get(position).get("bPic").toString()=="water")
            holder.imgView.setImageResource(R.drawable.water);
        if (mItemList.get(position).get("bPic").toString()=="watch")
            holder.imgView.setImageResource(R.drawable.watch);
        if (mItemList.get(position).get("bPic").toString()=="camera")
            holder.imgView.setImageResource(R.drawable.camera);
        if (mItemList.get(position).get("bPic").toString()=="book")
            holder.imgView.setImageResource(R.drawable.book);
        if (mItemList.get(position).get("bPic").toString()=="bottle")
            holder.imgView.setImageResource(R.drawable.bottle);
        if (mItemList.get(position).get("bPic").toString()=="coffee")
            holder.imgView.setImageResource(R.drawable.coffee);

        holder.selected.setChecked(mChecked.get(position));
        return v;
    }
    class ViewHolder{
        CheckBox selected;
        TextView name;
        TextView macAddress;
        ImageView imgView;
    }
}
