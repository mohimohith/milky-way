package com.example.miky_way;

public class billadder {

    String amount,date,id,pending,price,status,time;

    public billadder() {

    }

    public billadder(String amount, String date, String id, String pending,String price, String status, String time) {
        this.amount = amount;
        this.date = date;
        this.id = id;
        this.pending = pending;
        this.price = price;
        this.status = status;
        this.time = time;
    }

    public String getId() {
        return id;
    }



    public String getAmount() {
        return amount;
    }



    public String getStatus() {
        return status;
    }



    public String getDate() {
        return date;
    }



    public String getTime() {
        return time;
    }


    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}