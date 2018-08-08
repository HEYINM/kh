package com.example.user.yperinterntest;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;



public class AddActivity extends MainActivity{

    RecyclerItem recyclerItem = new RecyclerItem();
    EditText address;
    Date mdate;
    int pk;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.datalist);

        //툴바(액션바) 추가
        Toolbar addToolbar = (Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(addToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_adddata,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_adddata:

                //pkinfo = (EditText)findViewById(R.id.pkinfo);
                pk = recyclerItem.pkinfo;
                address = (EditText)findViewById(R.id.address);
                mdate = new Date(System.currentTimeMillis());
                //pkinfo를 int로 받기위해 필요.
//               String pkstring = pkinfo.getText().toString();
                //if(pkinfo.length()<1)
                {
               //     Toast.makeText(getApplicationContext(),"숫자를 입력해주세요.",1).show();
                }
               // else {
                    //pkinfo를 int로 받기위해 필요.
//                    int pk = Integer.parseInt(pkstring);

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("pkinfo", pk);
                    intent.putExtra("address", address.getText().toString());
                    intent.putExtra("todaydate", mdate);
                    this.setResult(RESULT_OK,intent);

                    finish();

                    break;


               // }
               // return true;

            default:
                super.onOptionsItemSelected(item);
        }


        return false;
    }



}
