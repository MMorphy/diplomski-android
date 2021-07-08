package hr.petkovic.diplomski.android.data.API;

import java.util.List;

import hr.petkovic.diplomski.android.data.model.Entry;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EntriesAPI {

    @GET("/api/entry/{username}")
    Call<List<Entry>> getEntriesForUser(@Path(value = "username", encoded = false) String username);

    @POST("/api/entry")
    Call<Entry> createEntry(@Body Entry entry);

    @PUT("/api/entry/{id}")
    Call<Void> updateEntry(@Path(value = "id", encoded = false) Long id, @Body Entry entry);

    @DELETE("/api/entry/{id}")
    Call<Void> deleteEntry(@Path(value = "id", encoded = false) Long id);

    interface GetEntriesCallBack {
        void onResponse(boolean successful, String errorMsg, List<Entry> entries);

        void onFailure(String cause);
    }

    interface InsertEntryCallBack {
        void onResponse(boolean successful, String errorMsg, Entry entry);

        void onFailure(String cause);
    }

    interface UpdateEntryCallBack {
        void onResponse(boolean successful, String errorMsg);

        void onFailure(String cause);

    }

    interface DeleteEntryCallBack {
        void onResponse(boolean successful, String errorMsg);

        void onFailure(String cause);
    }
}
