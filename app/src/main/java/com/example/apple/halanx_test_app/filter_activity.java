package com.example.apple.halanx_test_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class filter_activity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView seek_result,date_pick,date_set,boy,girl,family,sh_room,priv_room,ent_room,full_furnsh,semi_furnsh,un_furnsh,apart,inde,villa;
    private EditText min_R,max_R;
    private DatePickerDialog datePickerDialog;
    private Button reset,apply;
    private ArrayList<String>  arr1 =new ArrayList<>();
    private ArrayList<String>  arr2 =new ArrayList<>();
    private ArrayList<String>  arr3 =new ArrayList<>();
    private ArrayList<String>  arr4 =new ArrayList<>();
    private  String URL_DATA_filter="http://testapi.halanx.com/homes/houses/?";
    private List<Listitem> listitems1;
    private  Listitem item;
    private String furnish_type="";
    private String house_type="";
    private String accomodation_allowed="";
    private String accomodation_type="";
    private String rent_min="";
    private String rent_max="";
    private String radius="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_activity);

        seekBar= (SeekBar) findViewById(R.id.seek_bar);

        min_R=(EditText)findViewById(R.id.min);
        max_R=(EditText) findViewById(R.id.max);

        seek_result=(TextView) findViewById(R.id.seek_res);
        date_pick=(TextView) findViewById(R.id.date_pick);
        date_set=(TextView) findViewById(R.id.date_set);
        boy=(TextView) findViewById(R.id.boy);
        girl=(TextView) findViewById(R.id.girl);
        family=(TextView) findViewById(R.id.family);
        sh_room=(TextView) findViewById(R.id.shrd_room);
        priv_room=(TextView) findViewById(R.id.pvt_room);
        ent_room=(TextView)  findViewById(R.id.entire_room);
        full_furnsh=(TextView)  findViewById(R.id.fully_furnsh);
        semi_furnsh=(TextView)  findViewById(R.id.semi_furnsh);
        un_furnsh=(TextView)  findViewById(R.id.un_furnsh);
        apart=(TextView)  findViewById(R.id.aprt);
        inde=(TextView)  findViewById(R.id.indep);
        villa=(TextView)  findViewById(R.id.villa);

        reset=(Button) findViewById(R.id.reset_bt);
        apply=(Button) findViewById(R.id.apply_bt);

        listitems1=new ArrayList<>();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filter");

        radius="10";
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChangedValue = i;
                seek_result.setText(Integer.toString(progressChangedValue+1)+"KM");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                radius=(Integer.toString(progressChangedValue+1));
            }
        });

        rent_min= min_R.getText().toString();
        min_R.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rent_min=charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 rent_min=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rent_max= max_R.getText().toString();
        max_R.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rent_max= charSequence.toString();
               // Toast.makeText(getApplicationContext(),rent_max,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        date_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c =  Calendar.getInstance();
                int mYear=c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay =c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog =new DatePickerDialog(filter_activity.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date_set.setText(dayOfMonth + "/"
                                + (monthOfYear + 1) + "/" + year);
                       // date_1=date.getText().toString();
                        // attribute_arr.add(date_1);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

       reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date_set.setText("");
               min_R.setText("5000");
               max_R.setText("50000");
               boy.setTextColor(Color.parseColor("#000000"));
               girl.setTextColor(Color.parseColor("#000000"));
               family.setTextColor(Color.parseColor("#000000"));
               sh_room.setTextColor(Color.parseColor("#000000"));
               priv_room.setTextColor(Color.parseColor("#000000"));
               ent_room.setTextColor(Color.parseColor("#000000"));
               full_furnsh.setTextColor(Color.parseColor("#000000"));
               semi_furnsh.setTextColor(Color.parseColor("#000000"));
               un_furnsh.setTextColor(Color.parseColor("#000000"));
               apart.setTextColor(Color.parseColor("#000000"));
               inde.setTextColor(Color.parseColor("#000000"));
               villa.setTextColor(Color.parseColor("#000000"));
               seekBar.setProgress(9);




           }
       });






        boy.setOnClickListener(new View.OnClickListener() {
           int check = 1;
           @Override
           public void onClick(View view) {

               if(check == 1){
                   boy.setTextColor(Color.parseColor("#D6252B"));
                    arr1.add("boy");
                   check = 0;
               }else{
                   boy.setTextColor(Color.parseColor("#000000"));
                   arr1.remove("boy");
                   check = 1;
               }

           }
       });
        girl.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    girl.setTextColor(Color.parseColor("#D6252B"));
                   arr1.add("girl");
                    check = 0;
                }else{
                    girl.setTextColor(Color.parseColor("#000000"));
                  arr1.remove("girl");
                    check = 1;
                }
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    family.setTextColor(Color.parseColor("#D6252B"));
                    arr1.add("family");
                    check = 0;
                }else{
                    family.setTextColor(Color.parseColor("#000000"));
                    arr1.remove("family");
                    check = 1;
                }

            }
        });
        sh_room.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    sh_room.setTextColor(Color.parseColor("#D6252B"));
                     arr2.add("shared");
                    check = 0;
                }else{
                    sh_room.setTextColor(Color.parseColor("#000000"));
                    arr2.remove("shared");
                    check = 1;
                }

            }
        });
        priv_room.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    priv_room.setTextColor(Color.parseColor("#D6252B"));
                    arr2.add("private");
                    check = 0;
                }else{
                    priv_room.setTextColor(Color.parseColor("#000000"));
                    arr2.remove("private");
                    check = 1;
                }

            }
        });
        ent_room.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    ent_room.setTextColor(Color.parseColor("#D6252B"));
                    arr2.add("flat");
                    check = 0;
                }else{
                    ent_room.setTextColor(Color.parseColor("#000000"));
                    arr2.remove("flat");
                    check = 1;
                }

            }
        });
        full_furnsh.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    full_furnsh.setTextColor(Color.parseColor("#D6252B"));
                    arr3.add("full");
                    check = 0;
                }else{
                    full_furnsh.setTextColor(Color.parseColor("#000000"));
                    arr3.remove("full");
                    check = 1;
                }

            }
        });
        semi_furnsh.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    semi_furnsh.setTextColor(Color.parseColor("#D6252B"));
                    arr3.add("semi");
                    check = 0;
                }else{
                    semi_furnsh.setTextColor(Color.parseColor("#000000"));
                   arr3.remove("semi");
                    check = 1;
                }

            }
        });
        un_furnsh.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    un_furnsh.setTextColor(Color.parseColor("#D6252B"));
                   // arr.add("Unfurnished");
                    check = 0;
                }else{
                    un_furnsh.setTextColor(Color.parseColor("#000000"));
                   // arr.remove("Unfurnished");
                    check = 1;
                }

            }
        });
        apart.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    apart.setTextColor(Color.parseColor("#D6252B"));
                   arr4.add("apartment");
                    check = 0;
                }else{
                    apart.setTextColor(Color.parseColor("#000000"));
                    arr4.remove("apartment");
                    check = 1;
                }

            }
        });
        inde.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    inde.setTextColor(Color.parseColor("#D6252B"));
                    arr4.add("independent");
                    check = 0;
                }else{
                    inde.setTextColor(Color.parseColor("#000000"));
                   arr4.remove("independent");
                    check = 1;
                }

            }
        });
        villa.setOnClickListener(new View.OnClickListener() {
            int check = 1;
            @Override
            public void onClick(View view) {
                if(check == 1){
                    villa.setTextColor(Color.parseColor("#D6252B"));
                    arr4.add("villa");
                    check = 0;
                }else{
                    villa.setTextColor(Color.parseColor("#000000"));
                   arr4.remove("villa");
                    check = 1;
                }

            }
        });


       final HomeScreenActivity h= new HomeScreenActivity();


        //----apply listner--

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is the data you want to pass


                Onapply();

            }
        });





    }

public void data_setting(){

    for(int i=0;i<arr1.size();i++){
        accomodation_allowed=accomodation_allowed+arr1.get(i)+",";
    }

    for(int i=0;i<arr2.size();i++){
        accomodation_type=accomodation_type+arr2.get(i)+",";
    }
    for(int i=0;i<arr3.size();i++){
        furnish_type=furnish_type+arr3.get(i)+",";
    }
    for(int i=0;i<arr4.size();i++){
        house_type  =house_type+arr4.get(i)+",";
    }

    if(accomodation_type.isEmpty()){
        accomodation_type="flat,shared,private";
    }
    if(accomodation_allowed.isEmpty()){
        accomodation_allowed="girls,boys,family";
    }
    if(furnish_type.isEmpty()){
        furnish_type="full,semi";
    }
    if(house_type.isEmpty()){
        house_type="independent,villa,apartment";
    }




    //-------------------------------------------------------//
    LinkedHashMap<String, String> param = new LinkedHashMap<>();
    param.put("furnish_type",furnish_type);
    param.put("house_type", "independent");
    param.put("accomodation_allowed", accomodation_allowed);
    param.put("accomodation_type", accomodation_type);
    param.put("rent_min", rent_min);
    param.put("rent_max", rent_max);
    param.put("latitude", "28.6554");
    param.put("longitude", "77.1646");
    param.put("radius", radius);

    for (Map.Entry entry :param.entrySet()){
        URL_DATA_filter=URL_DATA_filter+"&"+entry.getKey()+"="+entry.getValue();
    }
    //-------------------------------------------------------//








}


    public  void Onapply(){
      data_setting();

        Log.e("TAG",URL_DATA_filter);
        Log.e("TAG",accomodation_allowed);
        Log.e("TAG",accomodation_type);
        Log.e("TAG",furnish_type);
        Log.e("TAG",house_type);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA_filter,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {


                       Log.e("filter",response);


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

                                item=new Listitem(
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


                               listitems1.add(item);

                            }


                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Listitem>>() {}.getType();
                            String json = gson.toJson(listitems1, type);
                            Intent intent = new Intent(getBaseContext(), HomeScreenActivity.class);
                            intent.putExtra("LIST", json);
                            setResult(3,intent);
                            finish();




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                        Toast.makeText(getApplicationContext(), "Succesfully fetching", Toast.LENGTH_SHORT).show();




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Eror in fetching", Toast.LENGTH_SHORT).show();
                    }
                }) {

        };

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }




    @Override
    public void recreate() {
        super.recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }





}
