package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class showbill extends AppCompatActivity {

    EditText et,et1;
    Button b;
String identity,period;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showbill);
        et=(EditText) findViewById(R.id.sbedt);
        et1=(EditText) findViewById(R.id.sbedt2);
        b=(Button) findViewById(R.id.sbbutton);
        BottomNavigationView bottonnav=(BottomNavigationView) findViewById(R.id.bnav5);
        bottonnav.setSelectedItemId(R.id.it2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               identity=et.getText().toString();
               period=et1.getText().toString();
                Intent i = new Intent(showbill.this,bills.class);
                i.putExtra("id",identity);
                i.putExtra("period",period);
                startActivity(i);

            }
        });
        bottonnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.it1:
                        startActivity(new Intent(showbill.this,valid.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(showbill.this,main3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(showbill.this,showbill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}