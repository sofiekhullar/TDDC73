package com.example.love.tddc73_lab3;

/**
 * Created by Love on 2016-12-13.
 */

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InteractiveSearcher extends LinearLayout {

    // declare variables used in class
    EditText searchField;
    ListPopupWindow popList;
    int id = 0;
    int lastShownId = 0;
    // maximum number of suggestions in popList
    int maxSuggestions = 5;
    Context context;

    //
    public InteractiveSearcher(Context theContext) {
        super(theContext);
        context = theContext;
        // instantiate searchField
        searchField = new EditText(context);
        // add listener to search field to know if search text changed
        searchField.addTextChangedListener(watcher);
        searchField.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        // instantiate popList
        popList = new ListPopupWindow(context);
        // put popList under search field
        popList.setAnchorView(searchField);
        // make items in popList clickable
        popList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // set text in search field to the name of the clicked item
                searchField.setText(adapterView.getItemAtPosition(position).toString());
            }
        });

        // add searchField to view
        addView(searchField);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        // if text changed in the search field then update search and increment id
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            String text = charSequence.toString();
            id += 1;
            loadWithThread(text, id);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private void loadWithThread(final String text, final int id){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final List<String> dataList = doNetworkCall(text, id);
                final MyAdapter myAdapter = new MyAdapter(context, dataList);
                if(id>lastShownId) {
                    searchField.post(new Runnable() {
                        @Override
                        public void run() {
                            popList.setAdapter(myAdapter);
                            myAdapter.notifyDataSetChanged();
                            popList.show();
                            lastShownId = id;
                        }
                    });
                }
            }
        });
        t.start();
    }

    private List<String> doNetworkCall(String name, int id){
        List<String> nameL = new ArrayList<String>();
        try {

            // set up the right url with preferd id and name to connect to
            URL url = new URL("http://flask-afteach.rhcloud.com/getnames/"+id+"/"+name);
            // make connection to url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // read in to buffer what's on the requested connection
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            // write it out to stringBuffer
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            // read every line and add to stringBuffer
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            // close the bufferedReader
            in.close();
            // collect all words from buffer in one string
            String result = sb.toString();

            try {
                // create JSONObject of the result string
                JSONObject jObj = new JSONObject(result);
                // make an JSONArray of the JSONObject
                JSONArray jArr = jObj.getJSONArray("result");

                // add names to list. No more names than maxSuggestions
                for(int i = 0;  i < maxSuggestions; i++){
                    nameL.add(jArr.get(i).toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return nameL;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameL;
    }

}