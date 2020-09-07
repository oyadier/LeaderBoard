package com.oyatech.leaderboard;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkilliQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkilliQFragment extends Fragment {
final String TOP_SKILL_IQ = "/api/skilliq";
static boolean isSkill ;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SkilliQFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SkilliQFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkilliQFragment newInstance(String param1, String param2) {
        SkilliQFragment fragment = new SkilliQFragment();
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
    }

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView =  inflater.inflate(R.layout.fragment_learners, container, false);

       mRecyclerView = rootView.findViewById(R.id.learnerRecycle);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL,false);

        mRecyclerView.setLayoutManager(manager);
       new SkillLearners().execute(APIUtil.leardersUrl(TOP_SKILL_IQ));
        return rootView;
    }

    private class SkillLearners extends AsyncTask <URL,Void,String> {

        String skills = null;
        @Override
        protected String doInBackground(URL... pURLS) {
            try {
                isSkill = true;
                URL skillUrl = (URL) pURLS[0];
                skills = APIUtil.getLearnersJSONString(skillUrl);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return skills;
        }

        @Override
        protected void onPostExecute(String pS) {
            super.onPostExecute(pS);
            if(!pS.isEmpty())
            {

            }
            ArrayList<LeaderDetails> skillLeaders = APIUtil.getSkillLeaders(skills);
            RecycleViewAdapter adapter = new RecycleViewAdapter(skillLeaders);
            mRecyclerView.setAdapter(adapter);
        }
    }
}