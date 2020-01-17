package com.example.appstore.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.activities.AcountActivity;
import com.example.appstore.adapters.UpdateAdpaterNeedUpdate;
import com.example.appstore.adapters.UpdateAdpaterYetUpdate;
import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.CallBack;
import com.example.appstore.database.ViewModule.UpdataAppViewModule;
import com.example.appstore.database.dao.UpdateAppDao;
import com.example.appstore.database.entity.UpdataApp;

import java.util.ArrayList;
import java.util.List;

public class FragmentUpdate extends Fragment  {


    CallBack listener;

    private RecyclerView recyclerViewNeedUpdate;
    private RecyclerView recyclerViewYetUpdate;

    private ImageView positopnLine1;
    private View      positopnLine2;
    private ScrollView scrollView;
    private RelativeLayout hideTile;
    private RelativeLayout  headTile;
    private TextView hideTileText;


    private AppDataBase appDataBase;
    private UpdateAppDao updateAppDao;
    private UpdataAppViewModule viewModule;
    private LiveData<List<UpdataApp>> liveDataMyApp;//app数据
    private static List<Integer> icons;//图标数据
    UpdateAdpaterNeedUpdate isUpdateAdpater;//可用更新
    UpdateAdpaterYetUpdate  notUpdateAdapter;//近期更新

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmment_update,container,false);
        recyclerViewNeedUpdate=view.findViewById(R.id.update_recycleView_1);
        recyclerViewYetUpdate=view.findViewById(R.id.update_recycleView_2);
        positopnLine1=view.findViewById(R.id.head_image_personalCenter);
        positopnLine2=view.findViewById(R.id.positon_update_line);
        scrollView=view.findViewById(R.id.scroll_update);
        hideTile=view.findViewById(R.id.head_hide_update);
        headTile=view.findViewById(R.id.head_all_update);
        hideTileText=view.findViewById(R.id.hidennUpdate).findViewById(R.id.hide_tile);

        setAppDataBase();
        setNotUpdateAdapter();
        setIsUpdateData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    //获取view控件的屏幕坐标
                    int[] location = new int[2];
                    positopnLine2.getLocationOnScreen(location);
                    int y=location[1];
                    if (positopnLine1.getTop()<=70){
                        hideTile.setVisibility(View.VISIBLE);//显示悬浮标题
                        hideTileText.setText("更新");
                        headTile.setVisibility(View.GONE);
                    }
                    if (y>=70){
                        hideTile.setVisibility(View.GONE);
                        headTile.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
        init();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setIcons(){
        icons =new ArrayList<>();
        icons.add(R.mipmap.update_1);
        icons.add(R.mipmap.update_2);
        icons.add(R.mipmap.update_3);
        icons.add(R.mipmap.update_4);
        icons.add(R.mipmap.update_5);
        icons.add(R.mipmap.update_6);
        icons.add(R.mipmap.update_7);
        icons.add(R.mipmap.update_8);
        icons.add(R.mipmap.update_9);
        icons.add(R.mipmap.update_10);
    }


    public void init(){
        Onclick onclick=new Onclick();
        positopnLine1.setOnClickListener(onclick);//点击个人中心跳转

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

            }
            startActivity(intent);
        }


    }

    //获取数据连接
    public void setAppDataBase(){
        appDataBase=AppDataBase.getInstance(getContext());
        updateAppDao =appDataBase.getMyAppDao();
        viewModule= ViewModelProviders.of(this).get(UpdataAppViewModule.class);
        setIcons();
    }

    //近期更新适配器数据查询
    public void setIsUpdateData(){
        isUpdateAdpater =new UpdateAdpaterNeedUpdate(getContext());
        isUpdateAdpater.setIcons(icons);
        viewModule.queryIsUpdate();
        viewModule.setIsDataListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {
                  List<UpdataApp> apps=(List<UpdataApp>) object;
                  System.out.println("setIsUpdateData"+apps.size());
                  isUpdateAdpater.setApps(apps);
                  isUpdateAdpater.notifyDataSetChanged();
            }
            @Override
            public void onDataFailed() {
                Toast.makeText(getContext(),"无法连接",Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewYetUpdate.setAdapter(isUpdateAdpater);
        recyclerViewYetUpdate.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    //未已更新适配器数据查询
    public void setNotUpdateAdapter(){
        notUpdateAdapter=new UpdateAdpaterYetUpdate(getContext());
        notUpdateAdapter.setIcons(icons);
        viewModule.queryNotUpdate();
        viewModule.setDataListener(new CallBack() {
            @Override
            public void onDataSuccessfully(Object object) {
                List<UpdataApp> apps= (List<UpdataApp>) object;
                System.out.println("setNotUpdateAdapter"+apps.size());
                notUpdateAdapter.setApps(apps);
                notUpdateAdapter.notifyDataSetChanged();
            }
            @Override
            public void onDataFailed() {
                Toast.makeText(getContext(),"无法连接",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewNeedUpdate.setAdapter(notUpdateAdapter);
        recyclerViewNeedUpdate.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
