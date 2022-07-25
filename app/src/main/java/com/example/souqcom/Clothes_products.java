package com.example.souqcom;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Clothes_products extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rc;
    List<product_data> cotants;
    Button create,searchB ;
    ImageView M1;
    EditText search;
    Adapter_product adapter;
    FirebaseFirestore db;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_clothes_products);
//    rc=findViewById(R.id.l3);
    rc=findViewById(R.id.clothes_product);

//        rc.setOnClickListener((View.OnClickListener) this);
        cotants = new ArrayList<>();
        adapter = new Adapter_product(Clothes_products.this, cotants);
        rc.setLayoutManager(new GridLayoutManager(this, 1));
        rc.setAdapter(adapter);// data set changed
        db = FirebaseFirestore.getInstance();
        db.collection("Gategories")
                .document("Clothes")
                .collection("Clothes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot documentSnapshot : documents) {
                            product_data s = documentSnapshot.toObject(product_data.class);

                                s.setId(documentSnapshot.getId());
                                cotants.add(s);
                            getNotification("Clothes", s.getModel());

                            Log.d("ID Clothes", "doucment ID" + s.getId());


                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void getNotification(String clothes, String text) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("r","r", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Clothes_products.this,"r");

        builder.setContentTitle(clothes);
        builder.setContentText(text);
        builder .setSmallIcon(R.drawable.not);
        builder.setAutoCancel(true);
        NotificationManagerCompat ma= NotificationManagerCompat.from(Clothes_products.this);
        ma.notify(1,builder.build());
    }

    private void filter(String text) {
        ArrayList<product_data> filterdNames = new ArrayList<>();

        //looping through existing elements
        //if the existing elements contains the search input
        for (product_data s : cotants) {
            if (s.getModel().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }


    //Oppo A54 Dual SIM Mobile Phone - 6.51 Inch, 64 GB, 4 GB RAM, 4G LTE - Crystal Black

    @Override
    public void onClick(View v) {

        Intent home=new Intent(Clothes_products.this, Login.class);
        startActivity(home);
        finish();


    }
}