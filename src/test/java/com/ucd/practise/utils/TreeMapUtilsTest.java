package com.ucd.practise.utils;

import com.ucd.practise.PractiseApplicationTests;
import com.ucd.practise.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ucd.practise.utils.TreeMapUtils.checkSortLists;


public class TreeMapUtilsTest extends PractiseApplicationTests {

    @Test
    public void checkSortListsTest()

    {
        List<Person> people = new ArrayList();
        people.add(new Person(1, "name" + 1, 10));
        people.add(new Person(6, "name" + 2, 11));
        people.add(new Person(3, "name" + 3, 2));
        people.add(new Person(15, "name" + 4, 6));
        people.add(new Person(5, "name" + 5, 11));

        //1.redundant
        Person person = new Person(1, "name" + 1, 10);
        people.add(person);
        System.out.println("redundant:"+checkSortLists(people));

        //2.success
        person.setNo(20);
        System.out.println(checkSortLists(people));

        //3.beyond
        person.setAge(20);
        System.out.println("beyond:"+checkSortLists(people));
    }
}