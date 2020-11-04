package com.onrpiv.uploadmedia;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LaserSafetyDummy extends LearnPIV {
    String [] laserList = {"Laser Definition", "Lasers in mi-PIV", "Safe use of lasers in Classroom"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.laser_safety_dummy);
        TextView t1 = (TextView)findViewById(R.id.laserSafetytextview1);
        t1.setText("Do NOT look at the laser beam!");

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, laserList);

        ListView listView = (ListView) findViewById(R.id.laserSafety_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 0){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Laser1.class);
                    startActivityForResult(myIntent,0);
                }
                if(position == 1){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Laser2.class);
                    startActivityForResult(myIntent,1);
                }
                if(position == 2){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Laser3.class);
                    startActivityForResult(myIntent,2);
                }
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
}
