package com.example.miky_way;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Validatebill extends AppCompatActivity {
EditText e1,e2;
Button b;
DatabaseReference dbr;
        DatabaseReference abr;
String balance,amount,remain;
Double bal;
    DatabaseReference balances;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validatebill);
        b=(Button)findViewById(R.id.bbuttonv);
        e1=(EditText)findViewById(R.id.bedt);
        e2=(EditText)findViewById(R.id.bedt2v);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=e1.getText().toString();
                String period = e2.getText().toString();
                dbr= FirebaseDatabase.getInstance().getReference("bill").child(id).child(period);
                abr= FirebaseDatabase.getInstance().getReference("users").child(id);
                balances=FirebaseDatabase.getInstance().getReference("users").child(id);
                abr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        balance=snapshot.child("balance").getValue().toString();
                        bal=Double.parseDouble(balance);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dbr.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                            String key=ds.getKey();
                            DatabaseReference bill=dbr.child(key);
                            bill b=ds.getValue(bill.class);
                            amount=b.getPending();
                            Double pend=Double.parseDouble(amount);
                            if(bal>pend){
                                bal=bal-pend;
                                bill.child("status").setValue("PAID");
                                bill.child("pending").setValue("0");


                            }else{

 continue;

                            }

                            remain=bal+"";
                            balances.child("balance").setValue(remain);

                        }

                        Toast.makeText(Validatebill.this,"Succesfully validated",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }
}