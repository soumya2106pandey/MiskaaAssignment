package com.example.miskaaassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miskaaassignment.database.Countries;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private final ArrayList<Countries> countriesList = new ArrayList<>();
    private final Context context;

    public CountriesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_countries_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Countries country = countriesList.get(position);
        holder.getName().setText(country.name);
        holder.getCapital().setText(country.capital);
        holder.getRegion().setText(country.region);
        holder.getSubregion().setText(country.subregion);
        holder.getPopulation().setText(String.valueOf(country.population));
        holder.getBorder().setText(country.borders);
        holder.getLanguage().setText(country.languages);
        Utils.fetchSvg(context, country.flagImage, holder.getFlagImage());
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    void updateCountries(ArrayList<Countries> updatedCountriesList) {
        countriesList.clear();
        countriesList.addAll(updatedCountriesList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name, capital, region, subregion, population, border, language;
        private final ImageView flagImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.country_capital);
            region = itemView.findViewById(R.id.country_region);
            subregion = itemView.findViewById(R.id.country_subregion);
            population = itemView.findViewById(R.id.country_population);
            border = itemView.findViewById(R.id.country_borders);
            language = itemView.findViewById(R.id.country_languages);
            flagImage = itemView.findViewById(R.id.country_flag_image);
        }

        public TextView getName() {
            return name;
        }

        public TextView getCapital() {
            return capital;
        }

        public TextView getRegion() {
            return region;
        }

        public TextView getSubregion() {
            return subregion;
        }

        public TextView getPopulation() {
            return population;
        }

        public TextView getBorder() {
            return border;
        }

        public TextView getLanguage() {
            return language;
        }

        public ImageView getFlagImage() {
            return flagImage;
        }
    }
}