package com.sun3toline.mybakingapps.Ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.Model.Resep;
import com.sun3toline.mybakingapps.Ui.Adapter.RecipeAdapterView;
import com.sun3toline.mybakingapps.Util.DisplayUtil;
import com.sun3toline.mybakingapps.Util.MockData;

import java.util.List;

/**
 * Created by coldware on 9/24/17.
 */

public class ResepFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean mTwoPane = false;

    private OnFragmentInteractionListener mListener;

    public ResepFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResepFragment newInstance(String param1, String param2) {
        ResepFragment fragment = new ResepFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if (DisplayUtil.isSW600dp(getActivity())){
            mTwoPane = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(com.sun3toline.mybakingapps.R.layout.fragment_recipe, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(com.sun3toline.mybakingapps.R.id.rv);
        List<Resep> recipes = new GsonBuilder().create().fromJson(MockData.DATA, new TypeToken<List<Resep>>(){}.getType());
        RecipeAdapterView recipeAdapterView = new RecipeAdapterView(recipes);
        rv.setAdapter(recipeAdapterView);
        if (mTwoPane){
            rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        } else {
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }
}
