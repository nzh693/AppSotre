package com.example.appstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class AppHoScrollAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final static int ONELAYOUT=1;
    private final static int TWOLAYOUT=2;
    private final static int THREELAYOUT=3;
    private final static int FOURLAYOUT=4;



    private static View view1;
    private static View view2;
    private static View view3;
    private static View view4;



    private int requestLayout;
    private Context context;


    public AppHoScrollAdpater(Context context, int requestLayout){
        this.context=context;
        this.requestLayout=requestLayout;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder=null;

        switch (requestLayout){
            case ONELAYOUT:
                 view1= LayoutInflater.from(context).inflate(R.layout.app_app_horizontalitems_1,parent,false);
                 holder=new MyViewHolder(view1);
                break;
            case TWOLAYOUT:
                view2= LayoutInflater.from(context).inflate(R.layout.app_app_horizontalitems_2,parent,false);
                holder=new MyViewHolder(view2);
                break;
            case THREELAYOUT:
                view3= LayoutInflater.from(context).inflate(R.layout.app_app_horizontalitems_3,parent,false);
                holder=new MyViewHolder(view3);
                break;
            case FOURLAYOUT:
                view4= LayoutInflater.from(context).inflate(R.layout.app_app_horizontalitems_4,parent,false);
                holder=new MyViewHolderVide(view4);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {



    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }


    class MyViewHolderVide extends RecyclerView.ViewHolder{

        private JCVideoPlayerStandard video;

        public MyViewHolderVide(@NonNull View itemView) {
            super(itemView);
            video= itemView.findViewById(R.id.app_app_vide);
            video.thumbImageView.setImageResource(R.drawable.videobg_1);
            video.backButton.setVisibility(View.INVISIBLE);
            video.bottomProgressBar.setVisibility(View.INVISIBLE);
        }
    }




}
