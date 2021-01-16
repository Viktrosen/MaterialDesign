package com.hfrad.materialdesign.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import com.hfrad.materialdesign.GithubApplication;
import com.hfrad.materialdesign.R;
import com.hfrad.materialdesign.mvp.presenter.list.IUserListPresenter;
import com.hfrad.materialdesign.mvp.view.list.UserItemView;
import com.hfrad.materialdesign.mvp.view.image.IImageLoader;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.ViewHolder> {

    private IUserListPresenter presenter;

    @Inject
    IImageLoader<ImageView> imageLoader;

    public UserRVAdapter(IUserListPresenter presenter) {
       this.presenter = presenter;

        GithubApplication.INSTANCE.initUserSubcomponent().inject(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.position = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.onItemClick(holder);
            }
        });

        presenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements UserItemView {
        TextView textViewTitle;
        TextView textViewExplanation;
        ImageView avatarView;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = (TextView)itemView.findViewById(R.id.tv_title);
            textViewExplanation = (TextView)itemView.findViewById(R.id.tv_explanation);
            avatarView = (ImageView)itemView.findViewById(R.id.iv_picture);
        }

        @Override
        public void setTitle(String text) {
            textViewTitle.setText(text);
        }

        @Override
        public void loadPicture(String url) {
            imageLoader.loadImage(url, avatarView);
        }

        @Override
        public void setExplanation(String text) {
            textViewExplanation.setText(text);
        }


        @Override
        public int getPos() {
            return position;
        }
    }
}
