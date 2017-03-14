package com.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;
import org.apache.http.HttpEntity;

import org.apache.http.*;

import org.apache.http.HttpClientConnection;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import static android.R.attr.thumbnail;
import static android.os.Build.VERSION_CODES.M;


public class Upload extends Activity {

    Bitmap bm;
    ImageView Camera, Gallery, Upload, Image;

    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    String photoPath;
    String sResponse = null;
    URL url;


    final int CAMERA_REQUEST = 13323;
    final int GALLERY_REQUEST = 25320;
     Bitmap bitmap,thumbnail;
    File pic1;

    private final String TAG = this.getClass().getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        Camera = (ImageView) findViewById(R.id.Camera);
        Gallery = (ImageView) findViewById(R.id.Gallery);
        Upload = (ImageView) findViewById(R.id.uploadimage);
        Image = (ImageView) findViewById(R.id.image);


        Upload.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
               /* Intent i= new Intent(Upload.this,record.class);
                startActivity(i);
*/



                                          HashMap<String, String> params = new HashMap<String, String>();

                                         // params.put("name", "Image");
                                          params.put("image", "nsm");







                                          MultipartRequest mr = new MultipartRequest("http://10.0.2.2:3001/upload", new Response.Listener<String>(){

                                              @Override
                                              public void onResponse(String response) {
                                                  Log.d("response", response);
                                              }

                                          }, new Response.ErrorListener(){

                                              @Override
                                              public void onErrorResponse(VolleyError error) {
                                                  Log.e("Volley Request Error", error.toString());
                                              }

                                          }, pic1, params);

                                          Volley.newRequestQueue(getApplicationContext()).add(mr);





                                      }




    });


        Camera.setOnClickListener(new View.OnClickListener()

                                  {
                                      @Override
                                      public void onClick(View v) {

                                        Intent i=new Intent(Upload.this,MapsActivity.class);
                                          startActivity(i);
                                      }
                                  }

        );

        Gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);
            }
        });
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                photoPath = cameraPhoto.getPhotoPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(256, 256).getBitmap();
                    Image.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Something Wrong while loading photos", Toast.LENGTH_SHORT).show();
                }

            }

            else if (requestCode == GALLERY_REQUEST) {

                Uri filePath = data.getData();
                try {
                    //Getting the Bitmap from Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    //Setting the Bitmap to ImageView
                    Image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               // thumbnail = (Bitmap) data.getExtras().get("data");



                try {
                    File root = Environment.getExternalStorageDirectory();
                    if (root.canWrite()){
                        pic1 = new File(root, "pic.png");
                        FileOutputStream out = new FileOutputStream(pic1);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    Log.e("BROKEN", "Could not write file " + e.getMessage());
                }

            }
            }








        }












}







