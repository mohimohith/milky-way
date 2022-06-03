package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class updateuser extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button register,back;
    DatabaseReference db ;
    FloatingActionButton plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateuser);
        e1 = (EditText) findViewById(R.id.et1);
        e2 = (EditText) findViewById(R.id.et2);
        e3 = (EditText) findViewById(R.id.et3);
        e4 = (EditText) findViewById(R.id.et4);
        e5 = (EditText) findViewById(R.id.et5);
        BottomNavigationView bottonnav=(BottomNavigationView) findViewById(R.id.bnav);
        bottonnav.setSelectedItemId(R.id.it2);
        db = FirebaseDatabase.getInstance().getReference();

        register=(Button) findViewById(R.id.rbtn);

        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                DatabaseReference rf=db.child("users");
                String id = e4.getText().toString();
                rf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(id)){
                            insertstudent();
                        }else{

                            Toast.makeText(updateuser.this,"User id DoNot exists",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





            }
        });
        bottonnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.it1:
                        startActivity(new Intent(updateuser.this,valid.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(updateuser.this,main3.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(updateuser.this,showbill.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }

    private void insertstudent() {
        String name = e1.getText().toString();
        String mobile = e2.getText().toString();
        String address = e3.getText().toString();
        String id = e4.getText().toString();
        String password = e5.getText().toString();
        String Balance = "0";
        DatabaseReference updated=db.child("users");
        user u=new user(address,id,mobile,name,password,Balance);
        Map<String, Object> users = new HashMap<>();
        users.put(id,u);
        updated.updateChildren(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(updateuser.this,"Succesfully added",Toast.LENGTH_SHORT).show();
                Intent i =new Intent(updateuser.this,success.class);
                startActivity(i);

            }
        });
    }
}