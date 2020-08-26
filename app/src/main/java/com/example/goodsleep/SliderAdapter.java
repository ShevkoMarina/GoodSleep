package com.example.goodsleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderItem> sliderItems;
    private ViewPager2 vIewPager2;

    public SliderAdapter(List<SliderItem> sliderItems, ViewPager2 vIewPager2) {
        this.sliderItems = sliderItems;
        this.vIewPager2 = vIewPager2;
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public SliderViewHolder(@NonNull View itemView) {
             super(itemView);
             imageView = itemView.findViewById(R.id.image_for_card);
             textView = itemView.findViewById(R.id.text_for_card);
        }

        void setImage (SliderItem sliderItem) {
            imageView.setImageResource(sliderItem.getImage());
        }

        void setText (SliderItem sliderItem) {
            textView.setText(sliderItem.getText());
        }
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        holder.setText(sliderItems.get(position));
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }
}
