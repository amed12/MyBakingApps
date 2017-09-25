package com.sun3toline.mybakingapps.Networking;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.Util.MockData;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;

/**
 * Created by coldware on 9/24/17.
 */

public class NetworkTask extends AsyncTask<Void, Void, Void> implements IdlingResource {

    @Nullable
    private volatile ResourceCallback mCallback;

    // Idleness is controlled with this boolean.
    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    @Override
    protected Void doInBackground(Void... params) {
        try {
            AccessNetwork instance = RetrofitInstance.getInstance().create(AccessNetwork.class);

            Call<List<Resep>> recipeCall = instance.getAllRecips();

            List<Resep> recipes = recipeCall.execute().body();

            Type type = new TypeToken<List<Resep>>(){}.getType();

            MockData.DATA = new GsonBuilder().create().toJson(recipes, type);

            EventBus.getDefault().post(MockData.DATA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mCallback != null) {
            mCallback.onTransitionToIdle();
        }
    }
}