package com.example.miskaaassignment.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Countries_table")
public class Countries {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name, capital, flagImage, region, subregion;
    public int population;
    public String borders, languages;

    public Countries(String name, String capital, String flagImage, String region, String subregion, int population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flagImage = flagImage;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }
}