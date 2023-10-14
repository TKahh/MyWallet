package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;
public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> expense_id, categories, date, amount;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(HistoryActivity.this);
        expense_id = new ArrayList<>();
        categories = new ArrayList<>();
        date = new ArrayList<>();
        amount = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HistoryActivity.this, expense_id, categories, date, amount);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
    }
    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()) {
                expense_id.add(cursor.getString(0));
                categories.add(cursor.getString(1));
                date.add(cursor.getString(2));
                amount.add(cursor.getString(3));
            }
        }
    }
}