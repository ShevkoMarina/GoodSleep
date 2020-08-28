package com.example.goodsleep;

import java.util.List;

public class CategoryItem {

    private String categoryTitle;
    private List<SoundItem> soundItemList;

    public CategoryItem(String categoryTitle, List<SoundItem> soundItemList) {
        this.categoryTitle = categoryTitle;
        this.soundItemList = soundItemList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<SoundItem> getSoundItemList() {
        return soundItemList;
    }

    public void setSoundItemList(List<SoundItem> soundItemList) {
        this.soundItemList = soundItemList;
    }
}
