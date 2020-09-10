package com.oyatech.leaderboard.leaners;

public class LeaderDetails {
    String name, performance,country;

    public LeaderDetails(String pName, String pPerformance, String pCountry) {
        name = pName;
        performance = pPerformance;
        country = pCountry;
    }


    public LeaderDetails(String pName, String pPerformance, String pCountry, String image) {
        name = pName;
        performance = pPerformance;
        country = pCountry;
        image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public String getPerformance() {
        return performance;
    }


    public String getCountry() {
        return country;
    }

}
