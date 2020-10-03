package com.example.goodsleep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private OnItemClickListener mListener;
    private OnItemClickListener mFavListener;
    private ArrayList<SoundItem> mSoundItems = new ArrayList<>();
    private Context mContext;

    public CardsAdapter(Context context) {
        mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public void setOnFavClickListener(OnItemClickListener listener) {
        mFavListener = listener;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private Button favButton;
        private Context context;

        public CardViewHolder(@NonNull View itemView, final OnItemClickListener listener,
                              final Context context, final OnItemClickListener favListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_for_card);
            textView = itemView.findViewById(R.id.text_for_card);
            favButton = itemView.findViewById(R.id.fav_btn);
            this.context = context;

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

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (favListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void bind(SoundItem soundItem) {
            if (soundItem.isFavorite()) {
                favButton.setCompoundDrawables(null, null,
                        ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_favorite_32, null), null);
            }
            imageView.setImageResource(soundItem.getImageSrcLow());
            textView.setText(soundItem.getName());
        }
    }

    @NonNull
    @Override
    public CardsAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsAdapter.CardViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.card_item,
                        parent,
                        false
                ), mListener, mContext, mFavListener
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
}
