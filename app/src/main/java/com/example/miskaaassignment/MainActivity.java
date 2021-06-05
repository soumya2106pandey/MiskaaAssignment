package com.example.miskaaassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.miskaaassignment.database.Countries;
import com.example.miskaaassignment.database.CountriesViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button deleteButton, refreshButton;
    CountriesAdapter adapter;
    CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CountriesAdapter(this);
        recyclerView.setAdapter(adapter);

        viewModel.getAllCountries().observe(this, countries -> {
            if (countries != null) {
                adapter.updateCountries(new ArrayList(countries));
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
                Toast.makeText(MainActivity.this, "Refreshing", Toast.LENGTH_SHORT).show();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete();
                Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void init() {
        recyclerView = findViewById(R.id.recyclerView);
        deleteButton = findViewById(R.id.deleteButton);
        refreshButton = findViewById(R.id.refreshButton);
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(CountriesViewModel.class);
    }

    private void fetchData() {
        String url = "https://restcountries.eu/rest/v2/region/asia";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String borders = "", languages = "";
                                JSONArray borderArray = jsonObject.getJSONArray("borders");
                                JSONArray languageArray = jsonObject.getJSONArray("languages");
                                for (int j = 0; j < borderArray.length(); j++) {
                                    if (j != borderArray.length() - 1)
                                        borders += borderArray.getString(j) + " , ";
                                    else {
                                        borders += borderArray.getString(j);
                                    }
                                }
                                for (int k = 0; k < languageArray.length(); k++) {
                                    if (k != languageArray.length() - 1) {
                                        JSONObject languageObject = languageArray.getJSONObject(k);
                                        languages += languageObject.getString("name") + " , ";
                                    } else {
                                        JSONObject languageObject = languageArray.getJSONObject(k);
                                        languages += languageObject.getString("name");
                                    }
                                }

                                Countries countries = new Countries(
                                        jsonObject.getString("name"),
                                        jsonObject.getString("capital"),
                                        jsonObject.getString("flag"),
                                        jsonObject.getString("region"),
                                        jsonObject.getString("subregion"),
                                        jsonObject.getInt("population"),
                                        borders,
                                        languages);

                                viewModel.insert(countries);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error fetching details", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}