package com.example.myburger.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myburger.AsyncTask.DeleteAllAsyncTask;
import com.example.myburger.AsyncTask.DeleteAsyncTask;
import com.example.myburger.AsyncTask.GetAsyncTask;
import com.example.myburger.AsyncTask.UpdateAsyncTask;
import com.example.myburger.CartRvAdapter;
import com.example.myburger.R;
import com.example.myburger.Room.BurgerEntity;
import com.example.myburger.Room.RoomFactory;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class CartFragment extends Fragment {
    RecyclerView cartRv;
    List<BurgerEntity>burgerList=new ArrayList<>();
    CartRvAdapter cartRvAdapter;
    MaterialButton clearBtn;
    MaterialButton goToPaymentBtn;

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartRv=view.findViewById(R.id.cart_rv);
        clearBtn=view.findViewById(R.id.clearBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllProducts();

        setUpRecyclerView();
        setUpClickListeners();

        setRvItemSwipe();

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(cartRv);



    }

    private void setRvItemSwipe() {
        simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //Toast.makeText(requireContext(), "I'm moving", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();

                BurgerEntity burgerEntity = burgerList.get(position);
                new DeleteAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute(burgerEntity);
                burgerList.remove(position);
                cartRvAdapter.notifyItemRemoved(position);

            }
        };

    }

    private void setUpClickListeners() {
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAllAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute();
                burgerList.clear();
                cartRvAdapter.notifyDataSetChanged();
            }
        });

    }


    private void getAllProducts(){

        burgerList.clear();

        try {
           burgerList.addAll(new GetAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    private void setUpRecyclerView() {

        cartRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        cartRv.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));


        cartRvAdapter = new CartRvAdapter(burgerList, requireContext() , new CartRvAdapter.OnIncClick() {

            @Override
            public void onInc(View view, int position) {

                BurgerEntity burgerEntity = burgerList.get(position);
                burgerEntity.setQuantity(burgerEntity.getQuantity() + 1);


                new UpdateAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute(burgerEntity);
                cartRvAdapter.notifyDataSetChanged();

            }

        }, new CartRvAdapter.OnDecClick() {
            @Override
            public void onDec(View view, int position) {

                BurgerEntity burgerEntity = burgerList.get(position);

                if (burgerEntity.getQuantity() > 1) {
                    burgerEntity.setQuantity(burgerEntity.getQuantity() - 1);
                    new UpdateAsyncTask(RoomFactory.getBurgerDatabase(requireContext()).getBurgerDAO()).execute(burgerEntity);
                    cartRvAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "item quantity cannot be less than 1 :)", Toast.LENGTH_SHORT).show();
                }
            }
         });

        cartRv.setAdapter(cartRvAdapter);
    }

}