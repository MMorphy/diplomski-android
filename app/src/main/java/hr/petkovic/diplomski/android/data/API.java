package hr.petkovic.diplomski.android.data;

import java.util.List;

import hr.petkovic.diplomski.android.data.model.Entry;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @GET("/api/entry/{username}")
    Call<List<Entry>> getEntriesForUser(@Path(value = "username", encoded = false) String username);

    @POST("/api/entry")
    Call<Void> createEntry(@Body Entry entry);

    @PUT("/api/entry/{id}")
    Call<Void> updateEntry(@Path(value = "id", encoded = false) Long id, @Body Entry entry);

    @DELETE("/api/entry/{id}")
    Call<Void> deleteEntry(@Path(value = "id", encoded = false) Long id);
}
