package com.oyatech.leaderboard;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIParsing extends AsyncTask<String,Void,String> {

    TextView learners;

 static String finalResult = " ";


    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... pStrings) {


        try {
            URL url = new URL(pStrings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            int data = reader.read();


            //Looping through all the data values
            while (data != -1)
            {
                //converting the int values to characters
                char retrievedData = (char) data;
                //Storing the int value of the data into a string
                finalResult +=retrievedData;
                //continue reading
                data = reader.read();
            }
            return finalResult;
        } catch (IOException pE) {
            pE.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String pS) {
        super.onPostExecute(pS);
        Log.d("APIParsing: ", "onPostExecute: " +pS);
//Setting the result to a static variable
        finalResult = pS;


    }

    public void allViews(View pView)
    {
        ProgressBar progressBar = pView.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
    }
}
