package com.example.appstore.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.appstore.MainActivity;
import com.example.appstore.R;
import com.example.appstore.activities.AcountActivity;
import com.example.appstore.adapters.AppHoScrollAdpater;
import com.example.appstore.adapters.GameHoScrollAdpater;

public class FragmentApp extends Fragment {


    private RecyclerView hoRecycleView_1;
    private RecyclerView hoRecycleView_2;
    private RecyclerView hoRecycleView_3;
    private RecyclerView hoRecycleView_4;

    private ImageView positopnLine1;
    private View      positopnLine2;
    private ScrollView scrollView;
    private RelativeLayout  hideTile;
    private RelativeLayout  headTile;
    private TextView hideTileText;



    private Context context;

    public FragmentApp(Context context){
        this.context=context;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View myView=inflater.inflate(R.layout.fragmment_app,container,false);
        hoRecycleView_1 = myView.findViewById(R.id.recycleView_app_HorizontalScroll_1);
        hoRecycleView_2 = myView.findViewById(R.id.recycleView_app_HorizontalScroll_2);
        hoRecycleView_3 = myView.findViewById(R.id.recycleView_app_HorizontalScroll_3);
        hoRecycleView_4 = myView.findViewById(R.id.recycleView_app_HorizontalScroll_4);
        positopnLine1=myView.findViewById(R.id.head_image_personalCenter);
        positopnLine2=myView.findViewById(R.id.positon_line);
        scrollView=myView.findViewById(R.id.scroll_app);
        hideTile=myView.findViewById(R.id.head_hide_app);
        headTile=myView.findViewById(R.id.head_all);
        hideTileText=myView.findViewById(R.id.hidennApp).findViewById(R.id.hide_tile);
        //游戏水平滚动1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hoRecycleView_1.setLayoutManager(linearLayoutManager);
        hoRecycleView_1.setAdapter(new AppHoScrollAdpater(getContext(),1));
        hoRecycleView_1.addItemDecoration(new Decrotion());

        //游戏水平滚动2
        GridLayoutManager linearLayoutManager2 = new GridLayoutManager(getContext(),1);
        linearLayoutManager2.setOrientation(GridLayoutManager.HORIZONTAL);
        hoRecycleView_2.setLayoutManager(linearLayoutManager2);
        hoRecycleView_2.setAdapter(new AppHoScrollAdpater(getContext(),2));
        hoRecycleView_2.addItemDecoration(new Decrotion());

        //游戏水平滚动3
        hoRecycleView_3.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        hoRecycleView_3.setAdapter(new AppHoScrollAdpater(getContext(),3));
        hoRecycleView_3.addItemDecoration(new Decrotion());

        //游戏水平滚动4
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext());
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
        hoRecycleView_4.setLayoutManager(linearLayoutManager4);
        hoRecycleView_4.setAdapter(new AppHoScrollAdpater(getContext(),4));
        hoRecycleView_4.addItemDecoration(new Decrotion());


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
                        hideTileText.setText("App");
                        headTile.setVisibility(View.GONE);
                    }
                    if (y>=70){
                        hideTile.setVisibility(View.GONE);
                        headTile.setVisibility(View.VISIBLE);
                    }
                    Log.d("距离Y：", " "+positopnLine1.getTop());
                    Log.d("回复距离Y：", " "+y);
                }
            });
        }
        init();
        return myView;

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    class Decrotion extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int gap=getResources().getDimensionPixelOffset(R.dimen.driverLineHeight20);
            outRect.set(0,0,gap,0);

        }
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
}
