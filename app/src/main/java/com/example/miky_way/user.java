package com.example.miky_way;

public class user {

    String id,name,mobile,address,password,Balance;

    public user() {
    }

    public user( String address,String id,  String mobile,String name, String password,String Balance) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.password = password;
        this.Balance = Balance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }
}
