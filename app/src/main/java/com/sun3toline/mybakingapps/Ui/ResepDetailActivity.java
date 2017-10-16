package com.sun3toline.mybakingapps.Ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.GsonBuilder;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.Model.SelectedPosition;
import com.sun3toline.mybakingapps.Model.Step;
import com.sun3toline.mybakingapps.R;
import com.sun3toline.mybakingapps.Ui.Adapter.RecipeStepDetailPageAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class ResepDetailActivity extends AppCompatActivity
        implements ResepDetailFragment.OnFragmentInteractionListener{

    public static final String RECIPE = "com.sun3toline.mybakingapps.Ui.ResepDetailActivity.RECIPE";

    private String sRecipe;
    private Resep recipe;
    private boolean mTwoPane;

    private ViewPager viewPager;
    private RecipeStepDetailPageAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        if (getIntent() != null && getIntent().hasExtra(RECIPE)){
            sRecipe = getIntent().getStringExtra(RECIPE);
            recipe = new GsonBuilder().create().fromJson(sRecipe, Resep.class);
        }

        if (findViewById(R.id.view_pager) != null){
            mTwoPane = true;
            initMasterDetail(savedInstanceState, sRecipe, recipe);
        } else {
            mTwoPane = false;
            init(savedInstanceState, sRecipe);
        }
    }

    private void init(Bundle savedInstanceState, String recipe){
        if (savedInstanceState == null){
            ResepDetailFragment rdf = ResepDetailFragment.newInstance(recipe, mTwoPane);
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, rdf, ResepFragment.class.getSimpleName());
            ft.commit();
        }
    }

    private void initMasterDetail(Bundle savedInstanceState, String SRecipe, Resep recipe){
        init(savedInstanceState, sRecipe);
        initViewPager(savedInstanceState, recipe.getSteps());
    }

    private void initViewPager(Bundle savedInstanceState, List<Step> steps){
        if (savedInstanceState == null){
            viewPager = (ViewPager)findViewById(R.id.view_pager);
            adapter = new RecipeStepDetailPageAdapter(getSupportFragmentManager(), this, steps);
            viewPager.setAdapter(adapter);
        }
    }

    @Override
    public void onFragmentMasterSelected(int position) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectedPosition selectedPosition) {
        /* Do something */
        viewPager.setCurrentItem(selectedPosition.getPosition());
    }

}
