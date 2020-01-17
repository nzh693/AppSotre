package com.example.appstore.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appstore.R;
import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.ViewModule.UpdataAppViewModule;
import com.example.appstore.database.ViewModule.SearChResultViewModel;
import com.example.appstore.database.ViewModule.SearchItemViewModel;
import com.example.appstore.database.dao.UpdateAppDao;
import com.example.appstore.database.entity.UpdataApp;
import com.example.appstore.database.entity.SearchItems;
import com.example.appstore.database.entity.SearchResults;

import java.util.List;

public class AppdDetailVideoActivity extends AppCompatActivity {
    private Button addData;
    private Button deleteAll;
    private Button deleteAllItems;
    private Button deleteAllResult;

    private Button addSearItem;
    private Button addSearResult;


    private TextView showData;

    private AppDataBase appDataBase;
    private UpdateAppDao updateAppDao;
    private  LiveData<List<UpdataApp>> liveDataApp;

    private SearchItemViewModel searchItemViewModel;
    private SearChResultViewModel searChResultViewModel;
    private UpdataAppViewModule updateViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail_video);
        addData=findViewById(R.id.app_add);
        deleteAll =findViewById(R.id.app_delete);
        showData=findViewById(R.id.app_showData);
        deleteAllItems =findViewById(R.id.app_delete_all_searchItems);
        deleteAllResult=findViewById(R.id.app_delete_all_searchResult);
        addSearItem=findViewById(R.id.app_add_searchItem);
        addSearResult=findViewById(R.id.app_add_searchResult);

        appDataBase=AppDataBase.getInstance(this);

        updateViewModel =ViewModelProviders.of(this).get(UpdataAppViewModule.class);
        updateAppDao =appDataBase.getMyAppDao();
        searchItemViewModel=ViewModelProviders.of(this).get(SearchItemViewModel.class);
        searChResultViewModel=ViewModelProviders.of(this).get(SearChResultViewModel.class);

        //数据更新监听
        liveDataApp= updateAppDao.queryAllApp();

        liveDataApp.observe(this, new Observer<List<UpdataApp>>() {
            @Override
            public void onChanged(List<UpdataApp> updataApps) {
                String text="";
                for(UpdataApp app: updataApps){
                    text+=app.getId()+app.getAppName();
                }
                showData.setText(text);
            }
        });


        //插入更新App数据
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdataApp test1=new UpdataApp("腾讯视频","1","这就是你的主场，不服来战","5天前",0);
                UpdataApp test2=new UpdataApp("腾讯新闻","2","【新增】 分享最新资讯, 会员成长系统", "11月11日",0);
                UpdataApp test3=new UpdataApp("淘宝","3","【新增】双十二已来，你准备好了吗", "11月11日",0);
                UpdataApp test4=new UpdataApp("微信","4","【修复】解决一些已知问题", "8天前",0);
                UpdataApp test5=new UpdataApp("QQ","5","【新增】—个性签名支持查看/添加话题，热点资讯聚集地。", "2019年10月30日",0);
                UpdataApp test6=new UpdataApp("京东—挑好物，上京东","6","【社区主题新增挑战】 必囤零食“ 深夜食堂”，”、防脱发宝典“", "一天前",0);
                UpdataApp test7=new UpdataApp("qq音乐—让生活充满音乐","7","问题修复以及用户体验化", "一个小时前",1);
                UpdataApp test8=new UpdataApp("豆瓣","10","新增精彩好内容《我就是演员》，《热血同行》，《演技派》", "一个小时前",1);
                updateViewModel.insert(test1,test2,test3,test4,test5,test6,test7,test8);
            }
        });

        //插入搜索词条数据
        addSearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1——社交 2——游戏 3——体育 4-购物 5-工作
                SearchItems item1=new SearchItems("qq","1");
                SearchItems item2=new SearchItems("微信","1");
                SearchItems item3=new SearchItems("微博","1");
                SearchItems item4=new SearchItems("吃鸡","2");
                SearchItems item5=new SearchItems("LOL","2");
                SearchItems item6=new SearchItems("我的世界","2");
                SearchItems item7=new SearchItems("腾讯体育","3");
                SearchItems item8=new SearchItems("PP体育","3");
                SearchItems item9=new SearchItems("CCTV5","3");
                SearchItems item10=new SearchItems("淘宝","4");
                SearchItems item11=new SearchItems("闲鱼","4");
                SearchItems item12=new SearchItems("京东","4");
                SearchItems item13=new SearchItems("聚美优品","4");
                SearchItems item14=new SearchItems("Office","5");
                SearchItems item15=new SearchItems("OA","5");
                SearchItems item16=new SearchItems("PS","5");
                SearchItems item17=new SearchItems("Word","5");
                SearchItems item18=new SearchItems("Excle","5");
                searchItemViewModel.inert(item1,item2,item3,item4,item5,item6,item7,item8,
                        item9,item10,item11,item12,item13,item14,item15,item15,item16,item17,item18
                        );

            }
        });

        //插入搜索结果App数据
        addSearResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchResults result1=new SearchResults("1","轻颜相机—风格自拍潮流","拍出高级脸","5","115万","1");
                SearchResults result2=new SearchResults("2","Faceu激萌-你就是这么好看","自拍总有新玩法","5","176万","2");
                SearchResults result3=new SearchResults("3","无他相机—拍好不用p","4D原生妆，逼脸超体验","4","14.5万","3");
                SearchResults result4=new SearchResults("4","B612咔叽-点缀你的自然美","拍出自然美的全能相机","3","4.5万","4");
                SearchResults result5=new SearchResults("5","MIX滤镜大师-创意无限","一秒换天空，超多风格任意选","3","1.8k","5");
                SearchResults result6=new SearchResults("6","NOMO相机","极简相机，真实胶片","4","56.3万","6");
                searChResultViewModel.insert(result1,result2,result3,result4,result5,result6);

            }
        });
        //删除更新app数据
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateViewModel.deleteAll();
            }
        });

        deleteAllItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchItemViewModel.deleteAll();
            }
        });

        deleteAllResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               searChResultViewModel.deleteAll();
            }
        });

    }




}
