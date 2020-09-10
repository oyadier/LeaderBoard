package com.oyatech.leaderboard.TopLearner;

import android.os.AsyncTask;
import android.view.View;

import com.oyatech.leaderboard.Util.APIUtil;
import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.net.URL;
import java.util.ArrayList;

import static com.oyatech.leaderboard.TopLearner.LearnersFragment.mProgressBar;
import static com.oyatech.leaderboard.TopLearner.LearnersFragment.mRecyclerView;

public class TopLearnerParsing extends AsyncTask<URL,Void,String> {


 static String learnersResult = null;
    @Override
    protected String doInBackground(URL... pURLS) {
        try {mProgressBar.setVisibility(View.VISIBLE);
             URL learnerUrl = (URL) pURLS[0];
                learnersResult = APIUtil.getLearnersJSONString(learnerUrl);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return learnersResult;
    }

    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);

            ArrayList<LeaderDetails> leader = APIUtil.getLeaders(learnersResult);
                mProgressBar.setVisibility(View.INVISIBLE);
            TopLearnerAdapter adapter = new TopLearnerAdapter(leader);
            mRecyclerView.setAdapter(adapter);


    }

}
