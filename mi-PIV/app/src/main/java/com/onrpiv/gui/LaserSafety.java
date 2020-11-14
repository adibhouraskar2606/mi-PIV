package com.onrpiv.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class LaserSafety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laser_safety);
        TextView t1 = (TextView)findViewById(R.id.laserSafetyTextView1);
        String html1 = "<b>Laser Definition</b><br>Lasers differ from other types of light sources because they are coherent, meaning they have the same wavelength, phase, and frequency.A laser emits light using optical amplification from stimulated emission of electromagnetic radiation.";
        t1.setText(Html.fromHtml(html1));
        TextView t2 = (TextView)findViewById(R.id.laserSafetyTextView2);
        String html2 = "<b>Lasers in PIV</b><br>Lasers are used in PIV to illuminate neutrally buoyant particles within a flow field.These particles are typically made of silica (glass) and are designed to scatter light so cameras can capture their position accurately. Check out the Learn About PIV page to learn more.";
        t2.setText(Html.fromHtml(html2));
        TextView t3 = (TextView)findViewById(R.id.laserSafetyTextView3);
        t3.setText("To illuminate the particles, the laser passes through a cylindrical or half-circle lens (like a glass stir stick) that flattens the light into a plane.");
        TextView t4 = (TextView)findViewById(R.id.laserSafetyTextView4);
        String html4 = "You can only capture data on particles within the laser light sheet. It is safe to observe the particles moving within the plane when the laser is a sheet. They should look something like this: <br><br><b>Safe use of lasers in a classroom</b><br>When working with lasers, it’s critical to operate them safely so you don’t damage your equipment or harm yourself or a someone nearby. The Laser Institute of America has defined an AmericanNational Standard for Safe Use of Lasers that is summarized here to understand the risks of working with lasers. There are three main things to consider: ";
        t4.setText(Html.fromHtml(html4));
        TextView t5 = (TextView)findViewById(R.id.laserSafetyTextView5);
        t5.setText("A laser’s potential for causing harm, the first consideration, is the main focus of laser safety.Lasers are categorized into classes based on their power. Class 1 lasers do not pose any danger because the entire laser apparatus is contained within an enclosed system, like a laser printer. Class 2 or 3R lasers are typically found in things like laser pointers available online. We strongly recommend againstusing Class 3B or 4 lasers with mI-PIV. ");
        TextView t6 = (TextView)findViewById(R.id.laserSafetyTextView6);
        String html3 = "Even if you are using a low powered laser, do <b><u>not look</u></b> directly at the beam.";
        t6.setText(Html.fromHtml(html3));
        TextView t7 = (TextView)findViewById(R.id.laserSafetyTextView7);
        t7.setText("When setting up your PIV experiment, consider the environment and pay special attention to all possible beam paths. You should be aware of unintentional paths the beam could take if the mount is unstable, the laser or table is bumped, or the laser reflects off a surface. When using a Class 1, 2, or 3R laser, an accidental beam path is not critical since these are relatively safe lasers to work with and your blink reflex should protect your eyes in the event of accidentally looking at the beam.");
    }
}
