package com.byron.library.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.byron.library.anno.Injector;

/**
 * Created by admin on 2016/7/27.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.injectLayout(this);
        Injector.injectViews(this);
        afterOnCreate(savedInstanceState);
    }

    protected abstract void afterOnCreate(Bundle savedInstanceState);

}
