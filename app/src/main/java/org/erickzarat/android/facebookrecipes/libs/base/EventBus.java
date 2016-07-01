package org.erickzarat.android.facebookrecipes.libs.base;

/**
 * Created by zarathos on 26/06/16
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
