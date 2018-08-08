package com.example.user.yperinterntest;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "items")
public class RecyclerItem {

    @PrimaryKey(autoGenerate = true)
    int pkinfo;

    String address;
    String Time;

    public RecyclerItem(String address, String time) {
        this.address = address;
        Time = time;
    }


    public int getPkinfo() {
        return pkinfo;
    }

    public void setPkinfo(int pkinfo) {
        this.pkinfo = pkinfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    public RecyclerItem(int pkinfo, String address, String time) {
        this.pkinfo = pkinfo;
        this.address = address;
        Time = time;
    }


    public RecyclerItem(){}


}


