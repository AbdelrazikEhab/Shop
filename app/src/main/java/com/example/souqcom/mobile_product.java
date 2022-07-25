package com.example.souqcom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class mobile_product extends AppCompatActivity  {

    RecyclerView rc;
    List<product_data> cotants =new ArrayList<>();
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
        setContentView(R.layout.activity_mobile_product);
        rc=findViewById(R.id.mobile_product);

        cotants = new ArrayList<>();
        adapter = new Adapter_product(mobile_product.this, cotants);
        rc.setLayoutManager(new GridLayoutManager(this, 1));
        rc.setAdapter(adapter);// d

        db = FirebaseFirestore.getInstance();
        db.collection("Gategories")
                .document("Electronics")
                .collection("Electronics")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> documents = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot documentSnapshot : documents) {
                            product_data s = documentSnapshot.toObject(product_data.class);

                            s.setId(documentSnapshot.getId());
                            cotants.add(s);
                          


//                            getNotification("Electronic", s.getModel());

                            Log.d("ID Clothes", "doucment ID" + s.getId());


                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });


    }



//    private void filter(String text) {
//        ArrayList<product_data> filterdNames = new ArrayList<>();
//
//        //looping through existing elements
//        //if the existing elements contains the search input
//        for (product_data s : cotants) {
//            if (s.getModel().contains(text.toLowerCase())) {
//                //adding the element to filtered list
//                filterdNames.add(s);
//            }
//        }
//
//        //calling a method of the adapter class and passing the filtered list
//        adapter.filterList(filterdNames);
//    }




    //Oppo A54 Dual SIM Mobile Phone - 6.51 Inch, 64 GB, 4 GB RAM, 4G LTE - Crystal Black


}