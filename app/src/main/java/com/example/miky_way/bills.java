package com.example.miky_way;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bills extends AppCompatActivity {

String id,period;
ListView lv;
    ArrayList<String> arr= new ArrayList<String>();
    ArrayAdapter<String> arrdp;
    DatabaseReference dbr;
    Double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        lv=(ListView)findViewById(R.id.list1);

        id=getIntent().getStringExtra("id");
        period=getIntent().getStringExtra("period");
        total=0.0;
        dbr= FirebaseDatabase.getInstance().getReference("bill").child(id).child(period);
        arrdp= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr);
        lv.setAdapter(arrdp);
        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    bill b=ds.getValue(bill.class);
                    String data=b.getDate().toString()+"   "+b.getTime().toString()+"   "+b.getAmount()+"   "+ b.getPending().toString()+"   "+b.getStatus().toString();
                    String p=b.getPending().toString();
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


    }
}