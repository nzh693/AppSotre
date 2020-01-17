package com.example.appstore.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.activities.AcountActivity;
import com.example.appstore.adapters.TodayLayoutsAdpater;

public class FragmentToday extends Fragment {

    private RecyclerView recyclerView;
    private ImageView personalCentre;
    private Button downLoad1;
    private Button downLoad2;
    private Button downLoad3;
    private Button downLoad4;

    private ProgressBar bar1;
    private ProgressBar bar2;
    private ProgressBar bar3;
    private ProgressBar bar4;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmment_today,container,false);
        recyclerView=view.findViewById(R.id.recycleView_Today);
        personalCentre=view.findViewById(R.id.head_image_personalCenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TodayLayoutsAdpater(getContext()));

        init();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    public void init(){
        Onclick onclick=new Onclick();
        personalCentre.setOnClickListener(onclick);//点击个人中心跳转

    }

    //处理点击事件
    public class Onclick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;
            switch (v.getId()){
                case R.id.head_image_personalCenter:
                    intent=new Intent(getContext(), AcountActivity.class);
                    break;
                case R.id.btm_get_1:

                    break;
                case R.id.btm_get_2:
                    bar2.setVisibility(View.VISIBLE);
                    downLoad2.setVisibility(View.GONE);
                    break;
                case R.id.btm_get_3:
                    bar3.setVisibility(View.VISIBLE);
                    downLoad3.setVisibility(View.GONE);
                    break;
                case R.id.btm_get_4:
                    bar4.setVisibility(View.VISIBLE);
                    downLoad4.setVisibility(View.GONE);
                    break;
            }
            startActivity(intent);
        }


    }
}
