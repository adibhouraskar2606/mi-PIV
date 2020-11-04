package com.onrpiv.uploadmedia;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.onrpiv.uploadmedia.networking.ApiConfig;
import com.onrpiv.uploadmedia.networking.ApiConfigFileNames;
import com.onrpiv.uploadmedia.networking.ApiConfigVectorFile;
import com.onrpiv.uploadmedia.networking.AppConfigFileNames;
import com.onrpiv.uploadmedia.networking.ApiConfigProcess;
import com.onrpiv.uploadmedia.networking.AppConfig;
import com.onrpiv.uploadmedia.networking.AppConfigProcess;

import com.onrpiv.uploadmedia.networking.AppConfigVector;
import com.onrpiv.uploadmedia.networking.ServerResponse;
import com.onrpiv.uploadmedia.networking.ServerResponseProcess;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

import android.telephony.TelephonyManager;
import android.content.Context;

import static android.support.constraint.Constraints.TAG;
import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * Created by sarbajit mukherjee on 10/29/2019.
 */

public class ImageActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button pickImage, upload, display, save, pickImageMultiple, review, process;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private static final int PICK_IMAGE_MULTIPLE = 1;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;

    private static final String TAG = ImageActivity.class.getSimpleName();

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;
    private String imagePath;
    private ArrayList<String> imagePathList = new ArrayList<>();
    private ArrayList<String> postPathMultiple = new ArrayList<>();
    private String mediaPath;

    private String serverMessage;

    private Button btnCapturePicture;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    ProgressDialog pDialog;
    private String postPath;
    private String IMEINumber;
    String SCAN_PATH;
    File[] allFiles ;
    // Below edittext and button are all exist in the popup dialog view.
    private View popupInputDialogView = null;
    // Get Sets.
    private TextView setEditText = null;
    // Get Set Number.
    private EditText setNumberEditText = null;
    // Get Image1.
    private EditText img1EditText = null;
    // Get Image2.
    private EditText img2EditText = null;
    // Click this button in popup dialog to save user input data
    private Button saveUserDataButton = null;
    // Click this button to cancel edit user data.
    private Button cancelUserDataButton = null;
    private String userName;
    private File storageDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_layout);

        imageView = (ImageView) findViewById(R.id.preview);
        pickImageMultiple = (Button) findViewById(R.id.pickImageMultiple);
        upload = (Button) findViewById(R.id.upload);
        display = (Button) findViewById(R.id.display);
        save = (Button) findViewById(R.id.save);
        review = (Button) findViewById(R.id.Review);
        process = (Button) findViewById(R.id.process);

        // Get the transferred data from source activity.
        Intent userNameIntent = getIntent();
        userName = userNameIntent.getStringExtra("UserName");

        storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/PIV_Frames_" + userName);
        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()){
            Toast.makeText(this, "You have not generated any frames yet", Toast.LENGTH_SHORT).show();
        } else {
            pickImageMultiple.setOnClickListener(this);
            upload.setOnClickListener(this);
            loadIMEI();
            initDialog();
        }
    }

    /**
     * Called when the 'loadIMEI' function is triggered.
     */
    public void loadIMEI() {
        // Check if the READ_PHONE_STATE permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // READ_PHONE_STATE permission has not been granted.
            requestReadPhoneStatePermission();
        } else {
            // READ_PHONE_STATE permission is already been granted.
            doPermissionGrantedStuffs();
        }
    }


    @Override
    public void onClick(final View v) {
        display.setEnabled(false);
        save.setEnabled(false);
        switch (v.getId()) {
            case R.id.pickImageMultiple:
                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ImageActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("User Data Collection Dialog.");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);
                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/PIV_Frames_" + userName);
                allFiles = folder.listFiles();
                int lenAllFiles = allFiles.length;
                int set = lenAllFiles/20;
                setEditText.setText("User '"+userName + "' has "+set+" image sets. The highest number set corresponds to the lastest generated frames");
                setEditText.setTextSize(20);
                if (set == 1) {
                    setNumberEditText.setHint("Number 1");
                } else {
                    setNumberEditText.setHint("Number 1 - "+set);
                }

                // When user click the save user data button in the popup dialog.
                saveUserDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        imagePathList.clear();
                        postPathMultiple.clear();
                        // Get user data from popup dialog editeext.
                        String setNumber = setNumberEditText.getText().toString();
                        String img1 = img1EditText.getText().toString();
                        String img2 = img2EditText.getText().toString();

                        // Create data for the listview.
                        String[] titleArr = { "SetNumber", "Image1", "Image2"};
                        String[] dataArr = {setNumber, img1, img2};

                        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;

                        int titleLen = titleArr.length;
                        for(int i =0; i < titleLen; i++) {
                            Map<String,Object> listItemMap = new HashMap<String,Object>();
                            listItemMap.put("title", titleArr[i]);
                            listItemMap.put("data", dataArr[i]);
                            itemDataList.add(listItemMap);
                        }
                        int image1 = Integer.parseInt(String.valueOf(itemDataList.get(1).get("data")));
                        int image2 = Integer.parseInt(String.valueOf(itemDataList.get(2).get("data")));


                        int allFileImage1 = image1 + (Integer.parseInt(setNumber) - 1)*20 - 1;
                        int allFileImage2 = image2 + (Integer.parseInt(setNumber) - 1)*20 - 1;

                        imagePathList.add(String.valueOf(allFiles[allFileImage1]));
                        imagePathList.add(String.valueOf(allFiles[allFileImage2]));
                        postPathMultiple = imagePathList;
                        postPath = "multiple image";
                        pickImageMultiple.setBackgroundColor(Color.parseColor("#00CC00"));
                        alertDialog.cancel();
                        Toast.makeText(ImageActivity.this, postPathMultiple.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                cancelUserDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
                break;
            case R.id.upload:
                uploadFile();
                break;
        }
    }

    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void initPopupViewControls()
    {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(ImageActivity.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        setEditText = (TextView) popupInputDialogView.findViewById(R.id.textView);
        setNumberEditText = (EditText) popupInputDialogView.findViewById(R.id.imgSet);
        img1EditText = (EditText) popupInputDialogView.findViewById(R.id.img1);
        img2EditText = (EditText) popupInputDialogView.findViewById(R.id.img2);
        saveUserDataButton = popupInputDialogView.findViewById(R.id.button_save_user_data);
        cancelUserDataButton = popupInputDialogView.findViewById(R.id.button_cancel_user_data);
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();


                    postPath = mediaPath;
                }

            } else if (requestCode == CAMERA_PIC_REQUEST) {
                if (Build.VERSION.SDK_INT > 21) {

                    Glide.with(this).load(mImageFileLocation).into(imageView);
                    postPath = mImageFileLocation;

                } else {
                    Glide.with(this).load(fileUri).into(imageView);
                    postPath = fileUri.getPath();

                }

            }
        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(false);
    }


    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }


    /**
     * Launching camera app to capture image
     */
    private void captureImage() {
//        Toast.makeText(this, "in capture", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            int j = 0;
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            // We give some instruction to the intent to save the image
            File photoFile = null;
//            Toast.makeText(this, "in 21", Toast.LENGTH_SHORT).show();
            try {
                // If the createImageFile will be successful, the photo file will have the address of the file
                photoFile = createImageFile();
                j =0;
                // Here we call the function that will try to catch the exception made by the throw function
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            // Here we add an extra file to the intent to put the address on to. For this purpose we use the FileProvider, declared in the AndroidManifest.
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

            // The following is a new line with a trying attempt
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");

            // The following strings calls the camera app and wait for his file in return.
            while(j < 2){
                startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
                j = j+1;
            }

        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

            // start the image capture Intent
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        // Here we specify the environment location and the exact path where we want to save the so-created file
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");

        // Then we create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();

        // Here we create the file using a prefix, a suffix and a directory
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        // The file is returned to the previous intent across the camera application
        return image;
    }


    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }


    /**
     * Receiving activity result method will be called after closing the camera
     * */

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    public void processFile(View view) {
        Map<String, RequestBody> map = new HashMap<>();
        String text = "process_"+IMEINumber;

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), text);
        map.put("command\"; command=\"", requestBody);
        ApiConfigProcess getResponse = AppConfigProcess.getRetrofit().create(ApiConfigProcess.class);
        Call<ServerResponseProcess> call = getResponse.upload("token", map);
        showpDialog();
        call.enqueue(new Callback<ServerResponseProcess>() {
            @Override
            public void onResponse(Call<ServerResponseProcess> call, Response<ServerResponseProcess> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        hidepDialog();
                        ServerResponseProcess serverResponse = response.body();
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_LONG).show();
                        display.setEnabled(true);
                    }
                } else {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "problem sending process to request", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponseProcess> call, Throwable t) {
                hidepDialog();
                Log.v("Response gotten is", t.getMessage());
            }
        });
        process.setBackgroundColor(Color.parseColor("#00CC00"));
    }


    public void reviewFile(View view) {
        reviewImageFromUrl(postPathMultiple);
        review.setBackgroundColor(Color.parseColor("#00CC00"));
    }


    private void reviewImageFromUrl(ArrayList<String> images) {
        String[] urls = new String[2];
        for (int i = 0; i < images.size(); i++) {
            urls[i] = images.get(i);
        }
        Intent intent = new Intent(this, ViewPagerActivity.class).putExtra("string-array-urls", urls);
        startActivity(intent);
    }


    public void displayFile(View view) {
        Toast.makeText(this, "display button pressed", Toast.LENGTH_SHORT).show();
        String url = "http://144.39.217.90/cgi-enabled/generatedImages_new/"+IMEINumber+"/tempImage.png";
        loadImageFromUrl(url);
        save.setEnabled(true);
        pickImageMultiple.setBackgroundResource(android.R.drawable.btn_default);
        upload.setBackgroundResource(android.R.drawable.btn_default);
        review.setBackgroundResource(android.R.drawable.btn_default);
        process.setBackgroundResource(android.R.drawable.btn_default);
    }

    private void loadImageFromUrl(String url) {
        Picasso.with(this)
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher) //if error
                .into(imageView, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    public void saveFile(View view) {
        saveVectorFile();
        Toast.makeText(this, "save button pressed", Toast.LENGTH_SHORT).show();
        String url = "http://144.39.217.90/cgi-enabled/generatedImages_new/"+IMEINumber+"/tempImage.png";
        saveImageOnPhone(url, userName);
    }

    private void saveVectorFile() {
        ArrayList<String> fileNames = new ArrayList<>();
        for (String s: postPathMultiple){
            File file = new File(s);
            fileNames.add(IMEINumber+"_"+file.getName());
        }
        String finalFileName = fileNames.get(0)+'-'+fileNames.get(1)+'_'+IMEINumber+"_fft.txt";
        String url = "generatedVectors_new/"+IMEINumber+"/"+finalFileName;
        downloadFile(url);
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
    }


    public void downloadFile(String url){
        ApiConfigVectorFile getResponse = AppConfigVector.getRetrofit().create(ApiConfigVectorFile.class);
        Call<ResponseBody> call = getResponse.downloadFileWithDynamicUrl(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "server contacted and has file");

                    Toast.makeText(getApplicationContext(), "Download in progress...", Toast.LENGTH_SHORT).show();
                    DownloadFileTask downloadFileTask = new DownloadFileTask();
                    downloadFileTask.execute(response.body());
                    Toast.makeText(getApplicationContext(), "Download Done...", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong!!! >> Error : "+response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Connection Failure!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class DownloadFileTask extends AsyncTask<ResponseBody, Pair<Integer, Long>, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(ResponseBody... urls) {
            saveToDisk(urls[0]);
            return null;
        }


        private void saveToDisk(ResponseBody body) {
            try {

                ArrayList<String> fileNames = new ArrayList<>();
                for (String s: postPathMultiple){
                    File file = new File(s);
                    fileNames.add(IMEINumber+"_"+file.getName());
                }
                String finalFileName = fileNames.get(0)+'-'+fileNames.get(1)+'_'+IMEINumber+"_fft.txt";
                File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Save_Output_" + userName);

                File destinationFile = new File(storageDirectory, finalFileName);

                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(destinationFile);
                    byte data[] = new byte[4096];
                    int count;
                    int progress = 0;
                    long fileSize = body.contentLength();
                    Log.d(TAG, "File Size=" + fileSize);
                    while ((count = inputStream.read(data)) != -1) {
                        outputStream.write(data, 0, count);
                        progress += count;
                        Pair<Integer, Long> pairs = new Pair<>(progress, fileSize);
                        Log.d(TAG, "Progress: " + progress + "/" + fileSize + " >>>> " + (float) progress / fileSize);
                    }

                    outputStream.flush();

                    Log.d(TAG, destinationFile.getParent());
                    Pair<Integer, Long> pairs = new Pair<>(100, 100L);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    Pair<Integer, Long> pairs = new Pair<>(-1, Long.valueOf(-1));
                    Log.d(TAG, "Failed to save the file!");
                    return;
                } finally {
                    if (inputStream != null) inputStream.close();
                    if (outputStream != null) outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Failed to save the file!");
                return;
            }
        }
    }

    private void saveImageOnPhone(String url, String userName) {
        Picasso.with(this)
                .load(url)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(getTarget(userName));
    }

    //target to save
    private static Target getTarget(final String userName){
        Target target = new Target(){
        String saveTimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSS").format(new Date());
        String imageFileSave = "IMAGE_" + saveTimeStamp;

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // image is the bitmap
                        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Save_Output_" + userName);

                        // Then we create the storage directory if does not exists
                        if (!storageDirectory.exists()) storageDirectory.mkdir();

                        File file = new File(storageDirectory, imageFileSave + ".png");

                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                            ostream.flush();
                            ostream.close();
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    // Uploading Image/Video
    private void uploadFile() {
        if (postPath == null || postPath.equals("")) {
            Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show();
            return;
        } else if (postPathMultiple.size() != 0){
            showpDialog();
            for (String s: postPathMultiple) {
                if(s!=null){
                    File file = new File(s);
                    // Map is used to multipart the file using okhttp3.RequestBody
                    Map<String, RequestBody> map = new HashMap<>();
                    // Parsing any Media type file
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    map.put("filename\"; filename=\"" +IMEINumber+"_"+file.getName(), requestBody);
                    ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);

                    Call<ServerResponse> call = getResponse.upload("token", map);
                    call.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    hidepDialog();
                                    ServerResponse serverResponse = response.body();
                                    Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                hidepDialog();
                                Toast.makeText(getApplicationContext(), "problem uploading image", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {
                            hidepDialog();
                            Log.v("Response gotten is", t.getMessage());
                        }
                    });
                }
            }
//            pickImageMultiple.setBackgroundResource(android.R.drawable.btn_default);
            upload.setBackgroundColor(Color.parseColor("#00CC00"));
            postPath = mediaPath;
        }
    }

    /**
     * Requests the READ_PHONE_STATE permission.
     * If the permission has been denied previously, a dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestReadPhoneStatePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(ImageActivity.this)
                    .setTitle("Permission Request")
                    .setMessage(getString(R.string.permission_read_phone_state_rationale))
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(ImageActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })
                    .show();
        } else {
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            // Received permission result for READ_PHONE_STATE permission.est.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                //alertAlert(getString(R.string.permision_available_read_phone_state));
                doPermissionGrantedStuffs();
            } else {
                alertAlert(getString(R.string.permissions_not_granted_read_phone_state));
            }
        }
    }

    private void alertAlert(String msg) {
        new AlertDialog.Builder(ImageActivity.this)
                .setTitle("Permission Request")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do somthing here
                    }
                })
                .show();
    }

    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this task i only need the IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            IMEINumber = tm != null ? tm.getImei() : null;
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            IMEINumber = tm != null ? tm.getDeviceId() : null;
        }

        /************************************************
         * **********************************************
         * This is just an icing on the cake
         * the following are other children of TELEPHONY_SERVICE
         *
         //Get SIM Serial Number
         String SIMSerialNumber=tm.getSimSerialNumber();

         //Get Network Country ISO Code
         String networkCountryISO=tm.getNetworkCountryIso();

         //Get SIM Country ISO Code
         String SIMCountryISO=tm.getSimCountryIso();

         //Get the device software version
         String softwareVersion=tm.getDeviceSoftwareVersion()

         //Get the Voice mail number
         String voiceMailNumber=tm.getVoiceMailNumber();


         //Get the Phone Type CDMA/GSM/NONE
         int phoneType=tm.getPhoneType();

         switch (phoneType)
         {
         case (TelephonyManager.PHONE_TYPE_CDMA):
         // your code
         break;
         case (TelephonyManager.PHONE_TYPE_GSM)
         // your code
         break;
         case (TelephonyManager.PHONE_TYPE_NONE):
         // your code
         break;
         }

         //Find whether the Phone is in Roaming, returns true if in roaming
         boolean isRoaming=tm.isNetworkRoaming();
         if(isRoaming)
         phoneDetails+="\nIs In Roaming : "+"YES";
         else
         phoneDetails+="\nIs In Roaming : "+"NO";


         //Get the SIM state
         int SIMState=tm.getSimState();
         switch(SIMState)
         {
         case TelephonyManager.SIM_STATE_ABSENT :
         // your code
         break;
         case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_PIN_REQUIRED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_PUK_REQUIRED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_READY :
         // your code
         break;
         case TelephonyManager.SIM_STATE_UNKNOWN :
         // your code
         break;

         }
         */
    }
}


