package tdtu.fit.tvka.mywallet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.InputDevice;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static TextView moneyview;
    Button buttonaddIncome;
    static int currentMoney = 0;

    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyview = findViewById(R.id.MoneyView);
//        buttonadd = findViewById(R.id.buttonAddExpenses);
        buttonaddIncome = findViewById(R.id.buttonAddIncome);
        ImageView eyeIcon = findViewById(R.id.eyeIcon);
        Button Chart = findViewById(R.id.barchart);
        updateMoneyDisplay();

        buttonaddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenList();
            }
        });

        Chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenInsight();
            }
        });


        eyeIcon.setOnClickListener(new View.OnClickListener() {
            boolean isTextVisible = true;
            String originalText = moneyview.getText().toString();
            @Override
            public void onClick(View view) {
                if (isTextVisible) {
                    moneyview.setText("******* đ");
                    eyeIcon.setImageResource(R.drawable.ic_close);

                } else {
                    String formattedMoney = String.format("%,d", currentMoney);
                    moneyview.setText(String.valueOf(formattedMoney) + " đ" );
                    eyeIcon.setImageResource(R.drawable.ic_eye);

                }

                // Toggle the visibility flag
                isTextVisible = !isTextVisible;
            }
        });

//        buttonaddFund.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                OpenMoneyAdd();
//            }
//        });

    }

    public static void updateMoneyDisplay() {
        String formattedMoney = String.format("%,d", currentMoney);
        moneyview.setText(formattedMoney + " đ" );
    }

//    private void OpenMoneyAdd() {
//        Intent intent = new Intent(this, AddFund.class);
//        startActivityForResult(intent, 1);
//    }

    private void OpenInsight(){
        Intent intent = new Intent(this, InsightView.class);
        startActivityForResult(intent,1);
    }

    private void OpenList(){
        Intent intent = new Intent(this, Expenses_List.class);
        startActivityForResult(intent,1);
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 1){
                if(data!= null){
                    currentMoney += data.getIntExtra("Expense", 0);
                    currentMoney += data.getIntExtra("Income",0);
                    updateMoneyDisplay();
                }
            }
        }
    }
}
