package com.example.miky_way;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class adapter extends ArrayAdapter<user> {
    private static final String TAG = "ladapter";
    private  Context mContext;
    int mresources;

    public adapter(@NonNull Context mContext, int resource, @NonNull List<user> objects) {
        super(mContext, resource, objects);
        this.mContext = mContext;
        mresources=resource;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup parent) {

        String id=getItem(position).getId();
        String name=getItem(position).getName();
        String mobile=getItem(position).getMobile();
        String password=getItem(position).getPassword();
        String address=getItem(position).getAddress();
        String Balance=getItem(position).getBalance();

        user u=new user(id,name,mobile,address,password,Balance);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mresources,parent,false);
        TextView id1=(TextView)convertView.findViewById(R.id.textView11);
        TextView name1=(TextView)convertView.findViewById(R.id.textView12);
        TextView mobile1=(TextView)convertView.findViewById(R.id.textView13);
        TextView address1=(TextView)convertView.findViewById(R.id.textView14);
        TextView password1=(TextView)convertView.findViewById(R.id.textView15);
        TextView balance1=(TextView)convertView.findViewById(R.id.textView16);
        id1.setText(id);
        name1.setText(name);
        mobile1.setText(mobile);
        password1.setText(password);
        address1.setText(address);
        balance1.setText(Balance);


        return convertView;
    }
}
