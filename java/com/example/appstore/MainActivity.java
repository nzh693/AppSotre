package com.example.appstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;

import com.example.appstore.fragments.FragmentApp;
import com.example.appstore.fragments.FragmentGame;
import com.example.appstore.fragments.FragmentSearch;
import com.example.appstore.fragments.FragmentToday;
import com.example.appstore.fragments.FragmentUpdate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    public  static boolean isLoad=false;

    private FragmentApp fragmentApp;
    private FragmentGame fragmentGame;
    private FragmentSearch fragmentSearch;
    private FragmentToday fragmentToday;
    private FragmentUpdate fragmentUpdate;

    private BottomNavigationView mBNV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBNV=findViewById(R.id.bottomNv);
        initi();
    }


    /**
     * 初始化
     */
    public void initi(){
        fragmentToday=new FragmentToday();
        getSupportFragmentManager().beginTransaction().add(R.id.f_layout,fragmentToday).commitAllowingStateLoss();//默认加载App布局

        mBNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_tab1:
                        if (fragmentToday==null){
                            fragmentToday=new FragmentToday();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.f_layout,fragmentToday)
                                .addToBackStack(null).commitAllowingStateLoss();
                        break;
                    case R.id.item_tab2:
                        if (fragmentGame==null){
                            fragmentGame=new FragmentGame();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.f_layout,fragmentGame)
                                .addToBackStack(null).commitAllowingStateLoss();
                        break;

                    case R.id.item_tab3:
                        if (fragmentApp==null){
                        fragmentApp=new FragmentApp(MainActivity.this);
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.f_layout,fragmentApp)
                                .addToBackStack(null).commitAllowingStateLoss();
                        break;

                    case R.id.item_tab4:
                        if (fragmentUpdate==null){
                            fragmentUpdate=new FragmentUpdate();
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.f_layout,fragmentUpdate)
                                .addToBackStack(null).commitAllowingStateLoss();
                        break;
                    case R.id.item_tab5:
                        if (fragmentSearch==null){
                            fragmentSearch=new FragmentSearch(MainActivity.this);
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.f_layout,fragmentSearch)
                                .addToBackStack(null).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });
    }

}
