package sofie.tddc73_lab2;

import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class Main2Activity extends Activity {

    private EditText search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Environment> environmentList = new ArrayList<>();
    private ArrayList<Animal> animalList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        search = (EditText) findViewById(R.id.searchText);
        //display the list
        displayList();
        //expand all Groups
        expandAll();

        search.setText("/");
        search.setSelection(1);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (search.getText().length() != 0) {
                    String tmp = search.getText().toString().substring(1);
                    if (tmp.contains("/")) {
                        String[] parts = tmp.split("/");
                        if (parts.length > 1) {
                            tmp = parts[1];
                        }
                    }

                    listAdapter.filterData(tmp);
                    expandAll();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                if (search.getText().length() == 0){
                    search.setText("/");
                    search.setSelection(1);
                }
            }
        });

    }


    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableListView);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(Main2Activity.this, environmentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        // Listview on child click listener
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                search.setText("/" + environmentList.get(groupPosition).getName() + "/" + environmentList.get(groupPosition).getAnimalList().get(childPosition).getName());
                search.setSelection(search.getText().toString().length());
                return false;
            }
        });

        // Listview Group expanded listener
       /* myList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                search.setText("/" + environmentList.get(groupPosition).getName());
            }
        });

        // Listview Group collasped listener
        myList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                search.setText("/");
            }
        });*/

    }

    private void loadSomeData() {

        Animal animal = new Animal("cat");
        animalList.add(animal);
        animal = new Animal("dog");
        animalList.add(animal);
        animal = new Animal("cow");
        animalList.add(animal);

        Environment environment = new Environment("land", animalList);
        environmentList.add(environment);

        animalList = new ArrayList<>();
        animal = new Animal("shark");
        animalList.add(animal);
        animal = new Animal("surtle");
        animalList.add(animal);
        animal = new Animal("sea Lion");
        animalList.add(animal);
        animal = new Animal("clown fish");
        animalList.add(animal);
        animal = new Animal("shrimp");
        animalList.add(animal);

        environment = new Environment("sea", animalList);
        environmentList.add(environment);
    }
}
