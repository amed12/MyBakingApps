package com.sun3toline.mybakingapps.Ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sun3toline.mybakingapps.R;

/**
 * Created by coldware on 9/24/17.
 */

public class ResepActivity extends AppCompatActivity implements ResepFragment.OnFragmentInteractionListener{

    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecipeFragment();
    }

    private void initRecipeFragment(){
        ResepFragment rf = new ResepFragment();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, rf, ResepFragment.class.getSimpleName());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
