package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Expenses_List extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    String[] expenses = new String[]{"Food and Drink", "Commute", "Rent", "Water Bill", "Electric Bill", "Income"};
    int currentMoney = 0;

    EditText editAddFund;

    Button add, datePickerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        editAddFund = findViewById(R.id.amountEdit);
        initDatePicker();
        datePickerbtn = findViewById(R.id.btnDatePickerExpenses);
        datePickerbtn.setText(getTodayDate());

        datePickerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        Intent intent_expense = new Intent(Expenses_List.this, MainActivity.class);

        add = findViewById(R.id.btnAddExpense);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String moneyAdd = editAddFund.getText().toString();
                if (!moneyAdd.isEmpty()) {
                    int money = Integer.parseInt(moneyAdd);
                    Spinner expense = findViewById(R.id.expensesSpinner);
                    String selectedCategory = expenses[expense.getSelectedItemPosition()];

                    if (selectedCategory.equals("Income")) {
                        currentMoney += money;
                    } else {
                        currentMoney -= money;
                    }

                    intent_expense.putExtra("Expense", currentMoney);
                    setResult(RESULT_OK, intent_expense);
                    finish();
                } else {
                    Toast.makeText(Expenses_List.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Spinner expense = findViewById(R.id.expensesSpinner);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, R.layout.expenses_spinner_list, expenses);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        expense.setAdapter(aa);
        expense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), expenses[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
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
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }
}