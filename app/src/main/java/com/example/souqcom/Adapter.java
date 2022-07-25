package com.example.souqcom;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewholder> {
    LayoutInflater layoutIntflater;
    Context Context;
    List<Gategory_data> contacts;


    public Adapter(Context context, List<Gategory_data> contacts) {
        this.Context = context;
        this.contacts = contacts;
        layoutIntflater = LayoutInflater.from(Context);
    }


    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutIntflater.inflate(R.layout.adapter, parent, false);
        MyViewholder MY = new MyViewholder(v);
        return MY;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        Gategory_data contant = contacts.get(position);
        holder.name.setText(contant.getName());


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void filterList(ArrayList<Gategory_data> filterdNames) {
        this.contacts = filterdNames;
        notifyDataSetChanged();
    }


    class MyViewholder extends RecyclerView.ViewHolder {

        TextView name;
        Context context;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            name = itemView.findViewById(R.id.view_categ);



  name.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          final Intent home;
          switch (getAdapterPosition()) {
              case 0:
                  home = new Intent(context, Clothes_products.class);
                  break;
              case 1:
                  home = new Intent(context, mobile_product.class);
                  break;
              default:
                  home = new Intent(context, food_product.class);
          }
          context.startActivity(home);
      }
  });





    }
        }
    }
