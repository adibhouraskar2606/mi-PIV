package com.onrpiv.uploadmedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

public class LearnImaging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_imaging);
        TextView t1 = (TextView)findViewById(R.id.learnImagingTextView1);
        t1.setText("\nHow does a digital video camera work?\n");
        TextView t2 = (TextView)findViewById(R.id.learnImagingTextView2);
        String html1 = "\nFor PIV, the quality of images used determines the quality of the analysis results. To produce images, a digital camera needs a way to convert light, to the array of numbers described in “Learn about PIV”. This is done with a computer chip which collects and records light.These chips are separated into a finite number of sensors, whose output is decided by <b>bit depth</b>.(The number of <b>pixels</b>/sensors determines the <b>resolution</b> of the camera.) An example image of a camera sensor array is shown below.";
        t2.setText(Html.fromHtml(html1));
        //image
        TextView t3 = (TextView)findViewById(R.id.learnImagingTextView3);
        String html2 = "When light contacts the “chip”, each sensor records the amount of light that has contacted it, which is also referred to as the sensor being “exposed”. The amount of time the sensor collects light is determined by the <b>ShutterSpeed</b>(with units of time in seconds).<br><br>As shown in the figure below, there are also several ways to adjust the light contacting the sensors.These adjustments are <b>focus</b> and <b>aperture</b>. Similarly, the sensitivity of the sensors may be adjusted, and this is referred to as ISO.As a further reading of these term definitions will show, PIV imaging requires a trade-off of the good and bad aspects of allowing more and less light through by adjusting shutter speed, aperture, and ISO.";
        t3.setText(Html.fromHtml(html2));
        //image
        TextView t4 = (TextView)findViewById(R.id.learnImagingTextView4);
        String html3 = "To record a video, a camera repeats the imaging process described above multiple times. The images, referred to as “frames”, are recorded in finite time intervals. The rate at which these images are taken is referred to as <b>framerate</b> with units of frames per second(fps).<br><br><b>Bit Depth</b><br><br>Each sensor stores a number, which indicates the amount of light that contacted it while the sensor was exposed.Due to the limitations of computers, these numbers are limited by the amount of bits which describe each sensor reading, or the value of a pixel. To understand bit depth, we must first recognize that current computers may store only ones and zeros. The number of ones and zeros per sensor output (bits) determines bit depth. In other words, bit depth describes the number of values each pixel may take. If the image has bit depth of one, the image may take two values: 0 or 1. However, if an image has a bit depth of 2, four different values may be stored: 00, 01, 10, 11. Similarly, if an image has a bit depth of 3, eight different values may be stored: 000, 001, 010, 011, 100, 101, 110, and 111. This pattern continues where the number of values each pixel may take is described as:";
        t4.setText(Html.fromHtml(html3));
        //image
        TextView t5 = (TextView)findViewById(R.id.learnImagingTextView5);
        t5.setText("Since most laboratory PIV setups use black and white images as they allow more information about the amount of light to be recorded, we will focus on black and white images. A discussion of color images is outside the scope of this page.\n\n Here is a mI-PIV image with a bit-depth of 256 (8 bits):");
        //image
        TextView t14 = (TextView)findViewById(R.id.learnImagingTextView14);
        t14.setText("The same mI-PIV image with a bit-depth of 8 (3 bits):");
        //image
        TextView t15 = (TextView)findViewById(R.id.learnImagingTextView15);
        t15.setText("The same mI-PIV image with a bit-depth of 4 (2 bits):");
        //image
        TextView t16 = (TextView)findViewById(R.id.learnImagingTextView16);
        t16.setText("The same mI-PIV image with a bit-depth of 2 (1 bits):");
        //image
        TextView t6 = (TextView)findViewById(R.id.learnImagingTextView6);
        String html4 = "A greater bit-depth allows researchers to gather more information.This added information adds to the quality of correlations described in 'learn about PIV'.<br><br><b>Pixel</b><br><br>A pixel is a small square area of light which makes up an image. The amount of pixels in an image determines the image resolution. An example of how pixels make an image is shown below:";
        t6.setText(Html.fromHtml(html4));
        //image
        TextView t7 = (TextView)findViewById(R.id.learnImagingTextView7);
        String html5 = "To use pixels in PIV, we calibrate the image. Commonly, image calibration begins with a photo taken by the camera of something of a known length (e.g., a ruler). By identifying the number of pixels that is equivalent to the known length of the item, experimenters can determine a conversion between pixels and length (e.g. meters, inches). For example, consider a calibrated image that has 100 pixels per inch,if an area of fluid moves 10 pixels between frames, we find that the displacement is 10/100 inches or 0.1 inches.To convert framerate to time,and find velocity see the framerate term description. Note: the calibration is only valid for the camera and laser sheet at a set distance apart, and the calibration is only valid along the plane used to calibrate the image.) <br><br><b>ISO</b><br><br>ISO refers to each sensor’s sensitivity to light. By increasing the ISO, the sensitivity of the camera is increased, and each sensor gathers more light. However, the “noise,” or the amount of light which does not come from the PIV particles, also increases as ISO is increased.<br> An example of the influence ISO has on a PIV image is shown below, where the ISO increases moving down the page.<br>";
        t7.setText(Html.fromHtml(html5));
        TextView t35 = (TextView)findViewById(R.id.learnImagingTextView37);
        t35.setText("ISO:200");

        //image
        TextView t17 = (TextView)findViewById(R.id.learnImagingTextView17);
        t17.setText("ISO:400");
        //image
        TextView t18 = (TextView)findViewById(R.id.learnImagingTextView18);
        t18.setText("ISO:800");
        //image
        TextView t19 = (TextView)findViewById(R.id.learnImagingTextView19);
        t19.setText("ISO:1200");
        //image
        TextView t20 = (TextView)findViewById(R.id.learnImagingTextView20);
        t20.setText("ISO:1600");
        //image
        TextView t21 = (TextView)findViewById(R.id.learnImagingTextView21);
        t21.setText("ISO:2400");
        //image
        TextView t8 = (TextView)findViewById(R.id.learnImagingTextView8);
        String html6 = "Based on the increase in noise paired with an increase in ISO, the ISO should be adjusted low enough to avoid bad correlations due to noise, and high enough to achieve good correlations from adequately illuminated particles.<br><br><br><b>Shutter Speed</b><br><br>To allow light to reach the sensor, the shutter has to open and then close(or in the case of electronic shutter, the sensors must be turned on and then off). The amount of time the shutter spends open is referred to as shutter speed and is measured by the amount of time open(on)in seconds.<br><br>Shutter speed has several important implications in PIV.One important consideration is particle image streaking or simply <b>streaking</b>. The amount of distance a particle moves while the shutter is open is recorded by the camera.As PIV measures movement between frames, particles which are “streaked” make the movement between frames harder to correlate.By decreasing the shutter speed,streaking is reduced. However, the amount of light which hits each sensor is decreased, as the time each sensor is exposed is decreased. The following images have a decreasing shutter speed. To keep the particles visible,the ISO has been adjusted across these images.<br>";
        t8.setText(Html.fromHtml(html6));

        TextView t36 = (TextView)findViewById(R.id.learnImagingTextView38);
        t36.setText("Shutter Speed: 1/30s, ISO: 800");
        //image
        TextView t22 = (TextView)findViewById(R.id.learnImagingTextView22);
        t22.setText("Shutter Speed: 1/50s, ISO: 1200");
        //image
        TextView t23 = (TextView)findViewById(R.id.learnImagingTextView23);
        t23.setText("Shutter Speed: 1/80s, ISO: 1200");
        //image
        TextView t24 = (TextView)findViewById(R.id.learnImagingTextView24);
        t24.setText("Shutter Speed: 1/125s, ISO: 1600");
        //image
        TextView t25 = (TextView)findViewById(R.id.learnImagingTextView25);
        t25.setText("Shutter Speed: 1/200s, ISO: 2400");
        //image
        TextView t26 = (TextView)findViewById(R.id.learnImagingTextView26);
        t26.setText("This following set of images is of a region of PIV particles which are not moving, to show how shutter speed affects the amount of light which reaches the sensors.\n");

        TextView t37 = (TextView)findViewById(R.id.learnImagingTextView39);
        t37.setText("Shutter Speed: 1/30s");
        //image
        TextView t27 = (TextView)findViewById(R.id.learnImagingTextView27);
        t27.setText("Shutter Speed: 1/50s");
        //image
        TextView t28 = (TextView)findViewById(R.id.learnImagingTextView28);
        t28.setText("Shutter Speed: 1/80s");
        //image
        TextView t29 = (TextView)findViewById(R.id.learnImagingTextView29);
        t29.setText("Shutter Speed: 1/125s");
        //image
        TextView t30 = (TextView)findViewById(R.id.learnImagingTextView30);
        t30.setText("Shutter Speed: 1/200s");
        //image
        TextView t31 = (TextView)findViewById(R.id.learnImagingTextView31);
        t31.setText("Shutter Speed: 1/320s");
        //image
        TextView t9 = (TextView)findViewById(R.id.learnImagingTextView9);
        String html7 = "For these reasons, the shutter speed should be reduced as much as necessary to eliminate streaking, while still allowing the particles to be seen. The light is also impacted by the aperture and ISO. Another important consideration is that as the framerate decreases,the maximum shutter speed decreases, as the shutter must open and shut for each frame to be recorded.<br><br><br><b>Resolution</b><br><br>The resolution of an image is a measurement of how many pixels make up a single image. Resolution is described by the number of pixels in each direction (x and y). For example, if an image has 720 pixels along the short axis, and 1280 pixels along the long axis, the image is 1280x720. (Short reference to resolution often picks the lowest number, so for 720x1280, the image would be 720p. 1080 video is 1920x1080.)<br><br>To see how resolution impacts an image, the following example shows a square image in increasing resolution.<br><br>This piece of a mI-PIV image was 1080x1080.";
        t9.setText(Html.fromHtml(html7));
        //image
        TextView t32 = (TextView)findViewById(R.id.learnImagingTextView32);
        t32.setText("This is the same image with a resolution of540x540");
        //image
        TextView t33 = (TextView)findViewById(R.id.learnImagingTextView33);
        t33.setText("This is the same image with a resolution of 270x270");
        //image
        TextView t34 = (TextView)findViewById(R.id.learnImagingTextView34);
        t34.setText("This is the same image with a resolution of 120x120");
        //image
        TextView t10 = (TextView)findViewById(R.id.learnImagingTextView10);
        String html8 = "An interesting point to note is that the error in mI-PIV measurements is highly dependent on the resolution, as particles (and resultantly velocities) may only be “resolved” down to pixel size.<br><br><br><b>Focus</b><br><br>The focus of a camera determines how ”sharp” an image is. Our eyes see this as blurred lines, and similarly, when the light is not focused on the image, as shown in the photos below, the particles are blurred. By placing the sensor, object of interest, and the lens at the appropriate distances, the image is brought into focus, and the light converges on the sensor as shown from android authority:";
        t10.setText(Html.fromHtml(html8));
        //image
        TextView t11 = (TextView)findViewById(R.id.learnImagingTextView11);
        String html9 = "When the lens is too close to the sensor or too far, the image appears blurry. The correct focus may be visually identified through adjustment until the particles have crisp, clear edges as shown:<br><br>An out of focus image, where the particles are not easily distinguished from each other.";
        t11.setText(Html.fromHtml(html9));
        //image
        //image
        //image
        TextView t12 = (TextView)findViewById(R.id.learnImagingTextView12);
        String html10 = "<br><b>Frame Rate</b><br><br>Frame rate is the number of images (“frames”,)taken per second in a video. It is important to note, that in a single camera, increasing the framerate (less time between images) means a shorter maximum shutter speed, as the shutter must open and shut for each frame to be recorded. To apply the framerate, the time between images is simply 1/framerate. For example, if there are 30 frames per second, then a frame is taken every 1/30 seconds or .033 seconds.See <b>Pixel</b> for using this framerate to identify displacements.With the displacement, the velocity may be found as the displacement divided by time.<br><br><br><b>Aperture</b><br><br>The aperture is a variably sized opening the light passes through before reaching the sensors. The aperture allows more and less light to reach the sensors and is another way to adjust the brightness of the final image as shown below.";
        t12.setText(Html.fromHtml(html10));
        //image
        TextView t13 = (TextView) findViewById(R.id.learnImagingTextView13);
        t13.setText("However, the larger the aperture, the more narrow the ”depth of field” or the distance particles remain in focus. The larger the depth of field, the longer the band of an image that remains in focus. Aperture is measured by “fstop” where the larger the aperture, the smaller the fstop value.");
        //image
    }
}
