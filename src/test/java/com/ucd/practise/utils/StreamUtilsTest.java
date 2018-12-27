package com.ucd.practise.utils;

import com.ucd.practise.consts.ConstPath;
import com.ucd.practise.model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ucd.practise.utils.StreamUtils.*;

public class StreamUtilsTest {

    @Test
    public void upperCaseTest() {
        String[] wordArray = new String[]{"a", "b", "c"};
        List<String> wordList = Arrays.asList(wordArray);
        System.out.println(upperCase(wordList));
    }

    @Test
    public void squareTest() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        System.out.println(square(nums));
    }


    @Test
    public void contactListTest() {
        Stream<Integer> stream = contactList(Arrays.asList(1), Arrays.asList(2, 3));
        List<Integer> list = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
    }

    @Test
    public void filterOddNumTest() {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        List<Integer> even = Arrays.asList(filterOddNum(sixNums));
        System.out.println("The number after filter is: " + even);
    }

    @Test
    public void filterWordTest() {
        String pathName = ConstPath.TXT_PATH;
        List<String> words = filterWord(pathName);
        System.out.println(words);
    }

    @Test
    public void filterWordAndUpCaseTest() {
        String[] words = new String[]{"one", "two", "three", "four"};
        filterWordAndUpCase(words);
    }


    @Test
    public void checkTheStringTest() {
        String strA = " abcd ", strB = null;
        checkTheString(strA,1);
        checkTheString(strB,1);
        System.out.println(checkTheString(strA,2));
        System.out.println(checkTheString(strB,2));
    }

    @Test
    public void countLongestLineTest() {
        System.out.println("The length of the longest line is: " + countLongestLine(ConstPath.TXT_PATH));
    }

    @Test
    public void getNameTest() {
        List<Person> people = new ArrayList<>();
        for(int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            people.add(person);
        }
        List<String> names = getName(10,5, people);
        System.out.println(names);
    }

    @Test
    public void getSortNameTest() {
        List<Person> people = new ArrayList<>();
        for(int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            people.add(person);
        }
        List<Person> peopleList = getSortName(10,5, people);
        System.out.println(peopleList);
    }

    @Test
    public void distinctWordTest() {


    }
}