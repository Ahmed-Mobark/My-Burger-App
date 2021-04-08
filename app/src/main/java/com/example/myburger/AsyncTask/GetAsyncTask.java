package com.example.myburger.AsyncTask;

import android.os.AsyncTask;

import com.example.myburger.Room.BurgerDAO;
import com.example.myburger.Room.BurgerEntity;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class GetAsyncTask extends AsyncTask<Void,Void,List<BurgerEntity>> {
    private BurgerDAO burgerDAO;

    public GetAsyncTask(BurgerDAO burgerDAO) {
        this.burgerDAO = burgerDAO;
    }


    @Override
    protected List<BurgerEntity> doInBackground(Void... voids) {
        return burgerDAO.getAllBurgers();
    }
}
