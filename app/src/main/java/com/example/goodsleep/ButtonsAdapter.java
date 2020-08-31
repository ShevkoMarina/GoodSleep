package com.example.goodsleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ButtonsAdapter extends RecyclerView.Adapter<ButtonsAdapter.ButtonViewHolder> {

    private List<CategoryButton> mButtonsList = new ArrayList<>();
    private static ViewPager2 mViewPager;

    public ButtonsAdapter(ViewPager2 viewPager) {
        mViewPager = viewPager;
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder {

        private Button mCategoryButton;
        public static ArrayList<Button> mButtons = new ArrayList<>();

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            mCategoryButton = itemView.findViewById(R.id.ctg_button);
        }

        public void bind(final CategoryButton categoryButton) {
            mCategoryButton.setText(categoryButton.getButtonText());
            mButtons.add(mCategoryButton);

            mCategoryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    enableAllButtons();
                    if (mCategoryButton.isEnabled()) {
                        mCategoryButton.setEnabled(false);
                    }
                    mViewPager.setCurrentItem(getAdapterPosition());
                }
            });
        }

        public void enableAllButtons() {
            for (Button button : mButtons) {
                if (!button.isEnabled()) {
                    button.setEnabled(true);
                }
            }
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

    public void disableButton(int position) {
        ButtonViewHolder.mButtons.get(position).performClick();
    }

    public void setItems(Collection<CategoryButton> categoryButtons) {
        mButtonsList.addAll(categoryButtons);
        notifyDataSetChanged();
    }
}
