package sofie.tddc73_lab1;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by sofiekhullar on 2016-11-08.
 */
public class Task2  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*** Layout 2 ***/
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int textLength = 150;

        // Name
        TextView myName  = new TextView(this);
        myName.setText("Name");
        myName.setId(View.generateViewId());
        myName.setWidth(textLength);
        myName.setPadding(20, 40, 0, 0);

        EditText myEditName = new EditText(this);
        myEditName.setId(View.generateViewId());
        myEditName.setWidth(width - 30);

        // Password
        TextView myPass  = new TextView(this);
        myPass.setText("Lösenord");
        myPass.setId(View.generateViewId());
        myPass.setWidth(textLength);
        myPass.setPadding(20, 40, 0, 0);

        EditText myEditPass = new EditText(this);
        myEditPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        myEditPass.setId(View.generateViewId());
        myEditPass.setWidth(width - 30);

        // Email
        TextView myEmail  = new TextView(this);
        myEmail.setText("Email");
        myEmail.setId(View.generateViewId());
        myEmail.setWidth(textLength);
        myEmail.setPadding(20, 40, 0, 0);

        EditText myEditEmail = new EditText(this);
        myEditEmail.setId(View.generateViewId());
        myEditEmail.setWidth(width - 30);

        // Age
        TextView myAge  = new TextView(this);
        myAge.setText("Ålder");
        myAge.setId(View.generateViewId());
        myAge.setWidth(textLength);
        myAge.setPadding(20, 40, 0, 0);

        SeekBar seekBar = new SeekBar(this);
        seekBar.setProgress(1);

        RelativeLayout myLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams nameParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams editNameParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams passParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams editPassParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams emailParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams editEmailParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams ageParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams seekbarParams =
                new RelativeLayout.LayoutParams(
                        width - 30,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);


        editNameParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editNameParams.addRule(RelativeLayout.RIGHT_OF, myName.getId());

        passParams.addRule(RelativeLayout.BELOW, myName.getId());
        editPassParams.addRule(RelativeLayout.BELOW, myEditName.getId());
        editPassParams.addRule(RelativeLayout.RIGHT_OF, myPass.getId());

        emailParams.addRule(RelativeLayout.BELOW, myPass.getId());
        editEmailParams.addRule(RelativeLayout.BELOW, myEditPass.getId());
        editEmailParams.addRule(RelativeLayout.RIGHT_OF, myEmail.getId());

        ageParams.addRule(RelativeLayout.BELOW, myEmail.getId());
        seekbarParams.addRule(RelativeLayout.BELOW, myEditEmail.getId());
        seekbarParams.addRule(RelativeLayout.RIGHT_OF, myAge.getId());

        myLayout.addView(myName, nameParams);
        myLayout.addView(myEditName, editNameParams);
        myLayout.addView(myPass, passParams);
        myLayout.addView(myEditPass, editPassParams);
        myLayout.addView(myEmail, emailParams);
        myLayout.addView(myEditEmail, editEmailParams);
        myLayout.addView(myAge, ageParams);
        myLayout.addView(seekBar, seekbarParams);
        //myLayout.addView(seekBar);
        setContentView(myLayout);
    }

}
