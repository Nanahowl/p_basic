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
        checkTheString(strA, 1);
        checkTheString(strB, 1);
        System.out.println(checkTheString(strA, 2));
        System.out.println(checkTheString(strB, 2));
    }

    @Test
    public void countLongestLineTest() {
        System.out.println("The length of the longest line is: " + countLongestLine(ConstPath.TXT_PATH));
    }

    @Test
    public void getNameTest() {
        List<Person> people = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i, i);
            people.add(person);
        }
        List<String> names = getName(10, 5, people);
        System.out.println(names);
    }

    @Test
    public void getSortNameTest() {
        List<Person> people = new ArrayList<>();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i, i);
            people.add(person);
        }
        List<Person> peopleList = getSortName(10, 5, people);
        System.out.println(peopleList);
    }

    @Test
    public void distinctWordTest() {
        List<String> words = distinctWord(ConstPath.TXT_PATH);
        System.out.println("The sorted words are: " + words);
    }

    @Test
    public void matchTest() {

        List<Person> people = new ArrayList();
        people.add(new Person(1, "name" + 1, 10));
        people.add(new Person(2, "name" + 2, 21));
        people.add(new Person(3, "name" + 3, 34));
        people.add(new Person(4, "name" + 4, 6));
        people.add(new Person(5, "name" + 5, 55));
        System.out.println("All are adult? " + match(1, people));
        System.out.println("Any child? " + match(2, people));

    }

    @Test
    public void generatorTest() {
        Stream<Integer> stream = generator(1);
        System.out.println("The first random stream is:");
        stream.forEach(System.out::println);
        Stream stream1 = generator(2);
        System.out.println("The second random stream is:");
        stream1.forEach(System.out::println);
    }

    @Test
    public void supplierTest() {
        supplier();
    }

    @Test
    public void iteratorTest() {
        iterator(3, 10, -1);
    }

    @Test
    public void groupByAgeTest() {
        groupByAge();
    }

    @Test
    public void partitioningByAgeTest() {
        partitioningByAge();
    }

    @Test
    public void concatStringTest() {
        String[] strings = {"a", "B", "c", "D", "e", "F"};
        System.out.println(concatString("B", strings, "The result of concat is: "));
    }

    @Test
    public void minOrMaxTest() {
        Double[] nums = {-1.5, 1.0, -3.0, -2.0};
        System.out.println("The minimum of the nums is: "+minOrMax(nums, "Min"));
        System.out.println("The maximum of the nums is: "+minOrMax(nums, "max"));
    }

    @Test
    public void sumTest() {
        Integer[] nums = {1, 2, 3, 4};
        System.out.println("(Without seed)The sum of the nums is: "+sum(nums,null));
        System.out.println("The sum of the nums is: "+sum(nums,9));
    }
}