package com.example.myburger.AsyncTask;

import android.os.AsyncTask;

import com.example.myburger.Room.BurgerDAO;

public class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void> {
    private BurgerDAO burgerDAO;

    public DeleteAllAsyncTask(BurgerDAO burgerDAO) {
        this.burgerDAO = burgerDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        burgerDAO.deleteAllProducts();
        return null;
    }
}
