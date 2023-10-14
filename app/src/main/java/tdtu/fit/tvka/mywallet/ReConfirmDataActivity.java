package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ReConfirmDataActivity extends AppCompatActivity {
    EditText category, amount, date;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_confirm_data);

        category = findViewById(R.id.categories_input);
        amount = findViewById(R.id.amount_input);
        add = findViewById(R.id.btn_add);
        date = findViewById(R.id.date_input);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredDate = date.getText().toString().trim();

                if (!enteredDate.isEmpty()) {
                    try {
                        SimpleDateFormat inputDateFormat =
                                new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat outputDateFormat =
                                new SimpleDateFormat("dd/MM/yyyy");

                        Date parsedDate = inputDateFormat.parse(enteredDate);
                        String formattedDate = outputDateFormat.format(parsedDate);

                        MyDatabaseHelper myDB = new MyDatabaseHelper(
                                ReConfirmDataActivity.this);
                        myDB.addHistory(category.getText().toString().trim(),
                                Integer.valueOf(amount.getText().toString().trim()),
                                formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(ReConfirmDataActivity.this,
                                "Invalid date format.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ReConfirmDataActivity.this,
                            "Date is required.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}