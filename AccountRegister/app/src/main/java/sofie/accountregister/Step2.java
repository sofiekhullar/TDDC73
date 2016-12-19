package sofie.accountregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sofiekhullar on 2016-12-19.
 */
public class Step2 extends AppCompatActivity {

    // Step2
    Button step2ButtonCreate, step2ButtonPrev;
    EditText step2EditTextUsername, step2EditTextPassword1, step2EditTextPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);

        // Step2
        step2ButtonCreate = (Button) findViewById(R.id.step2ButtonCreate);
        step2ButtonPrev = (Button) findViewById(R.id.step2ButtonPrev);
        step2EditTextUsername = (EditText) findViewById(R.id.step2EditTextUsername);
        step2EditTextPassword1 = (EditText) findViewById(R.id.step2EditTextPassword1);
        step2EditTextPassword2 = (EditText) findViewById(R.id.step2EditTextPassword2);

        step2ButtonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step2.this, LoginScreen.class);
                Step2.this.startActivity(myIntent);
            }
        });

        step2ButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Step2.this, Step1.class);
                Step2.this.startActivity(myIntent);
            }
        });
    }
}