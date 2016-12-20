package com.example.love.tddc73_lab3;

/**
 * Created by Love on 2016-12-13.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context context;
    List<String> names;

    public MyAdapter(Context theContext, List<String> stringArray){
        context = theContext;
        names = stringArray;
    }

    public int getCount() {
        return names.size();
    }

    // get names from result in search
    @Override
    public Object getItem(int i) {
        return names.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // make a new resultItem for each new name in the search result
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ResultItem resultItem = new ResultItem(context, names.get(i));
        return resultItem;
    }
}