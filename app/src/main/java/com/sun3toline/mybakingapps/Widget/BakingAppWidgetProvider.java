package com.sun3toline.mybakingapps.Widget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.R;
import com.sun3toline.mybakingapps.Util.MockData;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class BakingAppWidgetProvider extends AppWidgetProvider {
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int widgetId : appWidgetIds) {
            RemoteViews mView = initViews(context, appWidgetManager, widgetId);
            appWidgetManager.updateAppWidget(widgetId, mView);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId) {

        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.test_layout);
        Intent intent = new Intent(context, BakingAppWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

        Type type = new TypeToken<List<Resep>>(){}.getType();
        List<Resep> recipes = new GsonBuilder().create().fromJson(MockData.DATA_1, type);

        Resep recipe = recipes.get(0);
        String sRecipe = new GsonBuilder().create().toJson(recipe);

        intent.putExtra(BakingAppDataProvider.SELECTED_RECIPE, sRecipe);



        mView.setRemoteAdapter(widgetId, R.id.list, intent);

        return mView;
    }
}
