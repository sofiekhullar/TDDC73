package sofie.accountregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginScreen extends AppCompatActivity {

    // Login
    Button loginButton, loginRegisterButton;
    EditText loginEditTextUsername, loginEditTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Login
        loginButton = (Button) findViewById(R.id.loginButton);
        loginRegisterButton = (Button) findViewById(R.id.createButton);
        loginEditTextUsername = (EditText) findViewById(R.id.loginEditTestUsername);
        loginEditTextPassword = (EditText) findViewById(R.id.loginEditTextPass);

        loginButton.setEnabled(false);

        loginRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginScreen.this, Step1.class);
                LoginScreen.this.startActivity(myIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });
    }
}
