package com.example.souqcom;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Gategory extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rc;
    List<Gategory_data> cotants = new ArrayList<>();
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gategory);

        rc = findViewById(R.id.categ);
        rc.setOnClickListener(this);
        rc.setOnClickListener((View.OnClickListener) this);
        cotants = new ArrayList<>();



        cotants.add(new Gategory_data("Clothes"));
        cotants.add(new Gategory_data("Electronics"));
        cotants.add(new Gategory_data("Food"));
        adapter = new Adapter(this, cotants);
        rc.setLayoutManager(new GridLayoutManager(this, 1));
        rc.setAdapter(adapter);// data set changed



    }

    @Override
    public void onClick(View v) {

    }
}