package com.example.myburger.AsyncTask;

import android.os.AsyncTask;

import com.example.myburger.Room.BurgerDAO;
import com.example.myburger.Room.BurgerEntity;

public class AddAsyncTask extends AsyncTask<BurgerEntity,Void,Void> {
private BurgerDAO burgerDAO;

    public AddAsyncTask(BurgerDAO burgerDAO) {
        this.burgerDAO = burgerDAO;
    }

    @Override
    protected Void doInBackground(BurgerEntity... burgerEntities) {
      burgerDAO.insertBurger (burgerEntities[0]) ;
        return null;
    }
}
