package com.example.love.tddc73_lab3;

/**
 * Created by Love on 2016-12-13.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    InteractiveSearcher interactiveSearcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        interactiveSearcher = new InteractiveSearcher(this);
        setContentView(interactiveSearcher);
    }
}
