package com.example.mvvmgithub.Data.Database.Daos;


import com.example.mvvmgithub.Data.Database.Entities.UserEntity;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM USERENTITY WHERE search_query = :search_query")
    UserEntity getUserFromSearchQuery(String search_query);
}
