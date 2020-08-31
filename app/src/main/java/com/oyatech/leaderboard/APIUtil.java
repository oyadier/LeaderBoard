package com.oyatech.leaderboard;

import android.net.Uri;

import com.oyatech.leaderboard.leaners.LeaderDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class APIUtil {
    public APIUtil() {
    }

    public static URL leardersUrl(String base) {
        final String BASE_URL = "https://gadsapi.herokuapp.com";

        URL url = null;
        Uri leaderUri = Uri.parse(new StringBuilder().append(BASE_URL).append(base).toString());
        try {
            url = new URL(leaderUri.toString());
        } catch (MalformedURLException pE) {
            pE.printStackTrace();
        }
        return url;
    }

    public static String getLearnersJSONString(URL url) {
        HttpURLConnection connection = null;
        String jsonResult = " ";
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(stream);
            int data = reader.read();


            //Looping through all the data values
            while (data != -1) {
                //converting the int values to characters
                char retrievedData = (char) data;
                //Storing the int value of the data into a string
                jsonResult += retrievedData;
                //continue reading
                data = reader.read();
            }
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return jsonResult;
    }

    public static ArrayList<LeaderDetails> getLeaders(String jsonResult) {

        ArrayList<LeaderDetails> leaderDetails = new ArrayList<>();
        try {
            leaderDetails = new ArrayList<>();
            JSONArray leadersArray = new JSONArray(jsonResult);
            for (int i = 0; i <= leadersArray.length(); i++) {
                JSONObject leader = leadersArray.getJSONObject(i);
                String leaderName = leader.getString("name");
                String leaderScore = leader.getString("hours");
                String leaderCountry = leader.getString("country");

                leaderDetails.add(new LeaderDetails(leaderName, leaderScore, leaderCountry));
            }
            return leaderDetails;
        } catch (JSONException pE) {
            pE.printStackTrace();
        }
        return leaderDetails;
    }

  public static ArrayList<LeaderDetails> getSkillLeaders(String jsonResult) {
        ArrayList<LeaderDetails> skillLeaders = new ArrayList<>();
        try {
            skillLeaders = new ArrayList<>();
            JSONArray leadersArray = new JSONArray(jsonResult);
            for (int i = 0; i <= leadersArray.length(); i++) {
                JSONObject leader = leadersArray.getJSONObject(i);
                String leaderName = leader.getString("name");
                String leaderScore = leader.getString("score");
                String leaderCountry = leader.getString("country");
                skillLeaders.add(new LeaderDetails(leaderName, leaderScore, leaderCountry));
            }
            return skillLeaders;
        } catch (JSONException pE) {
            pE.printStackTrace();
        }
        return skillLeaders;
    }
}
