package sofie.accountregister;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by sofiekhullar on 2016-12-19.
 */
public class Step2 extends AppCompatActivity {

    // Variable declarations
    Button step2ButtonCreate, step2ButtonPrev;
    EditText step2EditTextUsername, step2EditTextPassword1, step2EditTextPassword2;
    ImageButton step2UsernameImageButton, step2PasswordImageButton1, step2PasswordImageButton2;
    String password, username;
    int passwordStrength;
    TextView step2PasswordStrength;
    boolean checkPassword1 = false;
    boolean checkPassword2 = false;
    boolean checkUsername = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        // Connect variables to the right component
        step2ButtonCreate = (Button) findViewById(R.id.step2ButtonCreate);
        step2ButtonPrev = (Button) findViewById(R.id.step2ButtonPrev);
        step2EditTextUsername = (EditText) findViewById(R.id.step2EditTextUsername);
        step2EditTextPassword1 = (EditText) findViewById(R.id.step2EditTextPassword1);
        step2EditTextPassword2 = (EditText) findViewById(R.id.step2EditTextPassword2);
        step2PasswordImageButton1 = (ImageButton) findViewById(R.id.step2InfoButtonPassword1);
        step2PasswordImageButton2 = (ImageButton) findViewById(R.id.step2InfoButtonPassword2);
        step2UsernameImageButton = (ImageButton) findViewById(R.id.step2InfoButtonUser);
        step2PasswordStrength = (TextView) findViewById(R.id.step2PasswordStrength);

        // make it impossible to move on to the next page
        step2ButtonCreate.setClickable(false);
        step2ButtonCreate.setTextColor(Color.GRAY);

        // add onclicklisterner to the create button. If pressed then start new activity
        step2ButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step2.this, LoginScreen.class);
                Step2.this.startActivity(myIntent);
            }
        });

        // add onclicklistener to the previous button so it lets you go back to the previous page
        step2ButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step2.this, Step1.class);
                Step2.this.startActivity(myIntent);
            }
        });

        // add textchangedlistener to username field so it is possible to see if somethings written there
        step2EditTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                username = step2EditTextUsername.getText().toString();

                // if something is written in the username field then change the icon on the right
                // and check if all fields are filled out correctly and see if it is possible to move
                // on to the next page
                if(step2EditTextUsername.getText().toString().length() > 0)
                {
                    step2UsernameImageButton.setBackgroundResource(R.drawable.check_good);
                    checkUsername = true;
                    readyToMoveOn();
                }
                // if the field is empty then change the icon to let the user know it's not filled out correctly
                else
                {
                    step2UsernameImageButton.setBackgroundResource(R.drawable.check_bad);
                    checkUsername = false;
                    readyToMoveOn();
                }
            }
        });

        // add textChangeListener to see what is written in the password field. Make sure it is longer than six characters.
        // If it meets all demands then change the icon on the right and see if it is possible to move on.
        // Depending on how strong the password is according to the algorithm write out different strings.
        step2EditTextPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                password = step2EditTextPassword1.getText().toString();

                if(passwordCheck(password)){
                    step2PasswordImageButton1.setBackgroundResource(R.drawable.info_good);
                    checkPassword1 = true;
                    readyToMoveOn();

                    // change tet color and string depending on password strength
                    if(passwordStrength < 20)
                    {
                        step2PasswordStrength.setText("WEAK");
                        step2PasswordStrength.setTextColor(Color.RED);
                    }
                    if(passwordStrength >= 20 && passwordStrength < 60)
                    {
                        step2PasswordStrength.setText("MEDIUM");
                        step2PasswordStrength.setTextColor(Color.YELLOW);
                    }
                    if(passwordStrength >= 60 && passwordStrength < 120)
                    {
                        step2PasswordStrength.setText("STRONG");
                        step2PasswordStrength.setTextColor(Color.GREEN);
                    }
                    if(passwordStrength >= 120)
                    {
                        step2PasswordStrength.setText("UNCRACKABLE");
                        step2PasswordStrength.setTextColor(Color.CYAN);
                    }
                }
                else{
                    step2PasswordImageButton1.setBackgroundResource(R.drawable.check_bad);
                    checkPassword1 = false;
                    readyToMoveOn();
                }

                if(password.equals(step2EditTextPassword2.getText().toString()))
                {
                    step2PasswordImageButton2.setBackgroundResource(R.drawable.check_good);
                    checkPassword2 = true;
                    readyToMoveOn();
                }
                else{
                    step2PasswordImageButton2.setBackgroundResource(R.drawable.check_bad);
                    checkPassword2 = false;
                    readyToMoveOn();
                }
            }
        });

        step2EditTextPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(step2EditTextPassword1.getText().toString().equals(step2EditTextPassword2.getText().toString()) && step2EditTextPassword2.getText().toString().length() > 5)
                {
                    step2PasswordImageButton2.setBackgroundResource(R.drawable.check_good);
                    checkPassword2 = true;
                    readyToMoveOn();
                }
                else{
                    step2PasswordImageButton2.setBackgroundResource(R.drawable.check_bad);
                    checkPassword2 = false;
                    readyToMoveOn();
                }
            }
        });
    }

    // Check if all fields are filled out correctly. If so then make it possible to click the create button
    public void readyToMoveOn() {

        if (checkPassword1 && checkUsername && checkPassword2)
        {
            step2ButtonCreate.setClickable(true);
            step2ButtonCreate.setTextColor(Color.BLACK);

        } else {
            step2ButtonCreate.setClickable(false);
            step2ButtonCreate.setTextColor(Color.GRAY);
        }

    }



    public boolean passwordCheck(String password){

        // make sure the password meets the required length
        if(password.length() < 6)
            return false;

        // declare variables that will determine password strength
        int lowerCase = 0;
        int upperCase = 0;
        int digits = 0;
        int special = 0;

        // loop through the password and count how many of the different kinds of characters are in it
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if(Character.isDigit(ch))
                digits = digits + 1;
            if(Character.isUpperCase(ch))
                upperCase = upperCase + 1;
            if(Character.isLowerCase(ch))
                lowerCase = lowerCase + 1;
            if(!Character.isLetterOrDigit(ch))
                special = special + 1;
        }

        // the ultimate password strength algorithm to check how secure it is.
        // Nothing big really, just multiply everything. Works perfectly.
        passwordStrength = (lowerCase + 1) * (upperCase + 1) * (digits + 1) * (special + 1);

        return true;
    }
}
