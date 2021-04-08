package com.example.myburger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myburger.Room.BurgerEntity;

import java.util.List;

public class CartRvAdapter extends RecyclerView.Adapter<CartRvAdapter.CartViewHolder> {

    List<BurgerEntity>burgerList;
    Context context;
    OnIncClick onIncClick;
    OnDecClick onDecClick;


    public interface OnIncClick {
        void onInc(View view, int position);
    }

    public interface OnDecClick {
        void onDec(View view, int position);
    }

    public CartRvAdapter(List<BurgerEntity> burgerList, Context context, OnIncClick onIncClick, OnDecClick onDecClick) {
        this.burgerList = burgerList;
        this.context = context;
        this.onIncClick = onIncClick;
        this.onDecClick = onDecClick;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rv_item, parent, false);
        return  new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
    BurgerEntity burger=burgerList.get(position);
    holder.image.setImageResource(burger.getImages());
    holder.titleTv.setText(burger.getTitle());
    holder.priceTv.setText(burger.getPrice()+"");
    holder.quantityTv.setText(burger.getQuantity()+"");


    holder.decIb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDecClick.onDec(v, holder.getAdapterPosition());
//.replaceAll("[\\D]", "")
            int price = Integer.parseInt(burger.getPrice()+"");
            int quantity = burger.getQuantity();
            int sum = price * quantity;
            holder.priceTv.setText(sum + "");

        }
    });
    holder.incIb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onIncClick.onInc(v, holder.getAdapterPosition());
//.replaceAll("[\\D]", "")
            int price = Integer.parseInt(burger.getPrice()+"");
            int newPrice = price * burger.getQuantity();

            holder.priceTv.setText(newPrice + "");
        }
    });
    }

    @Override
    public int getItemCount() {
        return burgerList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView titleTv;
        TextView priceTv;
        TextView quantityTv;
        ImageButton incIb;
        ImageButton decIb;



        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_cart_iv);
            titleTv = itemView.findViewById(R.id.title_cart_tv);
            priceTv = itemView.findViewById(R.id.price_cart_tv);
            quantityTv=itemView.findViewById(R.id.quantity_tv);
            incIb=itemView.findViewById(R.id.inc_ib);
            decIb=itemView.findViewById(R.id.dec_ib);


        }
    }
}
