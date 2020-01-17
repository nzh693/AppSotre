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


public class UpdateAdpaterNeedUpdate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View view;
    private Context context;



    private List<Integer> icons;
    private  List<UpdataApp> apps=new ArrayList<>();

    public UpdateAdpaterNeedUpdate(Context context){
        this.context=context;

    }

    public void setApps(List<UpdataApp> apps) {
        this.apps = apps;
    }

    public void setIcons(List<Integer> icons) {
        this.icons = icons;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.app_update_needupdate,parent,false);
        return new MyViewHolderNeedUpdate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MyViewHolderNeedUpdate myHolder=(MyViewHolderNeedUpdate)holder;

        myHolder.icon.setImageResource(icons.get(position+6));
        myHolder.tile.setText(apps.get(position).getAppName());
        myHolder.date.setText(apps.get(position).getDate());
        myHolder.message.setText(apps.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }





    class MyViewHolderNeedUpdate extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView tile;
        TextView date;
        TextView message;
        Button update;

        public MyViewHolderNeedUpdate(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.update_icon);
            tile=itemView.findViewById(R.id.update_tile);
            date=itemView.findViewById(R.id.update_date);
            message=itemView.findViewById(R.id.update_message);
            update=itemView.findViewById(R.id.update_bt);

        }
    }


}
