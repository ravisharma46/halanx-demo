package com.example.apple.halanx_test_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Listitem> listitems;
    private static final String URL_DATA="http://testapi.halanx.com/homes/houses/?furnish_type=full,semi&house_type=independent,villa,apartment&accomodation_allowed=girls,boys,family&accomodation_type=flat,shared,private&rent_min=1000&rent_max=20000&latitude=28.6554&longitude=77.1646&radius=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Delhi");



        listitems=new ArrayList<>();
        loadRecyclerViewData(URL_DATA);


    }


    public void loadRecyclerViewData(String s){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();



                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array= jsonObject.getJSONArray("results");

                            for(int i=0;i<array.length();i++){

                                  JSONObject obj= array.getJSONObject(i);

                            //----getting city and state from address object---
                                String city ="";
                                String state="";
                                JSONObject object2 = obj.getJSONObject("address");
                                 city = object2.getString("city");
                                 state=object2.getString("state");
                               //------------------------------------------------

                                Listitem item=new Listitem(
                                        obj.getString("name"),
                                        city,
                                        state,
                                        obj.getString("cover_pic_url"),
                                        obj.getString("rent_from"),
                                        obj.getString("security_deposit_from"),
                                        obj.getString("accomodation_allowed_str"),
                                        obj.getString("available_bed_count"),
                                        obj.getString("house_type"),
                                        obj.getString("furnish_type")


                                );

                                 listitems.add(item);

                            }

                            adapter= new MyAdapter(listitems,getApplicationContext());
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("EROR",error.toString());
                    }
                }
        );


        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_favorite:
                Intent i= new Intent(getApplicationContext(),filter_activity.class);
                listitems.clear();
                startActivityForResult(i,3);
                return true;


        }



        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {

            Gson gson = new Gson();
            String stringLocation = data.getStringExtra("LIST");

            Log.e("data", stringLocation);

            if (stringLocation != null) {

                Type type = new TypeToken<List<Listitem>>() {
                }.getType();
                List<Listitem> objectLocations = gson.fromJson(stringLocation, type);

                for(int i=0;i<objectLocations.size();i++){
                    listitems.add(objectLocations.get(i));
                }
                adapter= new MyAdapter(listitems,getApplicationContext());
                recyclerView.setAdapter(adapter);




                }
            }


        }


}
