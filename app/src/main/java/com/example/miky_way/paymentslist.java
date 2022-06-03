package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class paymentslist extends AppCompatActivity {
    String id,period;
    ListView lv;
    ArrayList<String> arr= new ArrayList<String>();
    ArrayAdapter<String> arrdp;
    DatabaseReference dbr;
    Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentslist);
        lv=(ListView)findViewById(R.id.listp);
        BottomNavigationView bottonnav=(BottomNavigationView) findViewById(R.id.pnav6);
        bottonnav.setSelectedItemId(R.id.it2);
        id=getIntent().getStringExtra("id");
        period=getIntent().getStringExtra("period");
        total=0.0;
        dbr= FirebaseDatabase.getInstance().getReference("payment").child(id).child(period);
        arrdp= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(arrdp);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    payment b=ds.getValue(payment.class);
                    String data=b.getId().toString()+"   "+b.getName().toString()+"   "+b.getDate()+"   "+ b.getTime().toString()+"   "+b.getAmount().toString();
                    String p=b.getAmount().toString();
                    double price= Double.parseDouble(p);
                    total=total+price;
                    arr.add(data);
                }
                String tot="Total  =  " + Double.toString(total);
                arr.add(tot);
                arrdp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bottonnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.it1:
                        startActivity(new Intent(paymentslist.this,valid.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(paymentslist.this,main3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(paymentslist.this,showbill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}