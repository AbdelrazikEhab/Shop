package com.example.souqcom;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter_product extends RecyclerView.Adapter<Adapter_product.MyViewholder> {
    LayoutInflater layoutIntflater;
    Context Context;
    List<product_data> contacts;
    FirebaseFirestore firebaseFirestore;



    public Adapter_product(Context context, List<product_data> contacts) {
        this.Context = context;
        this.contacts = contacts;
        layoutIntflater = LayoutInflater.from(Context);
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutIntflater.inflate(R.layout.recyclerview_prodect, parent, false);
        MyViewholder MY = new MyViewholder(v);
        return MY;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        product_data contant = contacts.get(position);
        holder.name.setText(contant.getModel());
        holder.price.setText("" + contant.getprice());
        holder.Quantati.setText("" + contant.getquantati());
        holder.M1.setImageResource(Integer.parseInt("" + contant.getImage()));
        Glide.with(Context)
                .load(contant.getImage())
                .into(holder.M1);
    }
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void filterList(ArrayList<product_data> filterdNames) {
        this.contacts = filterdNames;
        notifyDataSetChanged();
    }


    class MyViewholder extends RecyclerView.ViewHolder {

        TextView name, price, Quantati;
        ImageView M1;
        Context context;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.name_product);
            price = itemView.findViewById(R.id.salary_product);
            Quantati = itemView.findViewById(R.id.quantati_product);
            M1 = itemView.findViewById(R.id.IM);

//
//            itemView.setOnClickListener((View.OnClickListener) this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(Context).create();
                    View view = layoutIntflater.inflate(R.layout.buy, null);
                    EditText Ename = view.findViewById(R.id.Quantati_Edittext_Buy);
                    alertDialog.setView(view);
                    alertDialog.show();
                    view.findViewById(R.id.Buy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


try {

    String name_buy = Ename.getText().toString();
    salary salary = new salary(name_buy);
    Map<String, Object> map = new HashMap<>();
    map.put("Buying", salary.getBuy());

    firebaseFirestore.collection("Users")
            .document(salary.getBuy())
            .set(map)
            .addOnSuccessListener(avoid ->
                    Toast.makeText(Context, "Data is added successfilly", Toast.LENGTH_SHORT).show());
} catch (Exception e) {
    e.printStackTrace();
}
                        }
                    });

                }
            });


        }


    }
        }







