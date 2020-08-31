package com.oyatech.leaderboard.leaners;

public class LeaderDetails {
    String name, performance,country;

    public LeaderDetails(String pName, String pPerformance, String pCountry) {
        name = pName;
        performance = pPerformance;
        country = pCountry;
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

    public void setPerformance(String pPerformance) {
        performance = pPerformance;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String pCountry) {
        country = pCountry;
    }
}
