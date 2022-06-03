package com.example.miky_way;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class balconfirm extends AppCompatActivity {
    DatabaseReference seeref;
    Button confirm;
    TextView name,id,amount,status;
    String mobile1;
    String period,day,date1,time;
    String id1,amount1,name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balconfirm);
        seeref= FirebaseDatabase.getInstance().getReference();

        name=(TextView)findViewById(R.id.View3);
        id=(TextView)findViewById(R.id.View8);
        amount=(TextView)findViewById(R.id.View6);
        status=(TextView)findViewById(R.id.View10);
        confirm=(Button)findViewById(R.id.bc7);
        id1=getIntent().getStringExtra("id");
        amount1=getIntent().getStringExtra("Amount");
        date1=getIntent().getStringExtra("date");
        name2=getIntent().getStringExtra("name");
        period=getIntent().getStringExtra("period");
        name.setText(name2);
        id.setText(id1);
        amount.setText(amount1);
        status.setText(date1);
        seeref= FirebaseDatabase.getInstance().getReference();


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                insertbill();

            }
        });



    }
    private void insertbill() {


        DateTimeFormatter dt = null;

        LocalDateTime now = LocalDateTime.now();
        dt= DateTimeFormatter.ofPattern("dd-hhmmss");
        day=dt.format(now);
        dt= DateTimeFormatter.ofPattern("MMyyyy");
        period=dt.format(now);
        dt= DateTimeFormatter.ofPattern("hh-mm-ss-a");
        time=dt.format(now);


        payment add= new payment(id1,name2,amount1,date1,time);
        Map<String, Object> users = new HashMap<>();
        users.put(day,add);
        DatabaseReference updated=seeref.child("payment").child(id1).child(period);

        updated.updateChildren(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(balconfirm.this,"Succesfully added",Toast.LENGTH_SHORT).show();
                Intent i =new Intent(balconfirm.this,success.class);
                startActivity(i);
                finish();
            }
        });
    }
}