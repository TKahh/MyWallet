package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.InputDevice;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView moneyview;
    Button buttonaddmoney;
    int currentMoney = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyview = findViewById(R.id.MoneyView);
        buttonaddmoney = findViewById(R.id.buttonAddMoney);

        updateMoneyDisplay();
        buttonaddmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMoneyDialog();
            }
        });

    }

    private void updateMoneyDisplay() {
        moneyview.setText(currentMoney + "đ" );
    }

    private void showAddMoneyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add money");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String  moneyString = input.getText().toString();

                if(!moneyString.isEmpty()){
                    int MoneyAdd = Integer.parseInt(moneyString);
                    currentMoney += MoneyAdd;
                    updateMoneyDisplay();
                }
                else{
                    Toast.makeText(MainActivity.this, "Please enter a valid amount", Toast.LENGTH_SHORT ).show();
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
