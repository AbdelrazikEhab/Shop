package com.example.souqcom;

import com.google.firebase.firestore.Exclude;

public class product_data {

    String model;
    int price;
    int quantati;
    String image;
    @Exclude
    private String id;

    public product_data() {
    }

    public product_data(String model, int price, int quantati, String image) {
        this.model = model;
        this.price = price;
        this.quantati = quantati;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }


    public int getquantati() {
        return quantati;
    }

    public void setquantati(int quantati) {
        this.quantati = quantati;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getprice() {
        return price;
    }

    public void setprice(int salary) {
        this.price = salary;
    }

    @Override
    public String toString() {
        return "product_data{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", quantati=" + quantati +
                ", image='" + image + '\'' +
                '}';
    }
}
