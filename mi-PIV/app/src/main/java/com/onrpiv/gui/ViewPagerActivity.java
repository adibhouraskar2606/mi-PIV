package com.onrpiv.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        Intent intent = getIntent();
        String [] urls = intent.getStringArrayExtra("string-array-urls");

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager_adapter);
        ViewPagerAdapterTest adapter = new ViewPagerAdapterTest(this, urls);
        viewPager.setAdapter(adapter);
    }

}
