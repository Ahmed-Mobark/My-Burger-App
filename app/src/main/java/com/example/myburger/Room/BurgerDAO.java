package com.example.myburger.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface BurgerDAO {
    @Query("SELECT*FROM burgers")
    List<BurgerEntity>getAllBurgers();

    @Query("DELETE FROM burgers")
    void deleteAllProducts();

    @Insert
    void insertBurger(BurgerEntity burgerEntity);
    @Update
    void updateBurger(BurgerEntity burgerEntity);
    @Delete
    void deleteBurger(BurgerEntity burgerEntity);
}
