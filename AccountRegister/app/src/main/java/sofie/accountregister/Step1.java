package sofie.accountregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class Step1 extends AppCompatActivity {

    // Variables
    Button step1ButtonCreate, step1ButtonCancel;
    ImageButton step1InfoButtonName, step1InfoButtonEmail, step1InfoButtonGender, step1InfoButtonDate;
    EditText step1EditTextName,step1EditTextEmail,step1EditTextDD, step1EditTextMM, step1EditTextYYYY;
    Spinner step1SpinnerGender;
    boolean firstClick = false;
    boolean checkDD = false, checkMM = false, checkYYYY = false;
    boolean checkDate = false, checkName = false, checkEmail = false, checkGender = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        // Get all the components from the view
        step1ButtonCreate = (Button) findViewById(R.id.step1ButtonCreate);
        step1ButtonCancel = (Button) findViewById(R.id.step1ButtonCancel);
        step1InfoButtonName = (ImageButton) findViewById(R.id.step1InfoButtonName);
        step1InfoButtonEmail = (ImageButton) findViewById(R.id.step1InfoButtonEmail);
        step1InfoButtonGender = (ImageButton) findViewById(R.id.step1InfoButtonGender);
        step1InfoButtonDate = (ImageButton) findViewById(R.id.step1InfoButtonDate);
        step1EditTextName = (EditText) findViewById(R.id.step1EditTextName);
        step1EditTextEmail = (EditText) findViewById(R.id.step1EditTextEmail);
        step1EditTextDD = (EditText) findViewById(R.id.step1EditTextDD);
        step1EditTextMM = (EditText) findViewById(R.id.step1EditTextMM);
        step1EditTextYYYY = (EditText) findViewById(R.id.step1EditTextNameYYYY);
        step1SpinnerGender = (Spinner) findViewById(R.id.Step1SpinnerGender);

        // Set the next button unclickable so it's not possible to go to the next page
        step1ButtonCreate.setEnabled(false);

        // Call function for creating the gender spinner
        createSpinner();

        // Add listener on the editTextName to start validate the input when the text changes
        step1EditTextName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get the input from the editText
                String input = step1EditTextName.getText().toString();
                // Full name must be longer than 3 letters and contain one space
                if (input.length() > 2 && input.matches(".*\\s+.*")) {
                    // Change the info image to green if the input is correct
                    step1InfoButtonName.setBackgroundResource(R.drawable.check_good);
                    checkName = true;

                    readyToMoveOn();
                } else {
                    // Change the info image to red if the input is wrong
                    step1InfoButtonName.setBackgroundResource(R.drawable.check_bad);
                    checkName = false;
                }
            }
        });

        // Add listener on the EditTextEmail to start validate the input when the text changes
        step1EditTextEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get the input from the edittext
                String input = step1EditTextEmail.getText().toString();
                String part1, part2;

                // Email address must contain text@text.text
                // First check if the input contains a @, if it contains then split up the String and check for .
                if (input.contains("@")) {
                    String[] parts = input.split("@");
                    if (parts.length > 1) {
                        part1 = parts[0];
                        part2 = parts[1];

                        if (part2.contains(".")) {
                            String[] parts2 = input.split(".");
                            if (parts.length > 1) {
                                step1InfoButtonEmail.setBackgroundResource(R.drawable.check_good);
                                checkEmail = true;
                            } else {
                                step1InfoButtonEmail.setBackgroundResource(R.drawable.check_bad);
                                checkEmail = false;
                            }
                        } else {
                            step1InfoButtonEmail.setBackgroundResource(R.drawable.check_bad);
                            checkEmail = false;
                        }
                    } else {
                        step1InfoButtonEmail.setBackgroundResource(R.drawable.check_bad);
                        checkEmail = false;
                    }
                } else {
                    step1InfoButtonEmail.setBackgroundResource(R.drawable.check_bad);
                    checkEmail = false;
                }

                readyToMoveOn();
            }
        });

        // Add listener on the EditTextDD to start validate the input when the text changes
        step1EditTextDD.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get the text from the EditText
                String input = step1EditTextDD.getText().toString();

                // The date needs to be 2 numbers and between 1-31
                if (input.length() == 2)
                {
                    String[] parts = {input.substring(0, 1),input.substring(1)};
                    Log.d("PARTS", parts[0] + " " + parts[1]);
                    int first = Integer.parseInt(parts[0]);
                    int second = Integer.parseInt(parts[1]);

                    if(first < 3 || first == 3 && second == 0 || first == 3 && second == 1){
                         checkDD = true;
                    }
                    else{
                        checkDD = false;
                    }
                }
                else
                {
                    checkDD = false;
                }
                // Function for checking for all the parts of the dates
                checkDate();
            }
        });

        // Add listener on the EditTextMM to start validate the input when the text changes
        step1EditTextMM.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextMM.getText().toString();
                // The month needs to have 2 numbers and be between 1-12
                if (input.length() == 2) {
                    String[] parts = {input.substring(0, 1), input.substring(1)};

                    int first = Integer.parseInt(parts[0]);
                    int second = Integer.parseInt(parts[1]);

                    if (first < 1 || first == 1 && second <= 2) {
                        checkMM = true;
                    } else {
                        checkMM = false;
                    }
                } else {
                    checkMM = false;
                }
                checkDate();
            }
        });

        // Add listener on the EditTextMM to start validate the input when the text changes
        step1EditTextYYYY.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Get the text from the edittext
                String input = step1EditTextYYYY.getText().toString();
                // The year must consist of 4 numbers and we are just doing a simple check so it's between 1900-2090
                if (input.length() == 4) {
                    String[] parts = {input.substring(0, 1), input.substring(1, 2), input.substring(2, 3), input.substring(3, 4)};
                    Log.d("PARTS", parts[0] + " " + parts[1] + " " + parts[2] + " " + parts[3]);
                    int first = Integer.parseInt(parts[0]);
                    int second = Integer.parseInt(parts[1]);
                    int thrid = Integer.parseInt(parts[2]);
                    int fourth = Integer.parseInt(parts[3]);

                    if (first == 1 && second == 9 || first == 2 && second == 0) {
                        checkYYYY = true;
                    }
                    else {
                        checkYYYY = false;
                    }
                }
                else
                {
                    checkYYYY = false;
                }
            checkDate();
        }
    });


        step1ButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step1.this, Step2.class);
                Step1.this.startActivity(myIntent);
            }
        });

        step1ButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step1.this, LoginScreen.class);
                Step1.this.startActivity(myIntent);
            }
        });
    }

    // Function for creating a spinner
    public void createSpinner(){
        // Spinner Drop down elements
        final List<String> genders = new ArrayList<String>();
        genders.add("Choose gender");
        genders.add("Female");
        genders.add("Man");
        genders.add("other");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        step1SpinnerGender.setAdapter(dataAdapter);

        step1SpinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                // Change the color of the icon to green if the users chooses a gender
                if (position != 0 && firstClick)
                {
                    step1InfoButtonGender.setBackgroundResource(R.drawable.check_good);
                    checkGender = true;
                }
                else if (position == 0 && firstClick)
                {
                    step1InfoButtonGender.setBackgroundResource(R.drawable.check_bad);
                    checkGender = false;
                }
                readyToMoveOn();
                firstClick = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                step1InfoButtonGender.setBackgroundResource(R.drawable.check);
            }
        });
    }

    // Function for checking if all the parts of the date are correct
    public void checkDate(){
        Log.d("ready", String.valueOf(checkDD + " " + checkMM + " " + checkYYYY));
        if(checkDD && checkMM && checkYYYY)
        {
            checkDate = true;
            step1InfoButtonDate.setBackgroundResource(R.drawable.check_good);
        }
        else
        {
            step1InfoButtonDate.setBackgroundResource(R.drawable.check_bad);
            checkDate = false;
        }
        readyToMoveOn();
    }

    // Function to check if all the fields are field in correct
    public void readyToMoveOn(){
        if(checkDate && checkName && checkEmail && checkGender){
            step1ButtonCreate.setEnabled(true);
        }
    }
}
