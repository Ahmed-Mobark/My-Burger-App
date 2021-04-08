package com.example.myburger.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "burgers")
public class BurgerEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "images")
    private int images;
    @ColumnInfo(name = "quantity")
    private int quantity;

    public BurgerEntity(String title, int price, int images) {
        this.title = title;
        this.price = price;
        this.images = images;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
