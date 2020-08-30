package com.example.goodsleep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    public SliderAdapter sliderAdapter;
    private Context context;
    private List<CategoryItem> categoryItemsList;
    public static boolean isItemClicked = false;

    public CategoryAdapter(Context context, List<CategoryItem> categoryItems) { this.context = context;
        categoryItemsList = categoryItems;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(
                R.layout.category_item,
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.categoryTitle.setText(categoryItemsList.get(position).getCategoryTitle());
        InitHorizontalRecycleView(holder.itemRecycler, categoryItemsList.get(position).getSoundItemList());
    }

    @Override
    public int getItemCount() {
        return categoryItemsList.size();
    }

    public static final class CategoryViewHolder extends  RecyclerView.ViewHolder {

        TextView categoryTitle;
        RecyclerView itemRecycler;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.category_title);
            itemRecycler = itemView.findViewById(R.id.item_recycler);
        }
    }

    private void InitHorizontalRecycleView(final RecyclerView recyclerView, final List<SoundItem> soundItems) {
        sliderAdapter = new SliderAdapter(soundItems, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(sliderAdapter);

        sliderAdapter.setOnItemClickListener(new SliderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (!isItemClicked) {
                    Intent intent = new Intent(context, CardActivity.class);
                    intent.putExtra("Sound Item", soundItems.get(position));
                    context.startActivity(intent);
                    isItemClicked = true;
                }
            }
        });
    }
}
