package tdtu.fit.tvka.mywallet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;
public class HistoryActivity extends AppCompatActivity {

    ImageView empty_image_view;
    TextView no_data_text;
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ArrayList<String> expense_id, categories, date, amount;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        empty_image_view = findViewById(R.id.empty_image_view);
        no_data_text = findViewById(R.id.no_data_text);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }
    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            empty_image_view.setVisibility(View.VISIBLE);
            no_data_text.setVisibility(View.VISIBLE);
        } else{
            while (cursor.moveToNext()) {
                expense_id.add(cursor.getString(0));
                categories.add(cursor.getString(1));
                date.add(cursor.getString(2));
                amount.add(cursor.getString(3));
            }
            empty_image_view.setVisibility(View.GONE);
            no_data_text.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure to delete All data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(HistoryActivity.this);
                myDB.deleteAllData();
                Intent intent  = new Intent(HistoryActivity.this, HistoryActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}