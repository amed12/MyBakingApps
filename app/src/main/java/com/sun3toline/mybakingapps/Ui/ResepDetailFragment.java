package com.sun3toline.mybakingapps.Ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.Model.Step;
import com.sun3toline.mybakingapps.R;
import com.sun3toline.mybakingapps.Ui.Adapter.IngredientsAdapterView;
import com.sun3toline.mybakingapps.Ui.Adapter.StepsAdapterView;

import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class ResepDetailFragment extends Fragment implements StepsAdapterView.OnStepAdapterViewListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public  static final String STRING_RECIPE = "com.sun3toline.mybakingapps.Ui.ResepDetailFragment.STRING_RECIPE";
    public  static final String TWO_PANE = "com.sun3toline.mybakingapps.Ui.ResepDetailFragment.TWO_PANE";

    // TODO: Rename and change types of parameters
    private String sRecipe;
    private boolean mTwoPane;

    private static final String STEPS_POSITION = "STEPS_POSITION";
    private int stepsPosition = -1;

    private OnFragmentInteractionListener mListener;

    public ResepDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sRecipe Parameter 1.
     * @return A new instance of fragment RecipeDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResepDetailFragment newInstance(String sRecipe, boolean isTwoPane) {
        ResepDetailFragment fragment = new ResepDetailFragment();
        Bundle args = new Bundle();
        args.putString(STRING_RECIPE, sRecipe);
        args.putBoolean(TWO_PANE, isTwoPane);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStepAdapterViewSelected(int position) {
        stepsPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STEPS_POSITION, stepsPosition);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(STRING_RECIPE) && getArguments().containsKey(TWO_PANE)) {
            sRecipe = getArguments().getString(STRING_RECIPE);
            mTwoPane = getArguments().getBoolean(TWO_PANE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null){
            stepsPosition = savedInstanceState.getInt(STEPS_POSITION);
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(com.sun3toline.mybakingapps.R.layout.fragment_recipe_detail, container, false);

        Resep recipe = new GsonBuilder().create().fromJson(getArguments().getString(STRING_RECIPE), Resep.class);

        List<Bahan> ingredients = recipe.getIngredients();
        RecyclerView rvIngredients = (RecyclerView) rootView.findViewById(R.id.rv_ingredients);
        IngredientsAdapterView ingredientsAdapterView = new IngredientsAdapterView(ingredients);
        rvIngredients.setAdapter(ingredientsAdapterView);
        rvIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Step> steps = recipe.getSteps();
        RecyclerView rvSteps = (RecyclerView) rootView.findViewById(R.id.rv_steps);
        StepsAdapterView stepsAdapterView = new StepsAdapterView(steps, mTwoPane, this);
        rvSteps.setAdapter(stepsAdapterView);
        rvSteps.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (stepsPosition != -1)
            rvSteps.smoothScrollToPosition(stepsPosition);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentMasterSelected(int position);
    }
}
