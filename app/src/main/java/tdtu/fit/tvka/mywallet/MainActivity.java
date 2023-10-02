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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
                OpenList();
            }
        });

    }

    private void updateMoneyDisplay() {
        moneyview.setText(currentMoney + "đ" );
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
