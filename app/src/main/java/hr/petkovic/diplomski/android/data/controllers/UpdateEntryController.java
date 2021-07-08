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

public class UpdateEntryController {
    private EntriesAPI.UpdateEntryCallBack updateEntryCallback;
    private static final String BASE_URL = "http://192.168.0.12:8080";

    public UpdateEntryController(EntriesAPI.UpdateEntryCallBack updateEntryCallback) {
        this.updateEntryCallback = updateEntryCallback;
    }

    public void start(Long id, Entry entry) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EntriesAPI entriesApi = retrofit.create(EntriesAPI.class);

        Call<Void> call = entriesApi.updateEntry(id, entry);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    updateEntryCallback.onResponse(true, null);
                } else {
                    updateEntryCallback.onResponse(false, null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateEntryCallback.onFailure(t.getCause().toString());

            }
        });
    }
}
