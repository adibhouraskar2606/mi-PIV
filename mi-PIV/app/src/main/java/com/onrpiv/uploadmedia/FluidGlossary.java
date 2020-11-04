package com.onrpiv.uploadmedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FluidGlossary extends Activity {
    // Array of strings...
    String[] mobileArray = {"Boundary Layer","Laminar and Turbulent Flow","Reynolds Number","Vorticity/Circulation",
            "Fluid","Wake","Shear","Velocity profile","Streamline","Steady/Unsteady",
            "Bernoulli Equation","External/Internal Flow","Major/Minor losses","Common Assumptions","Viscocity","Gas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fluid_gloassary);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(position == 0){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos1_Activity.class);
                    startActivityForResult(myIntent,0);
                }
                if(position == 1){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos2_Activity.class);
                    startActivityForResult(myIntent,1);
                }
                if(position == 2){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos3_Activity.class);
                    startActivityForResult(myIntent,2);
                }
                if(position == 3){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos4_Activity.class);
                    startActivityForResult(myIntent,3);
                }
                if(position == 4){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos5_Activity.class);
                    startActivityForResult(myIntent,4);
                }
                if(position == 5){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos6_Activity.class);
                    startActivityForResult(myIntent,5);
                }
                if(position == 6){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos7_Activity.class);
                    startActivityForResult(myIntent,6);
                }
                if(position == 7){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos8_Activity.class);
                    startActivityForResult(myIntent,7);
                }
                if(position == 8){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos9_Activity.class);
                    startActivityForResult(myIntent,8);
                }
                if(position == 9){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos10_Activity.class);
                    startActivityForResult(myIntent,9);
                }
                if(position == 10){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos11_Activity.class);
                    startActivityForResult(myIntent,10);
                }
                if(position == 11){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos12_Activity.class);
                    startActivityForResult(myIntent,11);
                }
                if(position == 12){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos13_Activity.class);
                    startActivityForResult(myIntent,12);
                }
                if(position == 13){
                    Intent myIntent = new Intent(view.getContext(),com.onrpiv.uploadmedia.Pos14_Activity.class);
                    startActivityForResult(myIntent,13);
                }
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
    public BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_startExperiment:
                    Intent intent1 = new Intent(FluidGlossary.this, VideoActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.nav_Home:
                    System.out.println("Home it is");
                    Intent intent2 = new Intent(FluidGlossary.this, HomeActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.nav_feedback:
                    System.out.println("Feedback it is");
//                    Intent intent3 = new Intent(LearnFluids.this, HomeActivity.class);
//                    startActivity(intent2);
                    break;
            }
            return true;
        }
    };

}