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

public class Confirm extends AppCompatActivity {
    DatabaseReference addref,seeref;
    Button confirm,back;
    TextView name,id,amount,status,prices,t2;
    String name1,mobile1,value;
    String monthyear,time,date,day;
    String status1,id1,pending,price,amount1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        addref= FirebaseDatabase.getInstance().getReference();

        name=(TextView)findViewById(R.id.textView3);
        id=(TextView)findViewById(R.id.textView8);
        amount=(TextView)findViewById(R.id.textView6);
        status=(TextView)findViewById(R.id.textView10);
        prices=(TextView)findViewById(R.id.text6);
        t2=(TextView)findViewById(R.id.textView2);
        confirm=(Button)findViewById(R.id.b7);
        id1=getIntent().getStringExtra("id");
        pending=getIntent().getStringExtra("pending");
        price=getIntent().getStringExtra("price");
        status1=getIntent().getStringExtra("status");
        amount1=getIntent().getStringExtra("amount");
        seeref= FirebaseDatabase.getInstance().getReference("users").child(id1);
        seeref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                name1=snapshot.child("name").getValue().toString();
                mobile1=snapshot.child("mobile").getValue().toString();
                name.setText(name1);
                id.setText(id1);
                amount.setText(amount1);
                status.setText(status1);
                prices.setText(price);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        dt= DateTimeFormatter.ofPattern("MMyyyy");
        monthyear=dt.format(now);
        dt= DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date=dt.format(now);
        dt= DateTimeFormatter.ofPattern("hh:mm a");
        time=dt.format(now);
        dt= DateTimeFormatter.ofPattern("dd-hhmmss");
        day=dt.format(now);




        billadder add= new billadder(amount1,date,id1,pending,price ,status1, time);
        Map<String, Object> users = new HashMap<>();
        users.put(day,add);
        DatabaseReference updated=addref.child("bill").child(id1).child(monthyear);

        updated.updateChildren(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Confirm.this,"Succesfully added",Toast.LENGTH_SHORT).show();
                Intent i =new Intent(Confirm.this,success.class);
                startActivity(i);
                finish();
            }
        });



    }
}