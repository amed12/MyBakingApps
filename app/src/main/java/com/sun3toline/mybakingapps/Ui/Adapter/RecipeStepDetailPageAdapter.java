package com.sun3toline.mybakingapps.Ui.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sun3toline.mybakingapps.Model.Step;
import com.sun3toline.mybakingapps.Ui.ResepStepDescriptionItemFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class RecipeStepDetailPageAdapter extends FragmentPagerAdapter {

    Context context;
    List<Step> steps;

    public RecipeStepDetailPageAdapter(FragmentManager fm, Context context, List<Step> steps) {
        super(fm);
        this.context = context;
        this.steps = new ArrayList<>();
        this.steps.addAll(steps);
    }

    @Override
    public Fragment getItem(int position) {
        Step step = steps.get(position);
        String videoURL = step.getVideoURL();
        String description = step.getDescription();
        return ResepStepDescriptionItemFragment.newInstance(videoURL, description);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Step step = steps.get(position);
        return step.getShortDescription();
    }

    @Override
    public int getCount() {
        return steps.size();
    }
}

