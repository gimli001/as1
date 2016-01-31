package com.example.xinghua1.xinghua1__fueltrack;

import java.util.ArrayList;

/**
 * Created by xinghua1 on 1/31/16.
 */
public class Entrylist {


    private ArrayList<Initialization> Entrylist = new ArrayList<Initialization>();

//add a data
    public void AddEntrylist(Initialization entry) {
        if (Entrylist.contains(entry)) {
            throw new IllegalArgumentException();
        } else {
            Entrylist.add(entry);
        }
    }
    // get a data if it exists
    public Initialization getEntry(int i) {
        Initialization entry = Entrylist.get(i);
        return entry;
    }


   // check if the data exists or not
    public boolean hasenrty(Initialization entry) {
        int size = Entrylist.size();
        for (int i = 0; i < size; i++) {
            if (entry.equals(entry)) {
                return true;
            }
        }
        return false;
    }
    //remove a data
    public void RemoveEntry(Initialization entry) {
        Entrylist.remove(entry);
    }
    // count how many log entries
    public int getCount(){
        return Entrylist.size();
    }

}
