package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddFund extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    int currentMoney =  0;

    EditText editAddFund;

    Button add, datePickerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fund);
        editAddFund = findViewById(R.id.amountEdit);

        initDatePicker();
        datePickerbtn = findViewById(R.id.btnDatePicker);
        datePickerbtn.setText(getTodayDate());

        datePickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        Intent intent_fund = new Intent(AddFund.this,MainActivity.class);

        add = findViewById(R.id.btnAddFund);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String moneyAdd = editAddFund.getText().toString();
                if(!moneyAdd.isEmpty()){
                    int money = Integer.parseInt(moneyAdd);
                    currentMoney += money;
                    intent_fund.putExtra("Expense", 0);
                    intent_fund.putExtra("Income", currentMoney);
                    setResult(RESULT_OK,intent_fund);
                    finish();
                }
                else{
                    Toast.makeText(AddFund.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String getTodayDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePickerbtn.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = android.R.style.Theme_Holo_Light_Dialog;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";
        return "JAN";
    }

}