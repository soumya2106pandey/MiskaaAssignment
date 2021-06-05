package com.example.miskaaassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Countries country);

    @Delete()
    void deleteAll(List<Countries> countryList);

    @Query("SELECT * FROM countries_table ORDER BY id ASC")
    LiveData<List<Countries>> getAllCountries();
}
