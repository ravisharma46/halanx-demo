package com.example.apple.halanx_test_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class filter_activity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView seek_result,date_pick,date_set,boy,girl,family,sh_room,priv_room,ent_room,full_furnsh,semi_furnsh,un_furnsh,apart,inde,villa;
    private EditText min_R,max_R;
    private DatePickerDialog datePickerDialog;
    private Button reset,apply;




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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filter");


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


       apply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });



        boy.setOnClickListener(new View.OnClickListener() {
           int check = 1;
           @Override
           public void onClick(View view) {

               if(check == 1){
                   boy.setTextColor(Color.parseColor("#D6252B"));



                   check = 0;
               }else{
                   boy.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    girl.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    family.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    sh_room.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    priv_room.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    ent_room.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    full_furnsh.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    semi_furnsh.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    un_furnsh.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    apart.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    inde.setTextColor(Color.parseColor("#000000"));
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
                    check = 0;
                }else{
                    villa.setTextColor(Color.parseColor("#000000"));
                    check = 1;
                }

            }
        });










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
