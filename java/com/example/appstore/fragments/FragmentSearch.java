package com.example.appstore.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.adapters.SearchShowItemsAdapter;
import com.example.appstore.adapters.SearchShowResutltAdapter;
import com.example.appstore.database.AppDataBase;
import com.example.appstore.database.ViewModule.SearChResultViewModel;
import com.example.appstore.database.ViewModule.SearchItemViewModel;
import com.example.appstore.database.entity.SearchItems;
import com.example.appstore.database.entity.SearchResults;

import java.util.ArrayList;
import java.util.List;

public class FragmentSearch extends Fragment {


    private EditText editText;
    private Context context;
    private LinearLayout layout;
    private LinearLayout search_layout_root;
    private Button cancel;
    private TextView tile;
    private View showHot;

    private RecyclerView showItems;//显示搜索词条
    private RecyclerView showResult;//显示搜索结果

    private boolean searchResult=false;

    private AppDataBase dataBase;//数据库
    private SearchShowResutltAdapter resutltAdapter;//搜索结果适配器
    private SearchShowItemsAdapter itemsAdapter;//词条适配器

    private LiveData<List<SearchResults>> listLiveData;
    private SearChResultViewModel resultViewModel;
    private SearchItemViewModel itemViewModel;

    private List<Integer> icons;//app图标
    private List<Integer> images;//app展示图片

    public FragmentSearch(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmment_search,container,false);
        editText=view.findViewById(R.id.search_content);
        layout=view.findViewById(R.id.layout_changeColor);
        cancel=view.findViewById(R.id.search_cancel);
        tile=view.findViewById(R.id.head_tile);
        search_layout_root=view.findViewById(R.id.search_layout_root);
        showHot=view.findViewById(R.id.show_hot);
        showResult=view.findViewById(R.id.show_result);
        showItems =view.findViewById(R.id.show_inputitem);

        //获取数据
        dataBase=AppDataBase.getInstance(getContext());
        itemViewModel= ViewModelProviders.of(this).get(SearchItemViewModel.class);
        resultViewModel=ViewModelProviders.of(this).get(SearChResultViewModel.class);



        //取消搜索
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel.setVisibility(View.GONE);
                tile.setVisibility(View.VISIBLE);
                //恢复输入框的宽度
                ViewGroup.LayoutParams params=(ViewGroup.LayoutParams)editText.getLayoutParams();
                params.width=1000;
                editText.setLayoutParams(params);
                //恢复颜色
                layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(context.INPUT_METHOD_SERVICE));
                if (manager != null){
                    manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                editText.setText("");
                search_layout_root.requestFocus();
                showHot.setVisibility(View.VISIBLE);
            }
        });


        //设置点击其他区域收起键盘
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus ){
                    cancel.setVisibility(View.VISIBLE);
                    tile.setVisibility(View.GONE);
                    //缩短输入框的宽度
                    LinearLayout.LayoutParams params=(LinearLayout.LayoutParams)editText.getLayoutParams();
                    params.width=680;
                    editText.setLayoutParams(params);
                    if (searchResult){
                        layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }else {
                        layout.setBackgroundColor(Color.parseColor("#51000000"));
                    }
                }

                if(!hasFocus && !searchResult){//无搜索结果
                    System.out.println("无搜索结果");
                    cancel.setVisibility(View.GONE);
                    tile.setVisibility(View.VISIBLE);
                    //恢复输入框的宽度
                    ViewGroup.LayoutParams params=(ViewGroup.LayoutParams)editText.getLayoutParams();
                    params.width=1000;
                    editText.setLayoutParams(params);
                    //恢复颜色
                    layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(!hasFocus && searchResult){//有搜索结果  收起键盘
                    InputMethodManager manager = ((InputMethodManager)getContext().getSystemService(context.INPUT_METHOD_SERVICE));
                    if (manager != null)
                        manager.hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        //输入搜索信息变更搜索条目
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showHot.setVisibility(View.GONE);
                showItems.setVisibility(View.VISIBLE);
                final String keyword= editText.getText().toString();//搜索关键词
                itemsAdapter=new SearchShowItemsAdapter(getContext(),keyword);
                itemViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<SearchItems>>() {
                    @Override
                    public void onChanged(List<SearchItems> searchItems) {
                         List<SearchItems> list=selectLikeWord(searchItems,keyword);
                         itemsAdapter.setSearchItems(list);
                         itemsAdapter.notifyDataSetChanged();
                    }
                });
                showItems.setAdapter(itemsAdapter);
                showItems.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //监听键盘的回车事件----显示搜索结果
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId== EditorInfo.IME_ACTION_DONE
                    || actionId==EditorInfo.IME_ACTION_SEND
                    || (event!=null && event.getKeyCode()==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN)
                ){
                    layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    showHot.setVisibility(View.GONE);
                    showItems.setVisibility(View.GONE);
                    showResult.setVisibility(View.VISIBLE);

                    resutltAdapter=new SearchShowResutltAdapter(getContext(),v.getText().toString());

                    //绑定搜索结果数据
                    resultViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<SearchResults>>() {
                        @Override
                        public void onChanged(List<SearchResults> searchResults) {
                            setIconAndImages();
                            resutltAdapter.setIcons(icons);
                            resutltAdapter.setImages(images);
                            resutltAdapter.setResults(searchResults);
                            resutltAdapter.notifyDataSetChanged();
                        }
                    });
                    showResult.setAdapter(resutltAdapter);
                    showResult.setLayoutManager(new LinearLayoutManager(getContext()));
                    searchResult=true;
                }
                return true;

            }
        });
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //初始化图标和图片资源
    private void   setIconAndImages(){
        icons=new ArrayList<>();
        images=new ArrayList<>();
        icons.add(R.mipmap.caerma_1);
        icons.add(R.mipmap.caerma_2);
        icons.add(R.mipmap.caerma_3);
        icons.add(R.mipmap.caerma_4);
        icons.add(R.mipmap.caerma_5);
        icons.add(R.mipmap.caerma_6);


        images.add(R.mipmap.search_re1);
        images.add(R.mipmap.search_re2);
        images.add(R.mipmap.search_re3);
        images.add(R.mipmap.search_re4);
        images.add(R.mipmap.search_re5);
        images.add(R.mipmap.search_re6);
    }

    //模糊查询匹配
    private List<SearchItems> selectLikeWord(List<SearchItems> items,String keyword){
        //1——社交 2——游戏 3——体育 4-购物 5-工作
        String type[]={"社交","游戏","体育","购物","工作"};

        List<SearchItems> selectItems=new ArrayList<>();
        for (SearchItems i:items){
            if (i.getItemName().contains(keyword)){
                selectItems.add(i);
            }
        }

        if (selectItems.size()==0){
            return getStaticItems();
        }
        return selectItems;
    }


    private List<SearchItems> getStaticItems(){
        List<SearchItems> list=new ArrayList<>();
        SearchItems item1=new SearchItems("社交","1");
        SearchItems item2=new SearchItems("游戏","1");
        SearchItems item3=new SearchItems("体育","1");
        SearchItems item4=new SearchItems("购物","1");
        SearchItems item5=new SearchItems("工作","1");

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        return list;
    }
}
