package org.erickzarat.android.facebookrecipes.libs.di;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import dagger.Module;
import dagger.Provides;
import org.erickzarat.android.facebookrecipes.libs.GlideImageLoader;
import org.erickzarat.android.facebookrecipes.libs.GreenRobotEventBus;
import org.erickzarat.android.facebookrecipes.libs.base.EventBus;
import org.erickzarat.android.facebookrecipes.libs.base.ImageLoader;

import javax.inject.Singleton;

/**
 * Created by zarathos on 26/06/16
 */
@Module
public class LibsModule {

    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return this.activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
