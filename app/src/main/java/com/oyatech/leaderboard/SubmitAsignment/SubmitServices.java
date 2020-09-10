package com.oyatech.leaderboard.SubmitAsignment;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SubmitServices {
    //A service class to implement the Retrofit interface
    private final static String SEND_URL ="https://docs.google.com/forms/d/e/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(SEND_URL)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();

    public <P>P buildServices(Class<P>endPoint)
    {
        return retrofit.create(endPoint);
    }
}
