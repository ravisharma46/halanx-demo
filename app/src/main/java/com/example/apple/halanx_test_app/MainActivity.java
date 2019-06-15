package com.example.apple.halanx_test_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private EditText user_id,user_password;
    private Button signIn;

    private static final String myUrl="http://testapi.halanx.com/rest-auth/login/?username=test&password=test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id= (EditText) findViewById(R.id.userName);
        user_password =(EditText) findViewById(R.id.passWord);
        signIn =(Button) findViewById(R.id.sign_in_bt);



          signIn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
          userLogin();

              }
          });




    }

    public void userLogin() {
        //first getting the values
        final String username = user_id.getText().toString();
        final String password = user_password.getText().toString();


       //----- validating inputs-----
        if (username.isEmpty()) {
            user_id.setError("Please enter your username");
            user_id.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            user_password.setError("Please enter your password");
            user_password.requestFocus();
            return;
        }

        //----if everything is fine-----
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), "Succesfully login", Toast.LENGTH_SHORT).show();

                            Intent i= new Intent(MainActivity.this,HomeScreenActivity.class);
                            startActivity(i);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Eror in login", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);

                Log.e("TAG",params.toString());
                return params;
            }
        };

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }






}

