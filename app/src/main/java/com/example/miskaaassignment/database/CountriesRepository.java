package com.example.miskaaassignment.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CountriesRepository {

    private final CountriesDao countriesDao;
    private final LiveData<List<Countries>> countriesList;

    public CountriesRepository(Application application){
        CountriesRoomDatabase db = CountriesRoomDatabase.getDatabase(application);
        countriesDao = db.getDao();
        countriesList = countriesDao.getAllCountries();
    }

    public LiveData<List<Countries>> getAllCountries(){
        return countriesList;
    }

    void insert(Countries countries){
        CountriesRoomDatabase.databaseWriteExecutor.execute(() -> {
            countriesDao.insert(countries);
        });
    }

    void delete(){
        CountriesRoomDatabase.databaseWriteExecutor.execute(() ->{
            countriesDao.deleteAll(countriesList.getValue());
        });

    }
}
