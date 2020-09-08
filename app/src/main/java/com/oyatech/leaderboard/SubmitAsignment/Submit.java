package com.oyatech.leaderboard.SubmitAsignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.oyatech.leaderboard.LeaderBoard;
import com.oyatech.leaderboard.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Submit extends AppCompatActivity  {

    TextView name, lastName,email,gitAddress;
    Button submit;
    Dialog mDialog ;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
       /* getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);*/
        setContentView(R.layout.activity_submit);

/**
 * TODO: CUSTOMIZE THE TOOLBAR TO DISPLAY ON THE LEADER BOARD
 */
        name = findViewById(R.id.txtFirstName);
        lastName = findViewById(R.id.txtLastName);
        email = findViewById(R.id.txtEmail);
        gitAddress = findViewById(R.id.txtGithubAddress);
        submit = findViewById(R.id.btnSubmit);
        mImageView = findViewById(R.id.back);


        mDialog = new Dialog(this);


        mDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        final SubmitAssignment   submitAssignment = new SubmitServices().buildServices(SubmitAssignment.class);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                confirmation();
                String firstName = name.getText().toString();
                String LName = lastName.getText().toString();
                String UserEmail = email.getText().toString();
                String git = gitAddress.getText().toString();
                if(!firstName.isEmpty() && !LName.isEmpty() && !UserEmail.isEmpty()&&!git.isEmpty()) {
            final    Call<StudentData>mStudentDataCall = submitAssignment.sendAssignment(
                        firstName,LName,UserEmail,git
                );
                mStudentDataCall.enqueue(new Callback<StudentData>() {
                    @Override
                    public void onResponse(Call<StudentData> call, Response<StudentData> response) {
                        if (response.isSuccessful())
                        {
                            sentStatus();
                        }

                    }
                    @Override
                    public void onFailure(Call<StudentData> call, Throwable t) {
                        failStatus();
                    }
                });
            }else
                    Toast.makeText(Submit.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();


        }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Submit.this, LeaderBoard.class));
            }
        });

    }


    /**
     * TODO: CREATE THE DIALOG METHODS AND SEEK CONFIRMATION BEFORE SUBMITTING
     */
    private void sentStatus() {
        mDialog.setContentView(getLayoutInflater().inflate(R.layout.sent
                , null));
        mDialog.show();
    }
    private void failStatus()
    {
        mDialog.setContentView(getLayoutInflater().inflate(R.layout.failed
                ,null));
        mDialog.show();
    }

    protected void setFullScreen(){
        if(Build.VERSION.SDK_INT >Build.VERSION_CODES.LOLLIPOP)
        {
            View customScreen = getWindow().getDecorView();
            customScreen.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE|
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );

            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    private void confirmation()
    {
        mDialog.setTitle("Please confirm submission");
        mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(Submit.this, "Submission Canceled", Toast.LENGTH_SHORT).show();
            }
        });
    }


}