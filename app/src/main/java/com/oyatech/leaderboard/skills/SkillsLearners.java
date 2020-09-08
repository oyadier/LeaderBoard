package com.oyatech.leaderboard.skills;

import com.oyatech.leaderboard.SubmitAsignment.StudentData;
import com.oyatech.leaderboard.leaners.LeaderDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillsLearners {

    @GET ("/api/skilliq")
    Call<List<LeaderDetails>> getSkillLeaders(
    );

}
