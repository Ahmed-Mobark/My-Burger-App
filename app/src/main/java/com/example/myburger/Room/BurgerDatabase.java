package com.example.myburger.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BurgerEntity.class},version = 1,exportSchema = false)
public abstract class BurgerDatabase extends RoomDatabase {
    public abstract BurgerDAO getBurgerDAO();
}
