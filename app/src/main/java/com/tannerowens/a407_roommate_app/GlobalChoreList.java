package com.tannerowens.a407_roommate_app;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 3/30/2017.
 */

public class GlobalChoreList extends Application {
    //Key - String assignedTo
    //Value - ArrayList<String> listOfChores
    private static HashMap<String, ArrayList<String>> choreMap;

    public GlobalChoreList() {
        this.choreMap = new HashMap<String, ArrayList<String>>();
    }

    public Map<String, ArrayList<String>> getChoreMap() {

        return this.choreMap;
    }

    /* returns the chore list for supplied name
     * @param name - name of roommate
     * @return List<String> list of chores
     */
    public ArrayList<String> getChoresFor(String name) {

        if(choreMap.containsKey(name))
            return choreMap.get(name);
        else
            return null;
    }

    /* adds chore to chore list of supplied name. Adds
     * name to keys if it does not yet exist
     * @param chore - String of chore name
     * @param - name of roommate
     * @return void
     */
    public void assignChore(String chore, String name) {
        ArrayList<String> list;

        if(choreMap.containsKey(name)) {
            list = choreMap.get(name);
            if(list.contains(chore))
                return;
        }
        else {
            list = new ArrayList<String>();
        }

        list.add(chore);
        choreMap.put(name, list);

        return;
    }

    /* finds the owner of the specified chore
     * @param chore - name of chore to find owner for
     * @return String name of chore owner
     */
    public String getChoreOwner(String chore) {
        ArrayList<String> list;

        for(String name : choreMap.keySet()) {
            list = choreMap.get(name);

            for(String s : list) {
                if(chore.equals(s))
                    return name;
            }
        }

        return null;
    }

}
