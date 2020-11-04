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

public class TryExperiments extends FluidGlossary {
    String[] experimentList = {"Bubble Curtain","Deep Sea Vent","Flow Over Propeller Hulls","Fully Developed Pipe Flow",
            "Laminar Transition Pipe Flow","Pipe Flow Vortex Generator","Seed Particle Size Effects"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.try_experiments);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, experimentList);

        ListView listView = (ListView) findViewById(R.id.experimentList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 5){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Experiment1.class);
                    startActivityForResult(myIntent,0);
                }
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

    }
}
