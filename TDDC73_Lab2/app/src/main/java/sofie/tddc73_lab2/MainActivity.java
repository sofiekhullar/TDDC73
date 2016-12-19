package sofie.tddc73_lab2;

import android.graphics.Color;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {

    private EditText search;
    private MyListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<Environment> environmentList = new ArrayList<>();
    private ArrayList<Animal> animalList = new ArrayList<>();
    private boolean clicked = false;
    int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.searchText);
        //display the list
        displayList();
        //expand all Groups
        expandAll();

        // set the start text for the search field
        search.setText("/");
        search.setSelection(1);
        search.setClickable(true);

        // check if the user clicks on the search field
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                expandAll();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // if no text in search field always have /
                if (search.getText().length() == 0 && search.getText().length() == 1) {
                    search.setText("/");
                    search.setSelection(1);
                    myList.setItemChecked(index, false);

                } else if (clicked) {

                    boolean findEnvironment = false;
                    boolean findAnimal = false;

                    String input = search.getText().toString().substring(1);
                    String environment = input;
                    String animal = "";
                    int groupPos;

                    // if the search query contains / split the string
                    if (input.contains("/")) {
                        String[] parts = input.split("/");
                        environment = parts[0];
                        if (parts.length > 1) {
                            environment = parts[0];
                            animal = parts[1];
                        }
                    }

                    // first check the group
                    for (int i = 0; i < listAdapter.getGroupCount(); i++) {
                        Environment environmentGroup = environmentList.get(i);

                        if (environmentGroup.getName().startsWith(environment)) {
                            groupPos = environmentList.indexOf(environmentGroup);
                            search.setBackgroundColor(Color.TRANSPARENT);
                            findEnvironment = true;

                            // second check the child
                            for (int j = 0; j < environmentGroup.getAnimalList().size(); j++) {
                                Animal animalChild = environmentGroup.getAnimalList().get(j);

                                if (animalChild.getName().startsWith(animal) && !animal.equals("")) {
                                    search.setBackgroundColor(Color.TRANSPARENT);

                                    // mark the first correct child
                                    findAnimal = true;
                                    collapseAll(groupPos);
                                    index = myList.getFlatListPosition(ExpandableListView.getPackedPositionForChild(i, j));
                                    myList.setItemChecked(index, true);

                                    if (animal.contains(animalChild.getName())) {
                                        index = myList.getFlatListPosition(ExpandableListView.getPackedPositionForChild(i, j));
                                        myList.setItemChecked(index, true);
                                    }
                                    break;
                                }
                            }
                        }
                        // if not a environment or animal is found mark it red
                        if (!findAnimal && !animal.equals("")) {
                            search.setBackgroundColor(android.graphics.Color.RED);
                            myList.setItemChecked(index, false);
                        }
                        if (!findEnvironment && !environment.equals("")) {
                            search.setBackgroundColor(android.graphics.Color.RED);
                            myList.setItemChecked(index, false);
                        }
                    }
                }
            }
        });
        clicked = false;
    }


    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++) {
            myList.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll(int skip) {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            if(i != skip) myList.collapseGroup(i);
        }
    }


    //method to expand all groups
    private void displayList() {
        //display the list
        loadSomeData();
        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableListView);
        //create the adapter by passing your ArrayList data
        listAdapter = new MyListAdapter(MainActivity.this, environmentList);
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

        // Listview on child click listener
        myList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                expandAll();
                clicked = true;
                //get the group header
                Environment enviromentGroup = environmentList.get(groupPosition);
                //get the child info
                Animal animalChild = enviromentGroup.getAnimalList().get(childPosition);
                // Set the search way
                search.setText("/" + enviromentGroup.getName() + "/" + animalChild.getName());
                search.setSelection(search.getText().length());

                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                return false;
            }
        });

        myList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                expandAll();
                clicked = true;
                //set the group header
                Environment environmentGroup = environmentList.get(groupPosition);
                search.setText("/" + environmentGroup.getName() + "/");
                search.setSelection(search.getText().length());

                return true;
            }
        });
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
        animal = new Animal("turtle");
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
