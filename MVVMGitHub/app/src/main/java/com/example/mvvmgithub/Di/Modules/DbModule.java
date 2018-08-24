package com.example.mvvmgithub.Di.Modules;


import android.content.Context;

import com.example.mvvmgithub.Data.Database.Databases.MvvmDB;
import com.example.mvvmgithub.Di.Scopes.DbScope;
import com.example.mvvmgithub.Misc.Constants;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import dagger.Module;
import dagger.Provides;

//@Module(includes = ContextModule.class)
//public abstract class DbModule<T extends RoomDatabase> {
//
//    private Class<T> db_class;
//    private String db_name;
//
//    public DbModule(Class<T> db_class, String db_name) {
//        this.db_class = db_class;
//        this.db_name = db_name;
//    }
//
//    @Provides
//    T getDbClass(Context context) {
//        return (T) (Room.databaseBuilder(context, db_class, db_name).build());
//    }
//}


@Module(includes = ContextModule.class)
public class DbModule {

    @Provides
    @DbScope
    MvvmDB getDbClass(Context context) {
        return Room.databaseBuilder(context, MvvmDB.class, Constants.DB.DB_NAME).build();
    }
}