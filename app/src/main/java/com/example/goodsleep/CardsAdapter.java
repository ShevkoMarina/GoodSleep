package com.example.goodsleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private OnItemClickListener mListener;
    private ArrayList<SoundItem> mSoundItems = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public CardViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_for_card);
            textView = itemView.findViewById(R.id.text_for_card);

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
        }

        public void bind(SoundItem soundItem) {
            imageView.setImageResource(soundItem.getImageSrc());
            textView.setText(soundItem.getName());
        }
    }

    @NonNull
    @Override
    public CardsAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsAdapter.CardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item,
                        parent,
                        false
                ), mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.CardViewHolder holder, int position) {
        holder.bind(mSoundItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mSoundItems.size();
    }

    public void setItems(Collection<SoundItem> soundItems) {
        mSoundItems.addAll(soundItems);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mSoundItems.clear();
        notifyDataSetChanged();
    }
}
