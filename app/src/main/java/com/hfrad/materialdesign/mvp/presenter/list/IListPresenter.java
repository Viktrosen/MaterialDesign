package com.hfrad.materialdesign.mvp.presenter.list;

import com.hfrad.materialdesign.mvp.view.list.IItemView;

public interface IListPresenter<V extends IItemView> {
    void onItemClick(V view);
    void bindView(V view);
    int getCount();
}
