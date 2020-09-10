package com.oyatech.leaderboard.SubmitAsignment;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubmitAssignment {
@FormUrlEncoded
@POST("1FAIpQLSdWpJxFTi-y3d3tyZTxJZHP2rXxsrVXQ3QBDlVFIH83OxRfBg/formResponse")
 Call<Void>  sendAssignment(
         @Field("entry.518474370") String email,
            @Field("entry.2018648461") String firstName,
            @Field("entry.460112723") String lastName,
            @Field("entry.1801192203") String gitHubAddress

        /*@Field("entry.1824927963") String email,
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String lastName,
            @Field("entry.284483984") String gitHubAddress*/
    );
}

