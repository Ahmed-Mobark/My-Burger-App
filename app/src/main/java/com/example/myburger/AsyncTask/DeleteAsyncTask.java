package com.example.myburger.AsyncTask;

import android.os.AsyncTask;

import com.example.myburger.Room.BurgerDAO;
import com.example.myburger.Room.BurgerEntity;

public class DeleteAsyncTask  extends AsyncTask<BurgerEntity,Void,Void> {
    private BurgerDAO burgerDAO;

    public DeleteAsyncTask(BurgerDAO burgerDAO) {
        this.burgerDAO = burgerDAO;
    }

    @Override
    protected Void doInBackground(BurgerEntity... burgerEntities) {
        burgerDAO.deleteBurger(burgerEntities[0]);
        return null;
    }
}
