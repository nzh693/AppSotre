package com.example.appstore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.appstore.R;
import com.example.appstore.adapters.DetailVerScrollAdpater;

public class AppDetailSynthesisActivity extends AppCompatActivity {

    private ImageView imageView;
    private View position;
    private ScrollView scrollView;
    private TextView changeBg;//头部随滑动渐变透明
    private LinearLayout layoutBtHidden;//下滑出现按钮
    private TextView textView;
    private ImageView im_backIcon;//返回上一个页面
    private Button app_deatil;//点击弹出框
    private Button btm_detail_downLoad;//中间下载按钮
    private Button btm_headdownLoad;//顶部下载按钮
    private PopupWindow popupWindow_app_detail;
    private LinearLayout detail_popWindow_cancel;//点击收起弹框
    private ProgressBar btm_detail_downLoad_progress;//点击下载出现圆形进度
    private ProgressBar btm_headdownprogrss;//顶部下载出现圆形进度



    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        View view=findViewById(R.id.detail_myhead);
        imageView = findViewById(R.id.detail_head_im);
        position=findViewById(R.id.detail_position_line);
        scrollView=findViewById(R.id.scroll_detail);
        im_backIcon=findViewById(R.id.im_backIcon);
        app_deatil=findViewById(R.id.detail_quote_message).findViewById(R.id.app_Detail);
        btm_detail_downLoad=findViewById(R.id.detail_quote_message).findViewById(R.id.btm_detail_downLoad);
        btm_detail_downLoad_progress=findViewById(R.id.detail_quote_message).findViewById(R.id.btm_detail_downLoad_progress);
        changeBg=findViewById(R.id.detail_myhead).findViewById(R.id.hide_tile);
        layoutBtHidden=findViewById(R.id.detail_myhead).findViewById(R.id.detail_headBt_hidden);
        btm_headdownLoad=findViewById(R.id.detail_myhead).findViewById(R.id.btm_headdownLoad);
        btm_headdownprogrss=findViewById(R.id.detail_myhead).findViewById(R.id.btm_headdownprogrss);

        LinearLayout ll= (LinearLayout) getLayoutInflater().inflate(R.layout.app_detail_popwindowone,null);
        detail_popWindow_cancel=getLayoutInflater().inflate(R.layout.app_detail_popwindowone,null).findViewById(R.id.detail_popWindow_cancel);

        recyclerView1=findViewById(R.id.RV_scrollVerticalImage);
        recyclerView2=findViewById(R.id.RV_scrollVerticalImage_selector);
        //图片滚动
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(AppDetailSynthesisActivity.this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        recyclerView1.setAdapter(new DetailVerScrollAdpater(AppDetailSynthesisActivity.this,1));
        recyclerView1.addItemDecoration(new Decrotion());
        //app精选滚动
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(AppDetailSynthesisActivity.this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setAdapter(new DetailVerScrollAdpater(AppDetailSynthesisActivity.this,2));
        recyclerView2.addItemDecoration(new Decrotion());
        im_backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initi();
        openSinkIn();//开启沉浸模式
        //头部随滑动距离变化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    //获取view控件的屏幕坐标
                    int sumY=313;
                    int positionChange=225;
                    int colorDistance=255;
                    if (changeBg!=null){
                        int[] location = new int[2];
                        position.getLocationOnScreen(location);
                        int y=location[1];
                        if (y>positionChange){//渐变透明
                            int d=99-(int)(Math.floor((y-positionChange)*0.81)*0.38);
                            while(d<0){
                                d++;
                            }
                            while(d>100){
                                d--;
                            }
                            String lucencyValue;
                            if (d<10){
                                lucencyValue="0"+String.valueOf(d);
                            }
                            else {
                                lucencyValue=String.valueOf(d);
                            }
                            String color="#"+lucencyValue+"FFFFFF";

                            im_backIcon.setBackgroundColor(Color.parseColor(color));
                            changeBg.setBackgroundColor(Color.parseColor(color));
                        }

                        if (y<=225){//恢复状态栏为灰色
                            getWindow().setStatusBarColor(Color.GRAY);//无法改变状态栏字体颜色
                        }
                        if (y<=25){
                            changeBg.setBackgroundColor(Color.WHITE);
                            im_backIcon.setBackgroundColor(Color.WHITE);
                        }
                        if(y<=-200){//划下app图标顶部横条显示app信息
                            layoutBtHidden.setVisibility(View.VISIBLE);
                        }
                        if (y>=-200){//划上app图标顶部横条显示app信息
                            layoutBtHidden.setVisibility(View.INVISIBLE);
                        }
                        if (y>25){//向上滑动开启沉浸模式
                            layoutBtHidden.setVisibility(View.INVISIBLE);
                            openSinkIn();
                        }

                    }
                }
            });
        }
    }

    private void initi(){
        onclick onclick=new onclick();
        detail_popWindow_cancel.setOnClickListener(onclick);
        app_deatil.setOnClickListener(onclick);
        btm_detail_downLoad.setOnClickListener(onclick);
        btm_headdownLoad.setOnClickListener(onclick);
        btm_headdownprogrss.setOnClickListener(onclick);
        btm_detail_downLoad_progress.setOnClickListener(onclick);



    }

    public class onclick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.app_Detail://点击弹出弹框
                    View view=getLayoutInflater().inflate(R.layout.app_detail_popwindowone,null);
                    popupWindow_app_detail =new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    popupWindow_app_detail.setFocusable(true);
                    popupWindow_app_detail.setOutsideTouchable(true);
                    popupWindow_app_detail.showAtLocation(findViewById(R.id.activty_layout_detail), Gravity.BOTTOM,0,0);
                    popupWindow_app_detail.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
                    setBackgoundLucency(0.3f);
                    if (popupWindow_app_detail!=null){
                        popupWindow_app_detail.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                setBackgoundLucency(1f);
                            }
                        });
                    }
                    break;
                case R.id.detail_popWindow_cancel://点击取消弹框
                    setBackgoundLucency(1f);
                    if (popupWindow_app_detail!=null&&popupWindow_app_detail.isShowing()){
                        popupWindow_app_detail.dismiss();
                    }
                    break;
                case R.id.btm_detail_downLoad://中间点击开始下载进度
                    btm_detail_downLoad.setVisibility(View.INVISIBLE);
                    btm_detail_downLoad_progress.setVisibility(View.VISIBLE);
                    break;
                case R.id.btm_detail_downLoad_progress://中间点击进度恢复
                    btm_detail_downLoad.setVisibility(View.VISIBLE);
                    btm_detail_downLoad_progress.setVisibility(View.INVISIBLE);
                    break;
                case R.id.btm_headdownLoad://头部点击开始下载进度
                    btm_headdownprogrss.setVisibility(View.VISIBLE);
                    btm_headdownLoad.setVisibility(View.GONE);
                    break;
                case R.id.btm_headdownprogrss://头部点击开始下载进度
                    btm_headdownprogrss.setVisibility(View.GONE);
                    btm_headdownLoad.setVisibility(View.VISIBLE);
                    break;



            }
        }
    }

    //开启沉浸式
    public void  openSinkIn(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }


    /**
     * 设置背景透明度
     * @param f 透明度
     */
    public void setBackgoundLucency(float f){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha =f;//设置背景透明度
        getWindow().setAttributes(lp);
    }


    class Decrotion extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int gap=getResources().getDimensionPixelOffset(R.dimen.driverLineHeight20);
            outRect.set(0,0,gap,0);

        }
    }

}
