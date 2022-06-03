package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class main3 extends AppCompatActivity {
    CardView addbill,adduser,showbill,showuser,validate,balance,payments,update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addbill=(CardView)findViewById(R.id.c00);
        adduser=(CardView)findViewById(R.id.c01);
        showbill=(CardView)findViewById(R.id.c10);
        showuser=(CardView)findViewById(R.id.c11);
        validate=(CardView)findViewById(R.id.c20);
        balance=(CardView)findViewById(R.id.c21);
        payments=(CardView)findViewById(R.id.c40);
        update=(CardView)findViewById(R.id.c41);


        addbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,addbill.class);
                startActivity(intent);
            }
        });
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,newuser.class);
                startActivity(intent);
            }
        });
        showuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,showuser.class);
                startActivity(intent);
            }
        });
        showbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,showbill.class);
                startActivity(intent);
            }
        });
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,Validatebill.class);
                startActivity(intent);
            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,valid.class);
                startActivity(intent);
            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,payments.class);
                startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main3.this,updateuser.class);
                startActivity(intent);
            }
        });
    }
}