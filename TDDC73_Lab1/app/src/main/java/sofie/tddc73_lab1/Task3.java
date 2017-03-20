package sofie.tddc73_lab1;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Task3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*** Layout 3 ***/
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        Log.d("HEJ", String.valueOf(width));
        // First question
        TextView myQuest1  = new TextView(this);
        myQuest1.setText("Hur trivs du på LiU");
        myQuest1.setId(View.generateViewId());
        myQuest1.setWidth(width/2);
        myQuest1.setPadding(20, 20, 0, 0);

        CheckBox quest1box1 = new CheckBox(this);
        quest1box1.setText("Bra");
        quest1box1.setWidth(width/5);
        quest1box1.setId(View.generateViewId());

        CheckBox quest1box2 = new CheckBox(this);
        quest1box2.setText("Mycket bra");
        quest1box2.setWidth(width/3);
        quest1box2.setId(View.generateViewId());

        CheckBox quest1box3 = new CheckBox(this);
        quest1box3.setText("Jättebra");
        quest1box3.setWidth(width/4);
        quest1box3.setId(View.generateViewId());

        // Second question
        TextView myQuest2  = new TextView(this);
        myQuest2.setText("Läser du på LiTH");
        myQuest2.setId(View.generateViewId());
        myQuest2.setWidth(width/2);
        myQuest2.setPadding(20, 20, 0, 0);

        CheckBox quest2box1 = new CheckBox(this);
        quest2box1.setText("Ja");
        quest2box1.setWidth(width/5);
        quest2box1.setId(View.generateViewId());

        CheckBox quest2box2 = new CheckBox(this);
        quest2box2.setText("Nej");
        quest2box2.setWidth(width/5);
        quest2box2.setId(View.generateViewId());

        // Third question
        ImageView myImage = new ImageView(this);
        myImage.setId(View.generateViewId());
        myImage.setImageResource(R.drawable.liu_logo);
        myImage.setLayoutParams(new DrawerLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        myImage.setPadding(0, 20, 0, 0);

        TextView myQuest3  = new TextView(this);
        myQuest3.setText("Är detta LiUs logotyp");
        myQuest3.setId(View.generateViewId());
        myQuest3.setWidth(width/2);
        myQuest3.setPadding(20, 20, 0, 0);

        CheckBox quest3box1 = new CheckBox(this);
        quest3box1.setText("Ja");
        quest3box1.setWidth(width/5);
        quest3box1.setId(View.generateViewId());

        CheckBox quest3box2 = new CheckBox(this);
        quest3box2.setText("Nej");
        quest3box2.setWidth(width/5);
        quest3box2.setId(View.generateViewId());

        // Button
        Button myButton = new Button(this);
        myButton.setId(View.generateViewId());
        myButton.setText("SKICKA IN");
        myButton.setWidth(width - 10);

        RelativeLayout myLayout = new RelativeLayout(this);

        RelativeLayout.LayoutParams quest1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest1box1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest1box2Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest1box3Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest2Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest2box1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest2box2Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams imageParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest3Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest3box1Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams quest3box2Params =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);


        quest1Params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        quest1box1Params.addRule(RelativeLayout.BELOW, myQuest1.getId());
        quest1box2Params.addRule(RelativeLayout.BELOW, myQuest1.getId());
        quest1box3Params.addRule(RelativeLayout.BELOW, myQuest1.getId());

        quest1box1Params.addRule(RelativeLayout.ALIGN_LEFT);
        quest1box2Params.addRule(RelativeLayout.RIGHT_OF, quest1box1.getId());
        quest1box3Params.addRule(RelativeLayout.RIGHT_OF, quest1box2.getId());

        quest2Params.addRule(RelativeLayout.BELOW, quest1box1.getId());
        quest2Params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        quest2box1Params.addRule(RelativeLayout.BELOW, myQuest2.getId());
        quest2box2Params.addRule(RelativeLayout.BELOW, myQuest2.getId());

        quest2box1Params.addRule(RelativeLayout.ALIGN_LEFT);
        quest2box2Params.addRule(RelativeLayout.RIGHT_OF, quest2box1.getId());

        imageParams.addRule(RelativeLayout.BELOW, quest2box2.getId());
        imageParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        quest3Params.addRule(RelativeLayout.BELOW, myImage.getId());
        quest3Params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        quest3box1Params.addRule(RelativeLayout.BELOW, myQuest3.getId());
        quest3box1Params.addRule(RelativeLayout.ALIGN_LEFT);
        quest3box2Params.addRule(RelativeLayout.BELOW, myQuest3.getId());
        quest3box2Params.addRule(RelativeLayout.RIGHT_OF, quest3box1.getId());

        buttonParams.addRule(RelativeLayout.BELOW, quest3box1.getId());
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        myLayout.addView(myQuest1, quest1Params);
        myLayout.addView(quest1box1, quest1box1Params);
        myLayout.addView(quest1box2, quest1box2Params);
        myLayout.addView(quest1box3, quest1box3Params);

        myLayout.addView(myQuest2, quest2Params);
        myLayout.addView(quest2box1, quest2box1Params);
        myLayout.addView(quest2box2, quest2box2Params);

        myLayout.addView(myImage, imageParams);

        myLayout.addView(myQuest3, quest3Params);
        myLayout.addView(quest3box1, quest3box1Params);
        myLayout.addView(quest3box2, quest3box2Params);

        myLayout.addView(myButton, buttonParams);

        setContentView(myLayout);
    }
}
