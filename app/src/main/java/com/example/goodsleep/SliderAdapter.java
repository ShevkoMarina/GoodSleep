package com.example.goodsleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private OnItemClickListener mListener;
    private List<SoundItem> soundItems;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public SliderAdapter(List<SoundItem> soundItems) {
        this.soundItems = soundItems;
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public SliderViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
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

        void setImage (SoundItem soundItem) {
            imageView.setImageResource(soundItem.getImageSrc());
        }

        void setText (SoundItem soundItem) {
            textView.setText(soundItem.getName());
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
                ), mListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(soundItems.get(position));
        holder.setText(soundItems.get(position));
    }

    @Override
    public int getItemCount() {
        return soundItems.size();
    }
}
