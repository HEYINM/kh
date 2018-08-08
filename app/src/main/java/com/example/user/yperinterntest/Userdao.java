package com.example.user.yperinterntest;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
//@TypeConverters(User.class)
public interface Userdao{
    @Insert
    long insertUser(RecyclerItem items);
    @Delete
    void deleteUser(RecyclerItem items);
    @Update
    void updateUser(RecyclerItem items);
    @Query("SELECT * FROM items")
    List<RecyclerItem> getAllItems();
    @Query("SELECT pkinfo FROM items WHERE address = :address AND Time =:time")
    int getPk(String address, String time);
    @Query("SELECT pkinfo FROM items")
    int getPKINFO();

}

