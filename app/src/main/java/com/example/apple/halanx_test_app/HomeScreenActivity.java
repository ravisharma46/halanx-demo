package com.example.apple.halanx_test_app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        listitems=new ArrayList<>();
        loadRecyclerViewData();

    }


    private void loadRecyclerViewData(){
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
                                        obj.getString("available_bed_count")


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



}
