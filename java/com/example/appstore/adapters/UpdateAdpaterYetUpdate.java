package com.example.appstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.database.entity.UpdataApp;

import java.util.ArrayList;
import java.util.List;


public class UpdateAdpaterYetUpdate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View view;
    private Context context;

    List<UpdataApp> apps=new ArrayList<>();
    List<Integer> icons=new ArrayList<>();



    public UpdateAdpaterYetUpdate(Context context){
        this.context=context;
    }

    public void setApps(List<UpdataApp> apps) {
        this.apps = apps;
    }

    public void setIcons(List<Integer> icons) {
        this.icons = icons;
    }

    //返回布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.app_update_yetupdate,parent,false);
        return new MyViewHolderVideYetUpdate(view);
    }


    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MyViewHolderVideYetUpdate myHolder=(MyViewHolderVideYetUpdate)holder;
        if (position==getItemCount()-1){//去掉已经更新的最后一条线
            myHolder.view.setVisibility(View.INVISIBLE);
        }
        myHolder.icon.setImageResource(icons.get(position));
        myHolder.tile.setText(apps.get(position).getAppName());
        myHolder.date.setText(apps.get(position).getDate());
        myHolder.message.setText(apps.get(position).getContent());
    }


    @Override
    public int getItemCount() {
        return apps.size();
    }


    //绑定控件
    class MyViewHolderVideYetUpdate extends RecyclerView.ViewHolder{
        public View view;

        ImageView icon;
        TextView tile;
        TextView date;
        TextView message;
        Button update;
        public MyViewHolderVideYetUpdate(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.update_icon);
            tile=itemView.findViewById(R.id.update_tile);
            date=itemView.findViewById(R.id.update_date);
            message=itemView.findViewById(R.id.update_message);
            update=itemView.findViewById(R.id.update_bt);
            view=itemView.findViewById(R.id.update_yetLine);
        }
    }

}
