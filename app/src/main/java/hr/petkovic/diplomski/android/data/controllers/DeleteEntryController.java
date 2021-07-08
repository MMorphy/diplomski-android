package hr.petkovic.diplomski.android.data.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hr.petkovic.diplomski.android.data.API.EntriesAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteEntryController {
    EntriesAPI.DeleteEntryCallBack deleteEntriesCallback;
    private static final String BASE_URL = "http://192.168.0.12:8080";

    public DeleteEntryController(EntriesAPI.DeleteEntryCallBack deleteEntriesCallback) {
        this.deleteEntriesCallback = deleteEntriesCallback;
    }

    public void start(Long id) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EntriesAPI entriesApi = retrofit.create(EntriesAPI.class);

        Call<Void> call = entriesApi.deleteEntry(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    deleteEntriesCallback.onResponse(true, null);
                } else {
                    deleteEntriesCallback.onResponse(false, null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteEntriesCallback.onFailure(t.getCause().toString());
            }
        });
    }
}
