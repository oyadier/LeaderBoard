package com.oyatech.leaderboard.TopLearner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.oyatech.leaderboard.R;
import com.oyatech.leaderboard.Util.APIUtil;


public class LearnersFragment extends Fragment {
    public static final String TOP_LEANER = "/api/hours";

 static public   ProgressBar mProgressBar;
static RecyclerView mRecyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_learners, container, false);

        mRecyclerView = rootView.findViewById(R.id.learnerRecycle);
    mProgressBar = rootView.findViewById(R.id.progressBar);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);

        new TopLearnerParsing().execute(APIUtil.leardersUrl(TOP_LEANER));

        return rootView;
    }



}