package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class showuser extends AppCompatActivity {
ListView mylistview;
    DatabaseReference db;
    ArrayList<user> arrList= new ArrayList<>();
    adapter arrAdp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);
        mylistview=(ListView) findViewById(R.id.list);
        db= FirebaseDatabase.getInstance().getReference("users");
        BottomNavigationView bottonnav=(BottomNavigationView) findViewById(R.id.bnav2);
        bottonnav.setSelectedItemId(R.id.it2);
        arrAdp= new adapter(this,R.layout.list,arrList);
        mylistview.setAdapter(arrAdp);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    user ba= ds.getValue(user.class);
                    String txt= ba.getId().toString()+" "+ba.getName().toString()+" "+ba.getMobile().toString()+" "+ba.getPassword()+" "+ba.getAddress().toString()+" "+ba.getBalance();
                    arrList.add(ba);
                }
                arrAdp.notifyDataSetChanged();

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
                        startActivity(new Intent(showuser.this,valid.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(showuser.this,main3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(showuser.this,showbill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}