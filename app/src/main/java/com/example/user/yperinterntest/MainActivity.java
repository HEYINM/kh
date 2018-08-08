package com.example.user.yperinterntest;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    TextView textView;
    RecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<RecyclerItem> items = new ArrayList<>();
    //final Context context = this;
    private RecyclerView recyclerView;
    private Userdao userdao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        recyclerView = findViewById(R.id.my_recycler_view);
        textView = findViewById(R.id.textView);
        userdao = AppDatabase.getInstance(this).userdao();
        setSupportActionBar(myToolbar);
        setRecyclerView();
        checkvisible();



    }


    private void setRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //items 는 arraylist
        adapter = new RecyclerAdapter(items);
        showdata();

       adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener(){

            @Override
            public void OnItemClick(View view,final RecyclerItem item){

                //AlertDialog 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                        .setMessage(item.getPkinfo()+"번을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        userdao.deleteUser(item);
                                        //DELETE가 왜 오류가 날까?
                                    }

                                });
                                items.remove(item);
                                adapter.notifyDataSetChanged();

                                //DELETE 오류가 난 이유 index가 업데이트 되지 않아서 오류가 남.
                                //삭제된 이후부터 업데이트 해도 되지만, 모두다 업데이트 하면 댐!

                                checkvisible();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    public void checkvisible(){


        if(adapter.getItemCount()>0) {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        else{
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);

        }
    }



    public boolean onCreateOptionsMenu(Menu menu){


        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu_main,menu);

        return true;

    }

    static final int REQUEST_ADD_ITEM = 1;

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_main:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent,REQUEST_ADD_ITEM);

                return true;

            default:
                super.onOptionsItemSelected(item);
        }


        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Check which request we're responding to
        if (requestCode == REQUEST_ADD_ITEM) {
            // Make sure the request was successful
            super.onActivityResult(requestCode,resultCode,data);

            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                    final int pkinfo = data.getIntExtra("pkinfo", 0);
                    final String address = data.getStringExtra("address");
                    String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());


                Log.d("pkinfo",""+pkinfo);
                Log.d("address",""+address);
                Log.d("today",""+today);

                final RecyclerItem temp = new RecyclerItem(pkinfo,address,today);

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        userdao.insertUser(temp);
                        temp.setPkinfo(userdao.getPk(temp.address,temp.Time));
                    }

                });
                adapter.notifyDataSetChanged();
                items.add(temp);
                checkvisible();

                /*Log.d("getPknum",""+temp.getPkinfo());
                Log.d("address",""+temp.getAddress());
                Log.d("today",""+temp.getTime());*/

                // Do something with the contact here (bigger example below)


            }
        }
    }

    public void showdata(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<RecyclerItem> list = userdao.getAllItems();
                adapter.setNewItems(list);
                adapter.notifyDataSetChanged();
                checkvisible();

            }
        });

    }

    private void addItem(RecyclerItem item) {

    }

}


