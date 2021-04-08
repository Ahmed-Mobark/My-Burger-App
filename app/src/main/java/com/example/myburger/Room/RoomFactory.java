package com.example.myburger.Room;

import android.content.Context;

import androidx.room.Room;

public class RoomFactory {
    private static BurgerDatabase burgerDatabase;


    public static BurgerDatabase getBurgerDatabase(Context context) {

        if (burgerDatabase == null) {
            burgerDatabase = Room.databaseBuilder(context,
                    BurgerDatabase.class, "Burger_db")
                    .build();
        }

        return burgerDatabase;

    }
}
