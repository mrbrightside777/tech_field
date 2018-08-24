package com.example.mvvmgithub.Data.Database.Databases;


import com.example.mvvmgithub.Data.Database.Daos.RepoListDao;
import com.example.mvvmgithub.Data.Database.Daos.UserDao;
import com.example.mvvmgithub.Data.Database.Entities.RepoListEntity;
import com.example.mvvmgithub.Data.Database.Entities.UserEntity;
import com.example.mvvmgithub.Misc.Constants;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, RepoListEntity.class}, version = Constants.DB.VERSION_NUMBER, exportSchema = false)
public abstract class MvvmDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RepoListDao repoListDao();
}
