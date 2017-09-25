package com.sun3toline.mybakingapps.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.GsonBuilder;
import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class BakingAppDataProvider implements RemoteViewsService.RemoteViewsFactory {

    public static final String SELECTED_RECIPE = "com.sun3toline.mybakingapps.Widget.BakingAppDataProvider.SELECTED_RECIPE";

    private Context context;
    private Intent intent;
    private List<Bahan> ingredientList = new ArrayList<>();

    public BakingAppDataProvider(Context context, Intent intent){
        this.context = context;
        this.intent = intent;
    }

    void initData(){
        String sRecipe = intent.getStringExtra(SELECTED_RECIPE);
        Resep recipe = new GsonBuilder().create().fromJson(sRecipe, Resep.class);
        ingredientList.addAll(recipe.getIngredients());
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Bahan ingredient = ingredientList.get(position);
        RemoteViews views = new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
        views.setTextViewText(android.R.id.text1, String.format(context.getString(R.string.ingredients_detail)
                , ingredient.getQuantity(), ingredient.getMeasure(), ingredient.getIngredient()));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
