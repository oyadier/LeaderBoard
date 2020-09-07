package com.oyatech.leaderboard;

import android.os.AsyncTask;
import android.view.View;

import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.net.URL;
import java.util.ArrayList;

import static com.oyatech.leaderboard.LearnersFragment.mProgressBar;
import static com.oyatech.leaderboard.LearnersFragment.mRecyclerView;

public class APIParsing extends AsyncTask<URL,Void,String> {


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

            RecycleViewAdapter adapter = new RecycleViewAdapter(leader);

            mRecyclerView.setAdapter(adapter);


    }

}
