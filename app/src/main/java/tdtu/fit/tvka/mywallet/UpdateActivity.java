package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText expenses_input, date_input, amount_input;

    Button update_btn;

    String id, expenses, date;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        expenses_input = findViewById(R.id.categories_input2);
        date_input = findViewById(R.id.date_input2);
        amount_input = findViewById(R.id.amount_input2);

        update_btn = findViewById(R.id.btn_update);

        getAndSetIntentData();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                expenses = expenses_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                amount = amount_input.getText().toString().trim();
                myDB.updateData(id, expenses, amount, date);
            }
        });
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("expenses") &&
        getIntent().hasExtra("amount") && getIntent().hasExtra("date")) {
            //Get data
            id = getIntent().getStringExtra("id");
            expenses = getIntent().getStringExtra("expenses");
            date = getIntent().getStringExtra("date");
            amount = getIntent().getStringExtra("amount");
            //Set data
            expenses_input.setText(expenses);
            date_input.setText(date);
            amount_input.setText(amount);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}