package hr.petkovic.diplomski.android.data.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import hr.petkovic.diplomski.android.data.API.EntryTypesAPI;
import hr.petkovic.diplomski.android.data.model.EntryType;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetExpenseTypesController {
    EntryTypesAPI.GetExpenseTypesCallBack getExpenseTypesCallback;
    private static final String BASE_URL = "http://192.168.0.12:8080";

    public GetExpenseTypesController(EntryTypesAPI.GetExpenseTypesCallBack getExpenseTypesCallback) {
        this.getExpenseTypesCallback = getExpenseTypesCallback;
    }
    public void start(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        EntryTypesAPI typesAPI = retrofit.create(EntryTypesAPI.class);

        Call<List<EntryType>> call = typesAPI.getExpenseTypes();
        call.enqueue(new Callback<List<EntryType>>() {

            @Override
            public void onResponse(Call<List<EntryType>> call, Response<List<EntryType>> response) {
                if (response.isSuccessful()){
                    getExpenseTypesCallback.onResponse(true, null, response.body());
                } else {
                    getExpenseTypesCallback.onResponse(false, response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(Call<List<EntryType>> call, Throwable t) {
                getExpenseTypesCallback.onFailure(t.getCause().getMessage());
            }
        });
    }
}
