package com.example.appstore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.database.entity.SearchItems;
import com.example.appstore.database.entity.SearchResults;

import java.util.ArrayList;
import java.util.List;

public class SearchShowResutltAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private String keyword;

    private List<SearchResults> results=new ArrayList<>();//app
    private List<Integer> icons=new ArrayList<>();//app图标
    private List<Integer> images=new ArrayList<>();//app展示图片


    public SearchShowResutltAdapter(Context context, String keyword){
        this.context=context;
        this.keyword=keyword;
    }

    public void setResults(List<SearchResults> results) {
        this.results = results;
    }

    public void setIcons(List<Integer> icons) {
        this.icons = icons;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        View view=null;
        view= LayoutInflater.from(context).inflate(R.layout.app_search_showresult,parent,false);
        viewHolder=new  SearchShowResultHolder(view);
        return viewHolder;
    }


    //绑定搜索结果数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        SearchShowResultHolder myholder=(SearchShowResultHolder) holder;
        myholder.icon.setImageResource(icons.get(Integer.parseInt(results.get(position).getIconPath())-1));
        myholder.appName.setText(results.get(position).getAppName());
        myholder.appDesc.setText(results.get(position).getAppDecs());
        myholder.appNums.setText(results.get(position).getAppNums());
        myholder.showImage.setImageResource(images.get(Integer.parseInt(results.get(position).getAppShowIamge())-1));

        int level=Integer.parseInt(results.get(position).getAppLeves());//星级
        int i=0;
        for(ImageView im:((SearchShowResultHolder) holder).levels){
            if (i<level){
                im.setVisibility(View.VISIBLE);
            }
            else {
                im.setVisibility((View.INVISIBLE));
            }
            i++;
        }

    }

    //模糊查询匹配
    private List<String> selectLikeWord(String[] items,String keyword){
        List<String> selectItems=new ArrayList<>();
        for (String item:items){
            System.out.println(item+ keyword+item.contains(keyword));
            if (item.contains(keyword)){
                selectItems.add(item);
            }
        }
        return  selectItems;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class SearchShowResultHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView appName;
        TextView appDesc;
        TextView appNums;
        ImageView showImage;
        ImageView level_1;
        ImageView level_2;
        ImageView level_3;
        ImageView level_4;
        ImageView level_5;

        List<ImageView> levels=new ArrayList<>();

        public SearchShowResultHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.search_icon);
            appName=itemView.findViewById(R.id.search_appName);
            appDesc=itemView.findViewById(R.id.search_appDecs);
            appNums=itemView.findViewById(R.id.search_nums);
            showImage=itemView.findViewById(R.id.search_showImage);
            level_1=itemView.findViewById(R.id.search_level1);
            level_2=itemView.findViewById(R.id.search_level2);
            level_3=itemView.findViewById(R.id.search_level3);
            level_4=itemView.findViewById(R.id.search_level4);
            level_5=itemView.findViewById(R.id.search_level5);

            levels.add(level_1);
            levels.add(level_2);
            levels.add(level_3);
            levels.add(level_4);
            levels.add(level_5);

        }
    }

}
