package com.hfrad.materialdesign.di.module;

import android.widget.ImageView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.hfrad.materialdesign.mvp.view.image.GlideImageLoader;
import com.hfrad.materialdesign.mvp.view.image.IImageLoader;

@Module
public class ImageModule {
    @Singleton
    @Provides
    IImageLoader<ImageView> getImageLoader() {
        return new GlideImageLoader();
    }
}
