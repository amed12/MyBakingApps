package com.sun3toline.mybakingapps.Ui;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.sun3toline.mybakingapps.Networking.NetworkTask;
import com.sun3toline.mybakingapps.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    @Nullable
    private NetworkTask mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ProgressBar(this));
        initNetwork();
    }
    void initNetwork(){
        new NetworkTask().execute();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String data) {
        /* Do something */
        startActivity(new Intent(MainActivity.this, ResepActivity.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        PreferenceManager.getDefaultSharedPreferences(this).getString("ID", "");
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new NetworkTask();
        }
        return mIdlingResource;
    }


}
