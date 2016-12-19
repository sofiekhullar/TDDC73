package sofie.accountregister;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * Created by sofiekhullar on 2016-12-19.
 */
public class Step1 extends AppCompatActivity {

    // Step1
    Button step1ButtonCreate, step1ButtonCancel;
    ImageButton step1InfoButtonName, step1InfoButtonEmail;
    EditText step1EditTextName,step1EditTextEmail,step1EditTextDD, step1EditTextMM, step1EditTextYYYY;
    Spinner step1SpinnerGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        // Step1
        step1ButtonCreate = (Button) findViewById(R.id.step1ButtonCreate);
        step1ButtonCancel = (Button) findViewById(R.id.step1ButtonCancel);
        step1InfoButtonName = (ImageButton) findViewById(R.id.step1InfoButtonName);
        step1InfoButtonEmail = (ImageButton) findViewById(R.id.step1InfoButtonEmail);
        step1EditTextName = (EditText) findViewById(R.id.step1EditTextName);
        step1EditTextEmail = (EditText) findViewById(R.id.step1EditTextEmail);
        step1EditTextDD = (EditText) findViewById(R.id.step1EditTextDD);
        step1EditTextMM = (EditText) findViewById(R.id.step1EditTextMM);
        step1EditTextYYYY = (EditText) findViewById(R.id.step1EditTextNameYYYY);
        step1SpinnerGender = (Spinner) findViewById(R.id.Step1SpinnerGender);

        step1EditTextName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = step1EditTextName.getText().toString();
                // Full name must be longer than 3 letters and contain one space
                if( input.length() > 2 && input.matches(".*\\s+.*"))
                {
                    step1InfoButtonName.setBackgroundResource(R.drawable.info_good);
                } else  step1InfoButtonName.setBackgroundResource(R.drawable.info_bad);
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
}
