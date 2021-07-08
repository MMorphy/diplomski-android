package hr.petkovic.diplomski.android.ui.income;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import hr.petkovic.diplomski.android.R;
import hr.petkovic.diplomski.android.data.API.EntriesAPI;
import hr.petkovic.diplomski.android.data.API.EntryTypesAPI;
import hr.petkovic.diplomski.android.data.controllers.GetExpenseTypesController;
import hr.petkovic.diplomski.android.data.controllers.GetIncomeTypesController;
import hr.petkovic.diplomski.android.data.controllers.UpdateEntryController;
import hr.petkovic.diplomski.android.data.model.Entry;
import hr.petkovic.diplomski.android.data.model.EntryType;
import hr.petkovic.diplomski.android.data.model.User;
import hr.petkovic.diplomski.android.ui.expense.ExpenseEditActivity;
import hr.petkovic.diplomski.android.ui.main.MainActivity;

public class IncomeEditActivity extends AppCompatActivity {
    private EditText amount;
    private EditText description;
    private Spinner spinner;
    private EntryType previousEntryType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Entry originalEntry = (Entry) getIntent().getSerializableExtra("entry");
        previousEntryType = originalEntry.getType();
        GetIncomeTypesController typesController = new GetIncomeTypesController(incomeTypesCallBack);
        typesController.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_edit);
        amount = findViewById(R.id.amountAdd);
        amount.setText(originalEntry.getAmount().toString());
        description = findViewById(R.id.descriptionAdd);
        description.setText(originalEntry.getDescription());
        spinner = findViewById(R.id.spinner_expense);
        final Button submitButton = (Button) findViewById(R.id.button_submit_expense_add);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEntryController entryController = new UpdateEntryController(updateEntryCallBack);
                Entry entry = new Entry();
                entry.setAmount(Long.parseLong(amount.getText().toString()));
                entry.setDescription(description.getText().toString());
                entry.setType((EntryType) spinner.getSelectedItem());
                //TODO izvuci logganog usera
                entry.setCreatedBy(new User(1L, "test", "test", true));
                entryController.start(originalEntry.getId(), entry);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    EntryTypesAPI.GetIncomeTypesCallBack incomeTypesCallBack = new EntryTypesAPI.GetIncomeTypesCallBack() {
        @Override
        public void onResponse(boolean successful, String errorMsg, List<EntryType> incomeTypes) {
            ArrayAdapter spinnerArrayAdapter;
            if (successful) {
                spinnerArrayAdapter = new ArrayAdapter(IncomeEditActivity.this, android.R.layout.simple_spinner_item, incomeTypes.toArray());
            } else {
                spinnerArrayAdapter = new ArrayAdapter(IncomeEditActivity.this, android.R.layout.simple_spinner_item, new EntryType[0]);
            }
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setSelection(spinnerArrayAdapter.getPosition(previousEntryType));
        }

        @Override
        public void onFailure(String cause) {
            Log.d("TAG", "It failed.");
            Toast.makeText(IncomeEditActivity.this, cause, Toast.LENGTH_LONG).show();
        }
    };
    EntriesAPI.UpdateEntryCallBack updateEntryCallBack = new EntriesAPI.UpdateEntryCallBack() {
        @Override
        public void onResponse(boolean successful, String errorMsg) {
            if (successful) {
                Toast.makeText(IncomeEditActivity.this, "Entry successfully inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(IncomeEditActivity.this, "Invalid entry!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(String cause) {
            Log.d("TAG", "It failed.");
            Toast.makeText(IncomeEditActivity.this, cause, Toast.LENGTH_LONG).show();
        }
    };

    public void cancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}