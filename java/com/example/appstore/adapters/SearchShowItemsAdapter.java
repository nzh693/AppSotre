package com.example.appstore.adapters;

import android.app.PendingIntent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstore.R;
import com.example.appstore.database.entity.SearchItems;

import java.util.ArrayList;
import java.util.List;

public class SearchShowItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private String keyword;
    private List<SearchItems> searchItems=new ArrayList<>();



    public SearchShowItemsAdapter(Context context,String keyword){
        this.context=context;
        this.keyword=keyword;
    }

    public void setSearchItems(List<SearchItems> searchItems) {
        this.searchItems = searchItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder=null;
        View view=null;
        view= LayoutInflater.from(context).inflate(R.layout.app_search_showitems,parent,false);
        viewHolder=new  SearchShowItemsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SearchShowItemsHolder myholder=(SearchShowItemsHolder) holder;
        myholder.tile.setText(searchItems.get(position).getItemName());
    }


    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    class SearchShowItemsHolder extends RecyclerView.ViewHolder{

        TextView tile;

        public SearchShowItemsHolder(@NonNull View itemView) {
            super(itemView);
            tile=itemView.findViewById(R.id.item_tile);

        }
    }

}
