package com.oyatech.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.oyatech.leaderboard.SubmitAsignment.Submit;

public class LeaderBoard extends AppCompatActivity {
 TabLayout mTabLayout;
 ViewPager mViewPager;
 Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_leader_board);

mTabLayout = findViewById(R.id.tbLayout);
mViewPager = findViewById(R.id.viewPage);
submit = findViewById(R.id.btnSubmission);

FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),2);
//Binding the fragment instance to the ViewPager
mViewPager.setAdapter(fragmentAdapter);
//Binding the ViewPager to the Tablayout
mTabLayout.setupWithViewPager(mViewPager);

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(LeaderBoard.this, Submit.class));
    }
});

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
}
