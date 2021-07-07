package hr.petkovic.diplomski.android.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;


import hr.petkovic.diplomski.android.R;
import hr.petkovic.diplomski.android.data.model.Entry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GetEntriesController implements Callback<List<Entry>> {
    private static final String BASE_URL = "https://192.168.0.12:8080";
    public void start(String username){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        API api = retrofit.create(API.class);

        Call<List<Entry>> call = api.getEntriesForUser(username);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
        if(response.isSuccessful()){
            List<Entry> entries = response.body();
            entries.forEach(entry -> System.out.println(entry));
        } else {
            System.out.println("Error");
        }
    }

    @Override
    public void onFailure(Call<List<Entry>> call, Throwable t) {

    }
}
