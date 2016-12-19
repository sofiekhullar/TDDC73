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

/**
 * Created by sofiekhullar on 2016-12-19.
 */
public class Step1 extends AppCompatActivity {

    // Step1
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

        // Step1
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

        step1ButtonCreate.setEnabled(false);
        createSpinner();


        step1EditTextName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextName.getText().toString();
                // Full name must be longer than 3 letters and contain one space
                if (input.length() > 2 && input.matches(".*\\s+.*")) {
                    step1InfoButtonName.setBackgroundResource(R.drawable.info_good);
                    checkName = true;
                    readyToMoveOn();
                } else {
                    step1InfoButtonName.setBackgroundResource(R.drawable.info_bad);
                    checkName = false;
                }

            }
        });

        step1EditTextEmail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextEmail.getText().toString();
                String part1, part2;

                // Email address must contain text@text.text
                if (input.contains("@")) {
                    String[] parts = input.split("@");
                    part1 = parts[0];
                    if (parts.length > 1) {
                        part1 = parts[0];
                        part2 = parts[1];
                        Log.d("email", part1 + ", " + part2);
                        if (part2.contains(".")) {
                            String[] parts2 = input.split(".");
                            if (parts.length > 1) {
                                step1InfoButtonEmail.setBackgroundResource(R.drawable.info_good);
                                checkEmail = true;
                            } else {
                                step1InfoButtonEmail.setBackgroundResource(R.drawable.info_bad);
                                checkEmail = false;
                            }
                        } else {
                            step1InfoButtonEmail.setBackgroundResource(R.drawable.info_bad);
                            checkEmail = false;
                        }
                    } else {
                        step1InfoButtonEmail.setBackgroundResource(R.drawable.info_bad);
                        checkEmail = false;
                    }
                } else {
                    step1InfoButtonEmail.setBackgroundResource(R.drawable.info_bad);
                    checkEmail = false;
                }

                readyToMoveOn();
            }
        });

        step1EditTextDD.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextDD.getText().toString();

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
                checkDate();
            }
        });

        step1EditTextMM.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextMM.getText().toString();

                if (input.length() == 2) {
                    String[] parts = {input.substring(0, 1), input.substring(1)};
                    Log.d("PARTS", parts[0] + " " + parts[1]);
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


        step1EditTextYYYY.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextYYYY.getText().toString();

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

    public void createSpinner(){
        // Spinner Drop down elements
        final List<String> genders = new ArrayList<String>();
        genders.add("Choose");
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
                if (position != 0 && firstClick)
                {
                    step1InfoButtonGender.setBackgroundResource(R.drawable.info_good);
                    checkGender = true;
                }
                else if (position == 0 && firstClick)
                {
                    step1InfoButtonGender.setBackgroundResource(R.drawable.info_bad);
                    checkGender = false;
                }
                readyToMoveOn();
                firstClick = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                step1InfoButtonGender.setBackgroundResource(R.drawable.info);
            }
        });
    }
    public void checkDate(){
        Log.d("ready", String.valueOf(checkDD + " " + checkMM + " " + checkYYYY));
        if(checkDD && checkMM && checkYYYY)
        {
            checkDate = true;
            step1InfoButtonDate.setBackgroundResource(R.drawable.info_good);
        }
        else
        {
            step1InfoButtonDate.setBackgroundResource(R.drawable.info_bad);
            checkDate = false;
        }
        readyToMoveOn();
    }

    public void readyToMoveOn(){
       // Log.d("ready", String.valueOf(checkDate + " " + checkName + " " + checkEmail + " " + checkGender));

        if(checkDate && checkName && checkEmail && checkGender){

            step1ButtonCreate.setEnabled(true);
        }
    }
}
