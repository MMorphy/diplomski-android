package hr.petkovic.diplomski.android.ui.list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;

import hr.petkovic.diplomski.android.R;
import hr.petkovic.diplomski.android.data.API.EntriesAPI;
import hr.petkovic.diplomski.android.data.controllers.DeleteEntryController;
import hr.petkovic.diplomski.android.data.controllers.GetEntriesController;
import hr.petkovic.diplomski.android.data.model.Entry;
import hr.petkovic.diplomski.android.ui.expense.ExpenseAddActivity;
import hr.petkovic.diplomski.android.ui.expense.ExpenseEditActivity;
import hr.petkovic.diplomski.android.ui.income.IncomeAddActivity;
import hr.petkovic.diplomski.android.ui.income.IncomeEditActivity;

public class EntriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);
        GetEntriesController controller = new GetEntriesController(entriesCallBack);
        controller.start("test");
    }

    EntriesAPI.GetEntriesCallBack entriesCallBack = new EntriesAPI.GetEntriesCallBack() {
        @Override
        public void onResponse(boolean successful, String errorMsg, List<Entry> entries) {
            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            if (successful) {
                TableLayout tl = findViewById(R.id.entries_table);
                int i =1;
                for (Entry e : entries) {
                    TableRow tr = new TableRow(EntriesActivity.this);
                    TextView date = new TextView(EntriesActivity.this);
                    date.setText(dateFormat.format(e.getCreateDate()));
                    date.setPaddingRelative(10,0,10,0);
                    date.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    TextView amount = new TextView(EntriesActivity.this);
                    amount.setText(e.getAmount().toString() + '$');
                    amount.setPaddingRelative(10,0,10,0);
                    amount.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    TextView type = new TextView(EntriesActivity.this);
                    type.setText(e.getType().getMainType() + "-" + e.getType().getSubType());
                    type.setPaddingRelative(10,0,10,0);
                    type.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                    TextView desc = new TextView(EntriesActivity.this);
                    desc.setText(e.getDescription());
                    desc.setPaddingRelative(10,0,10,10);
                    desc.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                    Button edit = new Button(EntriesActivity.this);
                    edit.setText(R.string.button_edit);
                    edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent;
                            if (isIncome(e)) {
                                intent = new Intent(v.getContext(), IncomeEditActivity.class);
                            } else{
                                intent = new Intent(v.getContext(), ExpenseEditActivity.class);
                            }
                            intent.putExtra("entry", e);
                            startActivity(intent);
                        }
                    });
                    Button delete = new Button(EntriesActivity.this);
                    delete.setText(R.string.button_delete);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DeleteEntryController controller = new DeleteEntryController(deleteEntryCallBack) ;
                            controller.start(e.getId());
                            finish();
                            startActivity(getIntent());
                        }
                    });
                    tr.addView(date);
                    tr.addView(amount);
                    tr.addView(type);
                    tr.addView(desc);
                    tr.addView(edit);
                    tr.addView(delete);
                    tl.addView(tr,i);
                    i++;
                }
            }
        }

        @Override
        public void onFailure(String cause) {

        }
    };

    EntriesAPI.DeleteEntryCallBack deleteEntryCallBack = new EntriesAPI.DeleteEntryCallBack(){
        @Override
        public void onResponse(boolean successful, String errorMsg) {
            Toast.makeText(EntriesActivity.this, "Entry successfully deleted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(String cause) {
            Log.d("TAG", "It failed.");
            Toast.makeText(EntriesActivity.this, cause, Toast.LENGTH_LONG).show();
        }
    };
    private boolean isIncome(Entry e) {
        if (e.getType().getMainType().equals("Income"))
            return true;
        return false;
    }
}