package com.ucd.practise.utils;

import com.ucd.practise.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TreeMapUtils {

    /**
     *
     * Checking the whether the person list beyond the limitation or has redundant.
     * If it pass the examining above, outputting the sorted result as ArrayList.
     * This is a demo of  controller, so its structure looked bloated.
     *
     * @param people List<Person>
     * @return  sorted List<Person>
     */
    public static List<Person> checkSortLists(List<Person> people){
        TreeMap tm = new TreeMap();
        for (Person person: people) {
            if(person.getAge()>18){
                System.out.println("checking beyond");
                return null;
            }
            tm.put(person.getNo(),person);
        }
        if(people.size()!=tm.size()){
            System.out.println("checking redundant");
            return null;
        }
        List<Person> sortPeople = new ArrayList(tm.values());
        return sortPeople;
    }

}
