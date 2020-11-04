package com.onrpiv.uploadmedia;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.TextView;

public class Pos2_Activity extends FluidGlossary {
    private int headerTextSize = 25;
    private int paraTextSize = 16;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pos2);
        TextView t0 = (TextView)findViewById(R.id.pos2TextView0);
        t0.setText("Laminarand Turbulent Flow");
        t0.setTextSize(headerTextSize);
        TextView t1 = (TextView)findViewById(R.id.pos2TextView1);
        t1.setText("Laminar flow is characterized by fluid particles following smooth paths with little to no mixing. The photo below shows an example of laminar flow from:");
        t1.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t2 = (TextView)findViewById(R.id.pos2TextView2);
        t2.setText("Turbulent flow is characterized by chaotic fluctuations in pressure and/orvelocity. Turbulence is caused by excessive kinetic energy in the flow that overcomes the damping effect of the fluid’s viscosity.An example of turbulent flow is shown in the image below:");
        t2.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t3 = (TextView)findViewById(R.id.pos2TextView3);
        t3.setText("The Reynolds number is a common way to determine if a flow is turbulent or laminar where a high Reynolds number indicates turbulence, and a low Reynolds number indicates laminar flow. Flow which is not quite laminar or turbulent is commonly referred to as transitional.");
        t3.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t4 = (TextView) findViewById(R.id.pos2TextView4);
        t4.setText("\nConsiderations for mI-PIV:");
        t4.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView t5 = (TextView)findViewById(R.id.pos2TextView5);
        t5.setText("Some velocity profiles can indicate if the flow is turbulent or laminar. For example, the velocity profile in a pipe is much more parabolic in laminar flow than in turbulent flow, as shown in the following mI-PIV outputs converted to velocity profiles:");
        t5.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        TextView[] textViews = {t1,t2,t3,t4,t5};
        for(int i = 0; i<textViews.length; i++){
            textViews[i].setTextSize(paraTextSize);
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }
}
