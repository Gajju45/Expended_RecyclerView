package com.Android.Gajju;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.YELLOW;
import static android.graphics.Color.colorSpace;
import static android.graphics.Color.parseColor;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Product> products;
    ArrayList<Product> products2;



    public Adapter(Context ctx, ArrayList<Product> data ,ArrayList<Product>products2){
        this.inflater = LayoutInflater.from(ctx);
        this.products = data;
        this.products2=products2;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // bind the data
        holder.Title.setText(products.get(position).getTitle());

        Picasso.get().load(products.get(position).getCoverImage()).into(holder.CoverImage);

        //exp
        holder.expText.setText(products2.get(position).getExpName());
        Log.e("myLog","EXPENDED TEXT Is" +holder.expText) ;
    Picasso.get().load(products2.get(position).getExpImg()).into(holder.expImage);
        Log.e("myLog","EXPENDED Image Is" +holder.expImage) ;

        boolean isExpanded=products.get(position).isExpended();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

       holder.imgdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdown.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                holder.constraintLayout.setBackgroundColor(parseColor("#F5B041"));

            }
        });



    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView Title,expText ;
        ImageView CoverImage,expImage ,imgdown;
        RelativeLayout expandableLayout;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            
           Title = itemView.findViewById(R.id.songTitle);

            CoverImage = itemView.findViewById(R.id.coverImage);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            //exp
            expText= itemView.findViewById(R.id.textexp);
            expImage= itemView.findViewById(R.id.imgexp);
            imgdown=itemView.findViewById(R.id.downimg);

            constraintLayout=itemView.findViewById(R.id.MainL);


            // handle onClick

            Title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Product pro = products.get(getAdapterPosition());
                    pro.setExpended(!pro.isExpended());
                    notifyItemChanged(getAdapterPosition());


                    //relativeLayout.setBackgroundColor(Color.parseColor("#FFF000"));
                }
            });
        }
    }
}
