package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addbill extends AppCompatActivity {

     EditText e1,e2;
     RadioGroup r;
     Button b,back;
     String status,monthyear,time,date,day;
     double pricef,pendingf;
    FloatingActionButton plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbill);

        e1 = (EditText) findViewById(R.id.edtadd1);
        e2 =(EditText)findViewById(R.id.edtadd2);
        b = (Button)findViewById(R.id.buttonadd);
        r=(RadioGroup)findViewById(R.id.rgrp);
        BottomNavigationView bottonnav=(BottomNavigationView) findViewById(R.id.bnav3);
        bottonnav.setSelectedItemId(R.id.it2);
        b.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                int checked =r.getCheckedRadioButtonId();
                if(checked==-1){
                    Toast.makeText(addbill.this,"select the field(PAID/UNPAID)",Toast.LENGTH_SHORT).show();
                }else if(checked== R.id.radioButton){
                    status="PAID";
                }else {
                    status="UNPAID";
                }
                String id1 =e1.getText().toString();
                String amount =e2.getText().toString();
                pricef = (Integer.parseInt(amount))*0.06;
                if(status=="UNPAID"){
                    pendingf =pricef;
                }else{
                    pendingf=0;
                }
                String price=Double.toString(pricef);
                String pending = Double.toString(pendingf);

               Intent i=new Intent(addbill.this,Confirm.class);
                i.putExtra("amount",amount);
                i.putExtra("id",id1);
                i.putExtra("pending",pending);
                i.putExtra("price",price);
                i.putExtra("status",status);
                startActivity(i);

            }
        });
        bottonnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.it1:
                        startActivity(new Intent(addbill.this,valid.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(addbill.this,main3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(addbill.this,showbill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });




    }


}