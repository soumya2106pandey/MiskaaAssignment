package com.example.miskaaassignment.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class CountriesViewModel extends AndroidViewModel {

    public CountriesRepository countriesRepository;
    private final LiveData<List<Countries>> countriesList;

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        countriesRepository = new CountriesRepository(application);
        countriesList = countriesRepository.getAllCountries();
    }

    public LiveData<List<Countries>> getAllCountries(){
        return countriesList;
    }
    public void insert(Countries countries){
        countriesRepository.insert(countries);
    }
    public void delete(){
        countriesRepository.delete();
    }
}
