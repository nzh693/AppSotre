package com.example.appstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class GameHoScrollAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final static int ONELAYOUT=1;
    private final static int TWOLAYOUT=2;
    private final static int THREELAYOUT=3;
    private final static int FOURLAYOUT=4;
    private final static int FIVELAYOUT=5;
    private final static int SIXLAYOUT=6;
    private final static int SEVENLAYOUT=7;


    private static View view1;
    private static View view2;
    private static View view3;
    private static View view4;
    private static View view5;
    private static View view6;
    private static View view7;


    private int requestLayout;


    private Context context;

    public GameHoScrollAdpater(Context context,int requestLayout){
        this.context=context;
        this.requestLayout=requestLayout;



    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder=null;

        switch (requestLayout){
            case ONELAYOUT:
                 view1= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_1,parent,false);
                 holder=new MyViewHolder(view1);
                break;
            case TWOLAYOUT:
                view2= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_2,parent,false);
                holder=new MyViewHolder(view2);
                break;
            case THREELAYOUT:
                view3= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_3,parent,false);
                holder=new MyViewHolder(view3);
                break;
            case FOURLAYOUT:
                view4= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_4,parent,false);
                holder=new MyViewHolder(view4);
                break;
            case FIVELAYOUT:
                view5= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_5,parent,false);
                holder=new MyViewHolder(view5);
                break;
            case SIXLAYOUT:
                view6= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_6,parent,false);
                holder=new MyViewHolder(view6);
                break;
            case SEVENLAYOUT:
                view7= LayoutInflater.from(context).inflate(R.layout.app_game_horizontalitems_7,parent,false);
                holder=new MyViewHolderVide(view7);
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
            video= itemView.findViewById(R.id.app_game_vide);
            video.thumbImageView.setImageResource(R.drawable.videobg_1);
            video.backButton.setVisibility(View.INVISIBLE);
            video.bottomProgressBar.setVisibility(View.INVISIBLE);
        }
    }




}
