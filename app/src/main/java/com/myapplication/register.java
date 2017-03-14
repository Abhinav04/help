package com.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.name;
import static android.accounts.AccountManager.KEY_PASSWORD;
import static android.os.Build.VERSION_CODES.M;


public class register extends AppCompatActivity implements View.OnClickListener {

    EditText Emailreg, Passwordreg, phonereg, namereg;

    networkhelper helper;
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button go = (Button) findViewById(R.id.go);
        Emailreg = (EditText) findViewById(R.id.regemail);
        Passwordreg = (EditText) findViewById(R.id.passwordreg);
        phonereg = (EditText) findViewById(R.id.phonereg);

        namereg = (EditText) findViewById(R.id.namereg);

        go.setOnClickListener(this);

        helper = new networkhelper();

    }

    @Override
    public void onClick(View v) {

        final JSONObject jsonBodyObj = new JSONObject();
     /*   try{
            jsonBodyObj.put("name", namereg.getText().toString());
            jsonBodyObj.put("password",Passwordreg.getText().toString());
            jsonBodyObj.put("email",Emailreg.getText().toString());
            jsonBodyObj.put("phone",phonereg.getText().toString());
        }catch (JSONException e){
            e.printStackTrace();
        }
           final String reqjson = jsonBodyObj.toString();

*/
                          // makeRequest("127.0.0.1:3001/signup",json) ;

        new Thread(new Runnable() {
            @Override
            public void run() {

                //Map<String, String> postParam = new HashMap<String, String>();
                JSONObject postParam = new JSONObject();
                try {
                    postParam.put("ename", namereg.getText().toString());
                    postParam.put("password", Passwordreg.getText().toString());
                    postParam.put("email", Emailreg.getText().toString());
                    postParam.put("phone", phonereg.getText().toString());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, "http://10.0.2.2:3001/signup",postParam,


                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //  Toast.makeText(register.this,response,Toast.LENGTH_LONG).show();

                                try {
                                    VolleyLog.v("Response:%n %s", response.toString(4));
                                    Toast.makeText(register.this,response.toString(),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                // populateLessonDetails(myActiveLessonURLFiltered);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this,error.toString(),Toast.LENGTH_LONG).show();
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




      /*  RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://10.0.2.2:3001/signup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(register.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(register.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.e(TAG,error.toString());
                    }
                }){

            final String name=namereg.getText().toString();
            final String email=Emailreg.getText().toString();
            final String password=Passwordreg.getText().toString();
            final String phone=phonereg.getText().toString();

        @Override
        protected Map<String, String> getParams () {

            Map<String, String> postParam = new HashMap<String, String>();
            postParam.put("ename", name);
            postParam.put("password", password);
            postParam.put("email", email);
            postParam.put("phone", phone);
            return postParam;

        }
    };




        requestQueue.add(stringRequest);*/



        Intent i = new Intent(register.this,LoginActivity.class);
        startActivity(i);

    }
}


















