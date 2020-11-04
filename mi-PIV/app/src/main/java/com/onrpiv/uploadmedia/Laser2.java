package com.onrpiv.uploadmedia;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.widget.TextView;

public class Laser2 extends LaserSafetyDummy {
    private int headerTextSize = 25;
    private int paraTextSize = 16;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laser2);
        TextView t0 = (TextView)findViewById(R.id.laserSafetyTextView0);
        t0.setText("Lasers in PIV");
        t0.setTextSize(headerTextSize);
        TextView t2 = (TextView)findViewById(R.id.laserSafetyTextView2);
        String html2 = "Lasers are used in PIV to illuminate neutrally buoyant particles within a flow field.These particles are typically made of silica (glass) and are designed to scatter light so cameras can capture their position accurately. Check out the Learn About PIV page to learn more.";
        t2.setText(Html.fromHtml(html2));
        t2.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t3 = (TextView)findViewById(R.id.laserSafetyTextView3);
        t3.setText("To illuminate the particles, the laser passes through a cylindrical or half-circle lens (like a glass stir stick) that flattens the light into a plane.");
        t3.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t4 = (TextView)findViewById(R.id.laserSafetyTextView4);
        String html4 = "You can only capture data on particles within the laser light sheet. It is safe to observe the particles moving within the plane when the laser is a sheet. They should look something like this:";
        t4.setText(Html.fromHtml(html4));
        t4.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView[] textViews = {t2,t3,t4};
        for(int i = 0; i<textViews.length; i++){
            textViews[i].setTextSize(paraTextSize);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
}
