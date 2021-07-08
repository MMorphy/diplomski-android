package hr.petkovic.diplomski.android.data.API;

import java.util.List;

import hr.petkovic.diplomski.android.data.model.EntryType;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EntryTypesAPI {
    @GET("/api/types/income")
    Call<List<EntryType>> getIncomeTypes();

    @GET("/api/types/expense")
    Call<List<EntryType>> getExpenseTypes();

    interface GetIncomeTypesCallBack{
        void onResponse(boolean successful, String errorMsg, List<EntryType> incomeTypes);

        void onFailure(String cause);
    }

    interface GetExpenseTypesCallBack{
        void onResponse(boolean successful, String errorMsg, List<EntryType> expenseTypes);

        void onFailure(String cause);
    }
}
