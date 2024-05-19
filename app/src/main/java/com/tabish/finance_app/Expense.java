package com.tabish.finance_app;

import java.util.Date;

public class Expense {
    private String name;
    private int image;
    private double amount;
    private String sdate;
    private Date date;

    public Expense(String name, int image) {
        this.name = name;
        this.image = image;

    }

    public void setDate() {
        this.date = new Date();
        this.sdate = date.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public String getSdate() {
        return sdate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
