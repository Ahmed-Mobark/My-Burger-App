package com.example.myburger.AsyncTask;

import android.os.AsyncTask;

import com.example.myburger.Room.BurgerDAO;
import com.example.myburger.Room.BurgerEntity;

public class UpdateAsyncTask extends AsyncTask<BurgerEntity,Void,Void> {
    private BurgerDAO burgerDAO;

    public UpdateAsyncTask(BurgerDAO burgerDAO) {
        this.burgerDAO = burgerDAO;
    }

    @Override
    protected Void doInBackground(BurgerEntity... burgerEntities) {
       burgerDAO.updateBurger(burgerEntities[0]);
        return null;
    }
}
