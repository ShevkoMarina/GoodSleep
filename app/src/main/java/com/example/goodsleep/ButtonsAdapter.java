package com.example.goodsleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.TweetViewHolder> {

    private List<CategoryButton> mButtonsList = new ArrayList<>();
    private SliderAdapter.OnItemClickListener mListener;

    public static class TweetViewHolder extends RecyclerView.ViewHolder {

        private Button mCategoryButton;

        public TweetViewHolder(@NonNull View itemView, final SliderAdapter.OnItemClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mCategoryButton = itemView.findViewById(R.id.ctg_button);
        }

        public void bind(CategoryButton categoryButton) {
            mCategoryButton.setText(categoryButton.getButtonText());
        }
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TweetViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_button, parent, false),
                mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        holder.bind(mButtonsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mButtonsList.size();
    }

    public void setOnItemClickListener(SliderAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    public void setItems(Collection<CategoryButton> categoryButtons) {
        mButtonsList.addAll(categoryButtons);
        notifyDataSetChanged();
    }
}
