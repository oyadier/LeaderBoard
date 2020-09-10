package com.oyatech.leaderboard.SubmitAsignment;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.oyatech.leaderboard.LeaderBoard;
import com.oyatech.leaderboard.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {

    TextView name, lastName, email, gitAddress;
    Button submit;
    Dialog mDialog;
    ImageView mImageView;
    ProgressBar mProgressBar;
    int submissionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();

        setContentView(R.layout.activity_submit);

       initialize();

        mDialog = new Dialog(this);


        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final SubmitAssignment submitAssignment = new SubmitServices().buildServices(SubmitAssignment.class);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                String firstName = name.getText().toString();
                String LName = lastName.getText().toString();
                String UserEmail = email.getText().toString();
                String git = gitAddress.getText().toString();
                if (!firstName.isEmpty() && !LName.isEmpty() && !UserEmail.isEmpty() && !git.isEmpty()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    //Passing student details to the interface
                    final Call<Void> mStudentDataCall = submitAssignment.sendAssignment(
                            UserEmail, firstName, LName, git
                    );
                    mStudentDataCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.i("Submission", "onResponse: " + response.code());
                            submissionStatus = response.code();
                            if (response.isSuccessful()) {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        sentStatus();
                                    }
                                }, 2000);

                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.i("Submission", "onFailure: " + t.getMessage());
                            mProgressBar.setVisibility(View.INVISIBLE);
                            failStatus();
                            Snackbar.make(v,"Check Your Internet connection",Snackbar.LENGTH_LONG).show();


                        }
                    });
                } else
                    Toast.makeText(Submit.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Submit.this, LeaderBoard.class));
            }
        });

    }

    private void sentStatus()
    {
        mDialog.setContentView(getLayoutInflater().inflate(R.layout.sent
                , null));
        mDialog.show();
    }

    private void failStatus()
    {
        mDialog.setContentView(getLayoutInflater().inflate(R.layout.failed
                , null));
        mDialog.show();
    }

    protected void setFullScreen()
    {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            View customScreen = getWindow().getDecorView();
            customScreen.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );

            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    private void    initialize()
    {
        name = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        email = findViewById(R.id.txtEmail);
        gitAddress = findViewById(R.id.txtGithubAddress);
        submit = findViewById(R.id.btnSubmit);
        mImageView = findViewById(R.id.back);
        mProgressBar = findViewById(R.id.submitProgress);
        mProgressBar.setVisibility(View.INVISIBLE);

    }
}