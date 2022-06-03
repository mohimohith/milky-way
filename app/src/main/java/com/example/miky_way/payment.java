package com.example.miky_way;

public class payment {

    String id,name,amount,date,time;

    public payment() {
    }

    public payment(String id, String name, String amount, String date,String time) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.time=time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
