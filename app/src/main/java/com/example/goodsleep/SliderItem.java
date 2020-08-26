package com.example.goodsleep;

public class SliderItem {
    private int image;
    private String text;

    SliderItem(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
