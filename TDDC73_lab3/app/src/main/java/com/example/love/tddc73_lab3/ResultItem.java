package com.example.love.tddc73_lab3;

/**
 * Created by Love on 2016-12-13.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ResultItem extends View {

    String name;

    public ResultItem(Context theContext, String theName) {
        super(theContext);
        name = theName;
    }

    @Override
    protected void onDraw(Canvas canvas){
        // set color, size for the text
        Paint text = new Paint();
        text.setColor(Color.BLACK);
        text.setTextSize(50);

        // set color and style for background
        Paint white = new Paint();
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        canvas.drawPaint(white);

        // draw text with desired name
        canvas.drawText(name, 10, 70, text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int desiredWidth = 100;
        int desiredHeight = 100;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        //Must be this size
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } //Can't be bigger than...
        else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } //Be whatever you want
        else {
            width = desiredWidth;
        }

        //Measure Height
        //Must be this size
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;

        } //Can't be bigger than...
        else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }
}