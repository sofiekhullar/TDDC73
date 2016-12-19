package sofie.accountregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by sofiekhullar on 2016-12-19.
 */
public class Step1 extends AppCompatActivity {

    // Step1
    Button step1ButtonCreate, step1ButtonCancel;
    EditText step1EditTextName,step1EditTextEmail,step1EditTextDD, step1EditTextMM, step1EditTextYYYY;
    Spinner step1SpinnerGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        // Step1
        step1ButtonCreate = (Button) findViewById(R.id.step1ButtonCreate);
        step1ButtonCancel = (Button) findViewById(R.id.step1ButtonCancel);
        step1EditTextName = (EditText) findViewById(R.id.step1EditTextName);
        step1EditTextEmail = (EditText) findViewById(R.id.step1EditTextEmail);
        step1EditTextDD = (EditText) findViewById(R.id.step1EditTextDD);
        step1EditTextMM = (EditText) findViewById(R.id.step1EditTextMM);
        step1EditTextYYYY = (EditText) findViewById(R.id.step1EditTextNameYYYY);
        step1SpinnerGender = (Spinner) findViewById(R.id.Step1SpinnerGender);

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
