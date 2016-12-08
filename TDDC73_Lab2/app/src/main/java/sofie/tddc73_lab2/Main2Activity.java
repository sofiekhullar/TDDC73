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

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearColor();
            }

        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                expandAll();
                if (search.getText().length() != 0) {
                    String tmp = search.getText().toString().substring(1);
                    if (tmp.contains("/")) {
                        String[] parts = tmp.split("/");
                        if (parts.length > 1) {
                            tmp = parts[1];
                        }
                    }
                    int pos = listAdapter.selectData(tmp);
                    clearColor();

                    Log.d("ANIMAL POS", String.valueOf(pos));
                    if(pos > 0 && pos <=10){
                        View childView = listAdapter.getChildView(myList, pos);
                        childView.setBackgroundColor(Color.rgb(214, 214, 214));
                    }
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
                if (search.getText().length() == 1){
                    clearColor();
                }
            }
        });
    }

    private void clearColor(){
        int size = (listAdapter.getChildrenCount(0) + listAdapter.getChildrenCount(1) + listAdapter.getGroupCount());
        for(int i = 0; i < size; i++){
            View viewTmp = listAdapter.getChildView(myList, i);
            viewTmp.setBackgroundColor(Color.TRANSPARENT);
        }
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
            View lastColored;
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                if(lastColored != null)
                {
                    lastColored.setBackgroundColor(Color.TRANSPARENT);
                    lastColored.invalidate();
                }
                lastColored = v;
                v.setBackgroundColor(Color.rgb(214, 214, 214));

                search.setText("/" + environmentList.get(groupPosition).getName() + "/" + environmentList.get(groupPosition).getAnimalList().get(childPosition).getName());
                search.setSelection(search.getText().toString().length());
                return false;
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
