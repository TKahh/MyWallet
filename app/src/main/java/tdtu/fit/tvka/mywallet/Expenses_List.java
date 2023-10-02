package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expenses_List extends AppCompatActivity {

    String[] expenses = new String[]{"Food and Drink", "Commune", "Rent", "Water Bill", "Electric Bill"};
    List<String> Expense_List = new ArrayList<String>(Arrays.asList(expenses));
    ArrayAdapter<String> adapter;
    ListView listview;
    int currentMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        listview = findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,expenses);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posistion, long id) {
                showAddMoneyDialog();
            }
        });
    }


    private void showAddMoneyDialog() {
        Intent intent_money = new Intent(Expenses_List.this,MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add money");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String moneyString = input.getText().toString();
                if (!moneyString.isEmpty()) {
                    int MoneyAdd = Integer.parseInt(moneyString);
                    currentMoney += MoneyAdd;
                    intent_money.putExtra("Money",MoneyAdd);
                    setResult(RESULT_OK,intent_money);
                    finish();
                } else {
                    Toast.makeText(Expenses_List.this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
}
