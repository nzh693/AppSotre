package com.example.appstore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.activities.AppDetailImageActivity;
import com.example.appstore.activities.AppDetailSynthesisActivity;
import com.example.appstore.activities.AppdDetailVideoActivity;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class TodayLayoutsAdpater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    //三个final分别代表三个不同的布局
    public static final int ITEMONE = 1;
    public static final int ITEMTWO = 2;
    public static final int ITEMTHREE = 3;
    public static final int ITEMFOUR = 4;



    //上下文
    private Context context;

    /**
     * 构造方法
     *
     * @param context
     */
    public TodayLayoutsAdpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //这时候就要根据这个i来判断加哪一个布局了

        View inflate = null;
        RecyclerView.ViewHolder viewHolder = null;

        //根据i返回不同布局
        switch (i) {
            case ITEMONE:
                inflate = LayoutInflater.from(context).inflate(R.layout.today_fristlayout, viewGroup, false);
                viewHolder = new OneItemHolder(inflate);
                break;
            case ITEMTWO:
                inflate = LayoutInflater.from(context).inflate(R.layout.today_secondlayout, viewGroup, false);
                viewHolder = new TwoItemHolder(inflate);
                break;
            case ITEMTHREE:
                inflate = LayoutInflater.from(context).inflate(R.layout.today_threelayout, viewGroup, false);
                viewHolder = new ThreeItemHolder(inflate);
                break;
            case ITEMFOUR:
                inflate = LayoutInflater.from(context).inflate(R.layout.today_fourthlayout, viewGroup, false);
                viewHolder = new FourItemHolder(inflate);
                break;

        }

        //返回布局
        return viewHolder;
    }

    /**
     * 绑定控件，这里可以写一些事件方法等
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        //如果当前的 viewHolder 属于 OneItemHolder 则执行
        if (viewHolder instanceof OneItemHolder) {

            //写绑定或这写事件可以如下
            ((OneItemHolder) viewHolder).today_layout_first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, AppDetailSynthesisActivity.class);
                    context.startActivity(intent);
                }
            });

        } else if (viewHolder instanceof TwoItemHolder) {
            final TwoItemHolder  viewHolderTwo=(TwoItemHolder)viewHolder;
            viewHolderTwo.downLoad1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad1.setVisibility(View.GONE);
                    viewHolderTwo.bar1.setVisibility(View.VISIBLE);
                }
            });
            viewHolderTwo.downLoad2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad2.setVisibility(View.GONE);
                    viewHolderTwo.bar2.setVisibility(View.VISIBLE);
                }
            });

            viewHolderTwo.downLoad3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad3.setVisibility(View.GONE);
                    viewHolderTwo.bar3.setVisibility(View.VISIBLE);
                }
            });
            viewHolderTwo.downLoad4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad4.setVisibility(View.GONE);
                    viewHolderTwo.bar4.setVisibility(View.VISIBLE);
                }
            });

            viewHolderTwo.bar1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad1.setVisibility(View.VISIBLE);
                    viewHolderTwo.bar1.setVisibility(View.GONE);
                }
            });
            viewHolderTwo.bar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad2.setVisibility(View.VISIBLE);
                    viewHolderTwo.bar2.setVisibility(View.GONE);
                }
            });

            viewHolderTwo.bar3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad3.setVisibility(View.VISIBLE);
                    viewHolderTwo.bar3.setVisibility(View.GONE);
                }
            });
            viewHolderTwo.bar4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolderTwo.downLoad4.setVisibility(View.VISIBLE);
                    viewHolderTwo.bar4.setVisibility(View.GONE);
                }
            });


        } else if (viewHolder instanceof ThreeItemHolder) {
            ((ThreeItemHolder) viewHolder).today_layout_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, AppDetailImageActivity.class);
                    context.startActivity(intent);

                }
            });



        }else  if (viewHolder instanceof  FourItemHolder){
            ((FourItemHolder) viewHolder).today_layout_four.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, AppdDetailVideoActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }

    /**
     * 返回条目总数量，假设16个条目
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 8;
    }

    /**
     * 返回条目类型(这里就做个简单的判断区分)
     *
     * @param position 代表第几个条目
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position % 4 == 0) {
            return ITEMFOUR;
        } else if (position % 3== 0) {
            return ITEMTHREE;
        }
        else if (position % 2== 0) {
            return ITEMTWO;
        }
        else {
            return ITEMONE;
        }
    }

    /**
     * 第一个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class OneItemHolder extends RecyclerView.ViewHolder {
        LinearLayout today_layout_first;
        public OneItemHolder(@NonNull View itemView) {
            super(itemView);
            today_layout_first=itemView.findViewById(R.id.today_layout_first);
        }
    }

    /**
     * 第二个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class TwoItemHolder extends RecyclerView.ViewHolder {
         Button downLoad1;
         Button downLoad2;
         Button downLoad3;
         Button downLoad4;

         ProgressBar bar1;
         ProgressBar bar2;
         ProgressBar bar3;
         ProgressBar bar4;
        LinearLayout today_layout_second;
        public TwoItemHolder(@NonNull View itemView) {
            super(itemView);
            today_layout_second=itemView.findViewById(R.id.today_layout_second);
            bar1=itemView.findViewById(R.id.btm_headdownprogress_1);
            bar2=itemView.findViewById(R.id.btm_headdownprogress_2);
            bar3=itemView.findViewById(R.id.btm_headdownprogress_3);
            bar4=itemView.findViewById(R.id.btm_headdownprogress_4);
            downLoad1=itemView.findViewById(R.id.btm_get_1);
            downLoad2=itemView.findViewById(R.id.btm_get_2);
            downLoad3=itemView.findViewById(R.id.btm_get_3);
            downLoad4=itemView.findViewById(R.id.btm_get_4);
        }
    }

    /**
     * 第三个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class ThreeItemHolder extends RecyclerView.ViewHolder {

        LinearLayout today_layout_three;

        public ThreeItemHolder(@NonNull View itemView) {
            super(itemView);
            today_layout_three=itemView.findViewById(R.id.today_layout_three);

        }
    }

    /**
     * 第四个布局的Holder，要继承自RecyclerView.ViewHolder，这里你可以绑定控件
     */
    class FourItemHolder extends RecyclerView.ViewHolder {

        //设置视频播放地址
        private JCVideoPlayerStandard player;
        LinearLayout today_layout_four;

        public FourItemHolder(@NonNull View itemView) {
            super(itemView);
            today_layout_four=itemView.findViewById(R.id.today_layout_fourth);
            player= itemView.findViewById(R.id.today_video_1);
            player.thumbImageView.setImageResource(R.drawable.videobg);
            player.backButton.setVisibility(View.INVISIBLE);
            player.bottomProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}


