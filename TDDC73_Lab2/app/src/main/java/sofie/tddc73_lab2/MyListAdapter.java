package sofie.tddc73_lab2;

/**
 * Created by sofiekhullar on 2016-11-24.
 */

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Environment> environmentList;
    private ArrayList<Environment> originalList;

    public MyListAdapter(Context context, ArrayList<Environment> environmentList) {
        this.context = context;
        this.environmentList = new ArrayList<>();
        this.environmentList.addAll(environmentList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(environmentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Animal> envionmentList = environmentList.get(groupPosition).getAnimalList();
        return envionmentList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Animal animal = (Animal) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(animal.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Animal> animalList = environmentList.get(groupPosition).getAnimalList();
        return animalList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return environmentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return environmentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Environment continent = (Environment) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(continent.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public View getChildView(ExpandableListView listView, int pos) {
        return listView.getChildAt(pos);
    }

    public int selectData(String query) {
        int posEnv = 0;
        int posAni = 0;

        query = query.toLowerCase();
        for (Environment environment : originalList) {
            posEnv++;
            ArrayList<Animal> animalList = environment.getAnimalList();
            for (Animal animal : animalList) {
                posAni++;
                if (animal.getName().toLowerCase().contains(query)) {
                    Log.d("ANIMAL", animal.getName() + "    position " + posEnv + ", " + posAni);
                    if (posEnv == 1) {
                        return (posAni);
                    } else {
                        return (posEnv  + posAni -1);
                    }
                }
            }
        }
        return -1;
    }



    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(environmentList.size()));
        environmentList.clear();

        if(query.isEmpty()){
            environmentList.addAll(originalList);
        }
        else {

            for(Environment environment: originalList){

                ArrayList<Animal> animalList = environment.getAnimalList();
                ArrayList<Animal> newList = new ArrayList<>();
                for(Animal animal: animalList){
                    if(animal.getName().toLowerCase().contains(query)){
                        newList.add(animal);
                    }
                }
                if(newList.size() > 0){
                    Environment nEnvironment = new Environment(environment.getName(),newList);
                    environmentList.add(nEnvironment);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(environmentList.size()));
        notifyDataSetChanged();
    }
}