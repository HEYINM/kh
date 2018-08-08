package com.example.user.yperinterntest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {RecyclerItem.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{
    public abstract Userdao userdao();

    private static volatile AppDatabase appDatabase;


   static synchronized AppDatabase getInstance(Context context){
       if(appDatabase == null){
           appDatabase = create(context);
       }
       return appDatabase;

   }

   private static AppDatabase create(final Context context){
       return Room.databaseBuilder(context,AppDatabase.class,"UserDatabase.db")
               .fallbackToDestructiveMigration()
               .build();
   }


}
