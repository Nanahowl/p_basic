package com.ucd.practise.utils;

import com.ucd.practise.consts.ConstPath;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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


}