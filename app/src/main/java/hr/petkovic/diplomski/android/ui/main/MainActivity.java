package hr.petkovic.diplomski.android.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import hr.petkovic.diplomski.android.R;
import hr.petkovic.diplomski.android.ui.expense.ExpenseAddEditActivity;
import hr.petkovic.diplomski.android.ui.income.IncomeAddEditActivity;
import hr.petkovic.diplomski.android.ui.list.EntriesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void addIncome(View view){
        Intent intent = new Intent(this, IncomeAddEditActivity.class);
        startActivity(intent);
    }

    public void addExpense(View view){
        Intent intent = new Intent(this, ExpenseAddEditActivity.class);
        startActivity(intent);
    }

    public void showEntries(View view){
        Intent intent = new Intent(this, EntriesActivity.class);
        startActivity(intent);    }
}