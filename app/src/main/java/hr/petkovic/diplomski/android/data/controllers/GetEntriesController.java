package hr.petkovic.diplomski.android.data.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;


import hr.petkovic.diplomski.android.data.API.EntriesAPI;
import hr.petkovic.diplomski.android.data.model.Entry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetEntriesController {
    EntriesAPI.GetEntriesCallBack getEntriesCallback;
    private static final String BASE_URL = "http://192.168.0.12:8080";

    public GetEntriesController(EntriesAPI.GetEntriesCallBack getEntriesCallback) {
        this.getEntriesCallback = getEntriesCallback;
    }

    public void start(String username) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EntriesAPI entriesApi = retrofit.create(EntriesAPI.class);

        Call<List<Entry>> call = entriesApi.getEntriesForUser(username);
        call.enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                if (response.isSuccessful()) {
                    getEntriesCallback.onResponse(true, null, response.body());
                } else {
                    getEntriesCallback.onResponse(false, response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {
                getEntriesCallback.onFailure(t.getCause().getMessage());

            }
        });
    }
}
