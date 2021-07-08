package hr.petkovic.diplomski.android.data.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.petkovic.diplomski.android.data.API.EntriesAPI;
import hr.petkovic.diplomski.android.data.model.Entry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertEntryController {
    private EntriesAPI.InsertEntryCallBack insertEntryCallback;
    private static final String BASE_URL = "http://192.168.0.12:8080";

    public InsertEntryController(EntriesAPI.InsertEntryCallBack insertEntryCallback) {
        this.insertEntryCallback = insertEntryCallback;
    }

    public void start(Entry entry) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EntriesAPI entriesApi = retrofit.create(EntriesAPI.class);

        Call<Entry> call = entriesApi.createEntry(entry);
        call.enqueue(new Callback<Entry>() {
            @Override
            public void onResponse(Call<Entry> call, Response<Entry> response) {
                if (response.isSuccessful()) {
                    insertEntryCallback.onResponse(true, null, response.body());
                } else {
                    insertEntryCallback.onResponse(false, response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(Call<Entry> call, Throwable t) {
                insertEntryCallback.onFailure(t.getCause().toString());
            }
        });
    }
}
