package tdtu.fit.tvka.mywallet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
    Button buttonadd, buttonaddFund;
    static int currentMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyview = findViewById(R.id.MoneyView);
        buttonadd = findViewById(R.id.buttonAddExpenses);
        buttonaddFund = findViewById(R.id.buttonAddFunds);
        ImageView eyeIcon = findViewById(R.id.eyeIcon);
        updateMoneyDisplay();
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenList();
            }
        });


        //store original text
        String originalText = moneyview.getText().toString();

        eyeIcon.setOnClickListener(new View.OnClickListener() {
            boolean isTextVisible = true;

            @Override
            public void onClick(View view) {
                if (isTextVisible) {
                    moneyview.setText("****");
                    eyeIcon.setImageResource(R.drawable.ic_close);
                } else {
                    moneyview.setText(originalText);
                    eyeIcon.setImageResource(R.drawable.ic_eye);
                }

                // Toggle the visibility flag
                isTextVisible = !isTextVisible;
            }
        });

        buttonaddFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenMoneyDialog();
            }
        });

    }

    public static void updateMoneyDisplay() {
        moneyview.setText(currentMoney + " đ" );
    }


    private void OpenMoneyDialog(){
        AlertDialog.Builder fund = new AlertDialog.Builder(this);
        fund.setTitle("Add fund");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        fund.setView(input);

        fund.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String moneyString = input.getText().toString();
                if (!moneyString.isEmpty()) {
                    int FundAdd = Integer.parseInt(moneyString);
                    currentMoney += FundAdd;
                    updateMoneyDisplay();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter a valid amount", Toast.LENGTH_SHORT ).show();
                }
            }
        });

        fund.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        fund.show();
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
                    currentMoney += data.getExtras().getInt("Money");
                    updateMoneyDisplay();
                }
            }
        }
    }
}
