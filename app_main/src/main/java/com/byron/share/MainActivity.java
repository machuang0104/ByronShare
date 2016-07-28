package com.byron.share;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byron.library.anno.InjectLayout;
import com.byron.library.anno.InjectView;
import com.byron.library.base.BaseActivity;

@InjectLayout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(id = R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void afterOnCreate(Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
