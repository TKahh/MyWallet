package tdtu.fit.tvka.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        checkBoxRememberMe = findViewById(R.id.rememberaccount);
        Button buttonLogin = findViewById(R.id.buttonlogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        //load saved account
        loadSavedCredentials();
    }



    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
        } else if (isValidCredentials(username, password)){
            //for login successful
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Save or clear credentials based on "Remember Me" checkbox state
            if (checkBoxRememberMe.isChecked()) {
                saveCredentials(username, password);
            } else {
                clearSavedCredentials();
            }
            Intent intent = new Intent(MainActivity.this, MoneyActivity.class);
            startActivity(intent);

            // Continue with your MainActivity logic here
        } else {
            // Invalid credentials
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isValidCredentials(String username, String password) {
        return username.equals("admin") && password.equals("password");
    }
    private void loadSavedCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");

        editTextUsername.setText(savedUsername);
        editTextPassword.setText(savedPassword);
        checkBoxRememberMe.setChecked(!savedUsername.isEmpty());
    }


    private void clearSavedCredentials() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    private void saveCredentials(String userID, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userID", userID);
        editor.putString("password", password);
        editor.apply();
    }



}