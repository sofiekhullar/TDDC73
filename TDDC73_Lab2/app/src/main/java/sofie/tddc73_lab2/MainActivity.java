package sofie.tddc73_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    EditText searchText;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        searchText = (EditText) findViewById(R.id.searchText);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                searchText.setText("/" + listDataHeader.get(groupPosition) + "/" +
                        listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));

                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();

                searchText.setText("/" + listDataHeader.get(groupPosition));

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

                searchText.setText("/");
            }
        });


        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2,
                                      int arg3) {
                // ((Filter) listAdapter.getFilter()).filter(cs);
                //NewsFeedActivity.this.listAdapter.getFilter().filter(cs);
                Log.d("TextWatcher","Textchange");
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
                Log.d("TextWatcher","BeforeTextChange");

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // When user changed the Text
                Log.d("TextWatcher","AfterText");

            }
        });

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Asia");
        listDataHeader.add("Europe");

        // Adding child data
        List<String> asiaCountry = new ArrayList<>();
        asiaCountry.add("South Korea");
        asiaCountry.add("Thailand");
        asiaCountry.add("Laos");
        asiaCountry.add("China");

        List<String> europeCountry = new ArrayList<>();
        europeCountry.add("Sweden");
        europeCountry.add("Germany");
        europeCountry.add("Denmark");
        europeCountry.add("Italy");

        listDataChild.put(listDataHeader.get(0), asiaCountry); // Header, Child data
        listDataChild.put(listDataHeader.get(1), europeCountry);
    }
}
