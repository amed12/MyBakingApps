package com.sun3toline.mybakingapps.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun3toline.mybakingapps.Model.Step;
import com.sun3toline.mybakingapps.R;
import com.sun3toline.mybakingapps.Ui.Adapter.RecipeStepDetailPageAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class ResepStepDetailActivity extends AppCompatActivity {

    public static final String STEPS = "com.sun3toline.mybakingapps.Ui.ResepStepDetailActivity.STEPS";

    private ViewPager viewPager;
    private RecipeStepDetailPageAdapter adapter;

    private List<Step> steps = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);
        viewPager = (ViewPager)findViewById(R.id.view_pager);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(STEPS)){
            String ssteps = intent.getStringExtra(STEPS);
            Type type = new TypeToken<List<Step>>(){}.getType();
            steps.clear();
            steps = new GsonBuilder().create().fromJson(ssteps, type);
        }

        adapter = new RecipeStepDetailPageAdapter(getSupportFragmentManager(), this, steps);
        viewPager.setAdapter(adapter);
    }
}
