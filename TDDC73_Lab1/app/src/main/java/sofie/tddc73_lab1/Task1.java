package sofie.tddc73_lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

public class Task1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_task1);
        //setContentView(R.layout.layout_task2);
        //setContentView(R.layout.layout_task3);

        /*** Layout 1 ***/
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        Button myButton = new Button(this);
        myButton.setId(View.generateViewId());
        myButton.setText("Knapp");
        myButton.setWidth(width - 10);

        EditText myEditText = new EditText(this);
        myEditText.setId(View.generateViewId());
        myEditText.setText("Text fält");
        myEditText.setWidth(width - 10);

        RatingBar myRatingBar = new RatingBar(this);
        myRatingBar.setId(View.generateViewId());

        EditText myMultiEditText = new EditText(this);
        myMultiEditText.setId(View.generateViewId());
        myMultiEditText.setSingleLine(false);
        myMultiEditText.setWidth(width - 10);

        RelativeLayout myLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams editTextParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams ratingBarParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams multiEditTextParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        editTextParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        editTextParams.addRule(RelativeLayout.BELOW, myButton.getId());

        ratingBarParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        ratingBarParams.addRule(RelativeLayout.BELOW, myEditText.getId());

        multiEditTextParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        multiEditTextParams.addRule(RelativeLayout.BELOW, myRatingBar.getId());

        myMultiEditText.setPadding(0,200,0,0);

        myLayout.addView(myButton, buttonParams);
        myLayout.addView(myEditText, editTextParams);
        myLayout.addView(myRatingBar, ratingBarParams);
        myLayout.addView(myMultiEditText, multiEditTextParams);

        setContentView(myLayout);
    }
}
