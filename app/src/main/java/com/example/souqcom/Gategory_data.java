package com.example.souqcom;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Map;

public class Gategory_data  {

    String name;

    @Override
    public String toString() {
        return "Gategory_data{" +
                "name='" + name + '\'' +
                '}';
    }

    public Gategory_data(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

