package com.example.appstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class DetailVerScrollAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final static int ONELAYOUT=1;
    private final static int TWOLAYOUT=2;




    private static View view1;
    private static View view2;



    private int requestLayout;


    private Context context;

    public DetailVerScrollAdpater(Context context, int requestLayout){
        this.context=context;
        this.requestLayout=requestLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder=null;

        switch (requestLayout){
            case ONELAYOUT:
                 view1= LayoutInflater.from(context).inflate(R.layout.app_detail_scrollver,parent,false);
                 holder=new MyViewHolder(view1);
                break;
            case TWOLAYOUT:
                view2= LayoutInflater.from(context).inflate(R.layout.app_detail_scrollver_select,parent,false);
                holder=new MyViewHolderSelctor(view2);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder){

        }
        if (holder instanceof MyViewHolderSelctor){

        }



    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.detail_layout1_image);

        }
    }


    class MyViewHolderSelctor extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolderSelctor(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.detail_layout2_image);
        }
    }




}
