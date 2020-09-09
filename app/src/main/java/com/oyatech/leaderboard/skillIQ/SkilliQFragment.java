package com.oyatech.leaderboard.skillIQ;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.oyatech.leaderboard.Util.APIUtil;
import com.oyatech.leaderboard.R;
import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.net.URL;
import java.util.ArrayList;


public class SkilliQFragment extends Fragment {
     public final String TOP_SKILL_IQ = "/api/skilliq";

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
        //returning a Url to execute callback
       new SkillAPI().execute(APIUtil.leardersUrl(TOP_SKILL_IQ));
return rootView;
    }

    public class SkillAPI extends AsyncTask<URL,Void,String> {


        public String skillResult = null;

        @Override
        protected String doInBackground(URL... pURLS) {
            try {
                progress.setVisibility(View.VISIBLE);
                URL learnerUrl = (URL) pURLS[0];
                skillResult = APIUtil.getLearnersJSONString(learnerUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return skillResult;
        }

        @Override
        protected void onPostExecute(String pS) {
            super.onPostExecute(pS);
            ArrayList<LeaderDetails> leaderSkill = APIUtil.getSkillLeaders(skillResult);
            progress.setVisibility(View.INVISIBLE);
            SkillAdapter adapterSkill = new SkillAdapter(leaderSkill);
            mRecyclerView.setAdapter(adapterSkill);


        }
    }
}
