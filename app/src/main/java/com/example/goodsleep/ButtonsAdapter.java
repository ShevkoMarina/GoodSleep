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

public class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.ButtonViewHolder> {

    private List<CategoryButton> mButtonsList = new ArrayList<>();
    private CardsAdapter.OnItemClickListener mListener;
    private static CardsFragment mFragment;

    public ButtonsAdapter(CardsFragment fragment) {
        mFragment = fragment;
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {

        private Button mCategoryButton;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            mCategoryButton = itemView.findViewById(R.id.ctg_button);
        }

        public void bind(final CategoryButton categoryButton) {
            mCategoryButton.setText(categoryButton.getButtonText());

            mCategoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCategoryButton.setEnabled(false);
                    mFragment.setCards(categoryButton.getButtonText());
                }
            });
        }
    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ButtonViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_button, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        holder.bind(mButtonsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mButtonsList.size();
    }

    public void setItems(Collection<CategoryButton> categoryButtons) {
        mButtonsList.addAll(categoryButtons);
        notifyDataSetChanged();
    }
}
