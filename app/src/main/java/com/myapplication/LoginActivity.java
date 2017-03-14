package com.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static android.os.Build.VERSION_CODES.M;
import static com.myapplication.R.id.namereg;
import static com.myapplication.R.id.phonereg;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Intent uploadactivity;
Button login,register;
    EditText Email,Password;
networkhelper helplogin;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private View view;
    private Activity activity;
 private Context context;
    private final String TAG = this.getClass().getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.Login);
         Email = (EditText) findViewById(R.id.Email);
         Password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);

context =getApplicationContext();
activity=this;
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        helplogin = new networkhelper();








    }



    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        int id= v.getId();

        switch (id){

            case R.id.register:

                Intent registeractivity= new Intent(LoginActivity.this,register.class);
                startActivity(registeractivity);
                break;

            case R.id.Login:
               // String json = "{\"email\": \"" + Email.getText() + "\", \"password\":\"" + Password.getText() + "\"}";
               String json1 = "{\"abc\":\"aplha\"}";

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                JSONObject postParam = new JSONObject();

                try {

                    postParam.put("password", Password.getText().toString());
                    postParam.put("email", Email.getText().toString());

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


                JsonObjectRequest stringRequest = new JsonObjectRequest(com.android.volley.Request.Method.POST, "http://10.0.2.2:3001/login",postParam,


                        new com.android.volley.Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //  Toast.makeText(register.this,response,Toast.LENGTH_LONG).show();

                                try {
                                    VolleyLog.v("Response:%n %s", response.toString(4));
                                    Toast.makeText(LoginActivity.this,response.toString(),Toast.LENGTH_LONG).show();


                                        Intent i = new Intent(LoginActivity.this, Upload.class);
                                        startActivity(i);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // populateLessonDetails(myActiveLessonURLFiltered);
                            }
                        }, new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG,error.toString());
                    }
                })
                {


                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("User-agent", "My useragent");
                        return headers;
                    }



                };




                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                requestQueue.add(stringRequest);

        }
    }).start();






















                        }










                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
                            }
                        });*/



                }



        }






   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(LoginActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }


















  functionality.",Toast.LENGTH_LONG).show();



 CODE);



















}
      */
