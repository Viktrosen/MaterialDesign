package com.hfrad.materialdesign.mvp.view.list;

public interface UserItemView extends IItemView {
    void setTitle(String text);
    void loadPicture(String url);
    void setExplanation(String text);
}
