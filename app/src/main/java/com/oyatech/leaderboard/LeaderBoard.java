package com.oyatech.leaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

public class LeaderBoard extends AppCompatActivity {
 TabLayout mTabLayout;
 ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
mTabLayout = findViewById(R.id.tbLayout);
mViewPager = findViewById(R.id.viewPage);

FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),2);
mViewPager.setAdapter(fragmentAdapter);
mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.submit,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.submit)
        {
            startActivity(new Intent(LeaderBoard.this,Submit.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
