package com.example.myburger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;

import com.example.myburger.AsyncTask.AddAsyncTask;
import com.example.myburger.Room.BurgerEntity;
import com.example.myburger.Room.RoomFactory;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import java.util.List;



public class SpecialOf_RvAdapter extends RecyclerView.Adapter<SpecialOf_RvAdapter.SpecialOfViewHolder> {
    List<SpecialOf_Data> specialOfList;
    OnAddClick onAddClick;
    Context context;

    public interface OnAddClick{
        void onProductAddClick(View view,int position);
    }

    public SpecialOf_RvAdapter(List<SpecialOf_Data> specialOfList, Context context) {
        this.specialOfList = specialOfList;
        this.context = context;
    }

    @NonNull
    @Override
    public SpecialOfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.special_offers_items_rv,parent,false);

        return new SpecialOfViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SpecialOfViewHolder holder, int position) {
SpecialOf_Data specialOf_data=specialOfList.get(position);
holder.product_title.setText(specialOf_data.getTitle());
holder.product_price.setText(specialOf_data.getPrice()+" EGP");
holder.product_image.setImageResource(specialOf_data.getImage());

holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String title=specialOfList.get(position).getTitle();
        int price=specialOfList.get(position).getPrice();
        int image=specialOfList.get(position).getImage();

       // onAddClick.onProductAddClick(v,position);
        
       // new AddAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute(new BurgerEntity(title,price,image));
          new AddAsyncTask(RoomFactory.getBurgerDatabase(context).getBurgerDAO()).execute(new BurgerEntity(title,price,image));
        Navigation.findNavController(v).navigate(R.id.action_menuFragment_to_cartFragment);

     }

});

    }



    @Override
    public int getItemCount() {
        return specialOfList.size();
    }

    class SpecialOfViewHolder extends RecyclerView.ViewHolder{
       TextView product_title;
       TextView product_price;
       ImageView product_image;
       MaterialButton addToCartBtn;


    public SpecialOfViewHolder(@NonNull View itemView) {
        super(itemView);
   product_title=itemView.findViewById(R.id.title_tv);
   product_price=itemView.findViewById(R.id.priceTv);
   product_image=itemView.findViewById(R.id.SpOff_Iv);
   addToCartBtn=itemView.findViewById(R.id.AddToCart_bt);
    }
}
}






