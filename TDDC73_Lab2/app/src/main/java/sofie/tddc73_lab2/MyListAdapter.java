package sofie.tddc73_lab2;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Environment> environmentList;

    public MyListAdapter(Context context, ArrayList<Environment> environmentList) {
        this.context = context;
        this.environmentList = new ArrayList<>();
        this.environmentList.addAll(environmentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Animal> environment = environmentList.get(groupPosition).getAnimalList();
        return environment.get(childPosition);
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
}