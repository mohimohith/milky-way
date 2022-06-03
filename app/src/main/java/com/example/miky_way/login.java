package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    Button login;
    EditText e1,e2;
    Integer  n,p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.button3);
        e1=(EditText)findViewById(R.id.ledt1);
        e2= (EditText)findViewById(R.id.ledt2);


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String mob = e1.getText().toString();
                String pass= e2.getText().toString();
                if(TextUtils.isEmpty(mob) | TextUtils.isEmpty(pass)){
                    Toast.makeText(login.this,"filll the details",Toast.LENGTH_SHORT).show();

                }else {
                    n= (Integer.parseInt(mob));
                    p= (Integer.parseInt(pass));
                    n=n-950217;
                    p=p-950217;
                    if(n==0 & p==0){
                        Intent intent = new Intent(login.this,main3.class);
                        startActivity(intent);
                        finish();

                    }else{
                        Toast.makeText(login.this,"Wrong details",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}
