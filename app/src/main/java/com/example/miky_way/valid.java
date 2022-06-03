package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class valid extends AppCompatActivity {
    Button add;
    EditText etv1, etv2;
    DatabaseReference dbref;

    TextView tv,tv2;

    String id,Amount,oldAmount,updated,name,date;
    Double   balancenew,balanceold,newbal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valid);
        add = (Button) findViewById(R.id.bbuttonv);
        etv1 = (EditText) findViewById(R.id.vbedt);
        etv2 = (EditText) findViewById(R.id.bedt2v);
dbref= FirebaseDatabase.getInstance().getReference("users");
tv=(TextView) findViewById(R.id.vtextView);
tv2=(TextView) findViewById(R.id.vtextView4);

        BottomNavigationView bottonnav = (BottomNavigationView) findViewById(R.id.bnav6);
        bottonnav.setSelectedItemId(R.id.it2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               id=etv1.getText().toString();
               Amount=etv2.getText().toString();
               balancenew=Double.parseDouble(Amount);
               DatabaseReference newdb= dbref.child(id);
               date=Date();
               newdb.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                      oldAmount=snapshot.child("balance").getValue().toString();
                      name=snapshot.child("name").getValue().toString();
                      balanceold=Double.parseDouble(oldAmount);
                      newbal=balancenew+balanceold;
                      updated=newbal+"";
                      dbref.child(id).child("balance").setValue(updated).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void unused) {
                              Intent i=new Intent(valid.this,balconfirm.class);
                              i.putExtra("id",id);
                              i.putExtra("name",name);
                              i.putExtra("Amount",Amount);
                              i.putExtra("date",date);
                              startActivity(i);
                          }
                      });



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
                switch (item.getItemId()) {
                    case R.id.it1:
                        startActivity(new Intent(valid.this, valid.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.it2:
                        startActivity(new Intent(valid.this, main3.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.it3:
                        startActivity(new Intent(valid.this, showbill.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }
    String Date(){
        DateTimeFormatter dt = null;

        LocalDateTime now = LocalDateTime.now();
        dt= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String monthyear=dt.format(now);
        return monthyear;
    }

}