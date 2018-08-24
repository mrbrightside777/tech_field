package com.example.mvvmgithub.Data.Database.Daos;


import com.example.mvvmgithub.Data.Database.Entities.RepoListEntity;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface RepoListDao {

    @Query("SELECT * FROM REPOLISTENTITY WHERE user_name = :user_name")
    RepoListEntity getUserRepoList(String user_name);

}
