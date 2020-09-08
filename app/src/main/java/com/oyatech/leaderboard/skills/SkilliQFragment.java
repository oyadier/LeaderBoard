package com.oyatech.leaderboard.skills;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oyatech.leaderboard.R;
import com.oyatech.leaderboard.SubmitAsignment.SubmitServices;
import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkilliQFragment extends Fragment {
    final String TOP_SKILL_IQ = "/api/skilliq";

    public SkilliQFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    RecyclerView mRecyclerView;
    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_learners, container, false);
        progress = rootView.findViewById(R.id.progressBar);
//Setting the layout for the recycle view
        mRecyclerView = rootView.findViewById(R.id.learnerRecycle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(manager);
        //implementing the retrofit interface
        SkillsLearners skill = new SubmitServices().buildServices(SkillsLearners.class);
        //using the  submit object to fully implement the interface
        final Call<List<LeaderDetails>> detailsCall = skill.getSkillLeaders();
        //calling the enqueue method to make the RESTful endpoint request
        detailsCall.enqueue(new Callback<List<LeaderDetails>>() {
            @Override
            public void onResponse(Call<List<LeaderDetails>> call, Response<List<LeaderDetails>> response) {
                //binding the adapter with the result of the request

                    progress.setVisibility(View.INVISIBLE);
                mRecyclerView.setAdapter(new SkillAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<LeaderDetails>> call, Throwable t) {

                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

return rootView;
    }
}
