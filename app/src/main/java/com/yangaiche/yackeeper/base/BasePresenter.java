package com.yangaiche.yackeeper.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by mr_yang on 16-3-21.
 */
public abstract class BasePresenter<V>{

    protected Reference<V> mViewRef;

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);//把view存在弱引用中
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
