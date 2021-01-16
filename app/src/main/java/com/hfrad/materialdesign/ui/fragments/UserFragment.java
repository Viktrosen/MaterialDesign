package com.hfrad.materialdesign.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import com.hfrad.materialdesign.GithubApplication;
import com.hfrad.materialdesign.R;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.presenter.UserPresenter;
import com.hfrad.materialdesign.mvp.view.UserView;
import com.hfrad.materialdesign.ui.BackButtonListener;
import com.hfrad.materialdesign.ui.adapter.ReposotoriesRVAdapter;

public class UserFragment extends MvpAppCompatFragment implements UserView, BackButtonListener {
    private static final String USER_ARG = "user";

    private RecyclerView mRecyclerView;
    private ReposotoriesRVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View mView;

    @InjectPresenter
    UserPresenter mPresenter;

    @ProvidePresenter
    UserPresenter provideUserPresenter() {
        final Picture user = getArguments().getParcelable(USER_ARG);

        return new UserPresenter(user);
    }

    public static UserFragment newInstance(Picture user) {
        UserFragment fragment = new UserFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER_ARG, user);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.rv_repositories);

        return mView;
    }

    @Override
    public void init() {
        mLayoutManager = new LinearLayoutManager(mView.getContext());

        mAdapter = new ReposotoriesRVAdapter(mPresenter.getPresenter());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void release() {
        GithubApplication.INSTANCE.releaseRepositorySubcomponent();
    }

    @Override
    public boolean backPressed() {
        return mPresenter.backPressed();
    }
}
