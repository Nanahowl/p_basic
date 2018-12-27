package com.ucd.practise.utils;


import com.ucd.practise.consts.ConstPath;
import com.ucd.practise.model.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {

    public static void main(String[] args) {
        sort(Arrays.asList("a", "b", "c", "c"));


        //
//

        //List the word from a file without repeat.
        List<String> dWords = distinctWord(ConstPath.TXT_PATH);
        System.out.println(dWords);

    }

    public static void sort(List<String> stringList) {

        System.out.printf("");

    }


    public void constractStream() {
        // 1. Individual values
        Stream stream = Stream.of("a", 1, 2.3f, "test");
        // 2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();


        //Special values
        // 1.IntStream
        //.of all of the elements
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
        //.range not include right bound.
        IntStream.range(1, 3).forEach(System.out::println);
        //.rangeClosed all of the elements.
        IntStream.rangeClosed(1, 3).forEach(System.out::println);

    }

    public void streamToOtherType() {

        String[] arrays = new String[]{"a", "b", "c"};
        Stream stream = Arrays.stream(arrays);

        //The exchange between List and Array.
        List<String> listStrings = Stream.of(arrays).collect(Collectors.toList());
        String[] ss = listStrings.stream().toArray(String[]::new);

        // 1. Array
        String[] strArray1 = Arrays.stream(arrays).toArray(String[]::new);
        //****Can not cast the type
        //String[] strArray1 = stream.toArray(String[]::new);

        // 2. Collection
        List<String> lista = Arrays.asList(arrays).stream().collect(Collectors.toList());
        List<String> listb = Arrays.asList(arrays).stream().collect(Collectors.toCollection(ArrayList::new));
        Set set = Arrays.stream(arrays).collect(Collectors.toSet());
        Stack stack = Arrays.stream(arrays).collect(Collectors.toCollection(Stack::new));

        //****Can not cast the type
        // List<String> list1 = stream.collect(Collectors.toList());
        List<String> strings = Arrays.asList(arrays);

        //List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
        //Set set1 = stream.collect(Collectors.toSet());
        //Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));

        // 3. String
        String str = stream.collect(Collectors.joining()).toString();
    }


    /**
     * Performs a Uppercase operation.
     *
     * @param inputStr The input String List.
     * @return The ouput String List which already case to the Uppercase.
     */
    public static List<String> upperCase(List<String> inputStr) {
        List<String> outputStr = inputStr.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        return outputStr;
    }


    /**
     * Performs a square operation.
     *
     * @param inputInt The input Integer List.
     * @return The square result of intput data.
     */
    public static List<Integer> square(List<Integer> inputInt) {
        List<Integer> outputInt = inputInt.stream().
                map(n -> n * n).
                collect(Collectors.toList());
        return outputInt;
    }


    /**
     * Returns a stream consisting of the results of replacing each element of
     * those Lists with the contents of a mapped stream produced by applying
     * the provided mapping function to each element.  Each mapped stream is
     * after its contents have been placed into this stream.  (If a mapped
     * stream is {@code null an empty stream is used, instead.)
     *
     * @param list1 A input List
     * @param list2 list2 Another input List
     * @return The stream which include all of the elements from input Lists.
     */
    public static Stream<Integer> contactList(List list1, List list2) {
        //flatmap
        Stream<List<Integer>> inputStream = Stream.of(list1, list2);
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        return outputStream;
    }


    /**
     * Filter the odd number from the inuput Integer array.
     *
     * @param nums The input Integer array which mix with odd and even nums.
     * @return The output Integer array which only has the even number.
     */
    public static Integer[] filterOddNum(Integer[] nums) {
        Integer[] evenNums = Stream.of(nums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        return evenNums;
    }


    /**
     * Filter the words from a file.
     *
     * @param pathname the location of the file.
     * @return the List of the all of the words in the file.
     */
    public static List<String> filterWord(String pathname) {
        List<String> words = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathname));
            words = br.lines().
                    flatMap(line -> Stream.of(line.split(" "))).
                    filter(word -> word.length() > 0).
                    collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    /**
     * Peek: intermediate
     * Using the Peek to output the elements.
     *
     * @param words
     * @return
     */
    public static void filterWordAndUpCase(String[] words) {
        Stream.of(words)
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }


    /**
     * Optional
     * 这也是一个模仿 Scala 语言中的概念，作为一个容器，它可能含有某值，或者不包含。
     * 使用它的目的是尽可能避免 NullPointerException.
     * The relevant method: ofNullable, ifPresent, map, orElse.
     *
     * @param str  The input String
     * @param type The operation type.
     *             (*1: printing the string when the string has value.
     *             2: counting the length of input string when its has element.)
     * @return the length of the string or just null
     */
    public static Integer checkTheString(String str, int type) {
        if (type == 1) {
            Optional.ofNullable(str).ifPresent(System.out::println);
            return null;
        } else {
            return Optional.ofNullable(str).map(String::length).orElse(-1);
        }
    }

    /**
     * @param pathname
     * @return
     */
    public static int countLongestLine(String pathname) {
        BufferedReader br = null;
        int longest = 0;
        try {
            br = new BufferedReader(new FileReader(pathname));
            //Max
            //Finding the longest line in the file.
            longest = br.lines().
                    mapToInt(String::length).
                    max().
                    getAsInt();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return longest;
    }

    /**
     * limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素
     *
     * @param limit
     * @param skip
     * @param personList
     * @return
     */
    public static List<String> getName(int limit, int skip, List<Person> personList) {
        List<String> nameList = personList.stream().
                map(Person::getName).limit(limit).skip(skip).collect(Collectors.toList());
        return nameList;
    }

    /**
     * limit & skip 's short-circuit invalid after sorted.
     *
     * @param limit
     * @param skip
     * @param personList
     * @return
     */
    public static List<Person> getSortName(int limit, int skip, List<Person> personList) {
        List<Person> nameList = personList.stream()
                .sorted(Comparator.comparing(Person::getName))
                .limit(limit).skip(skip).collect(Collectors.toList());
        return nameList;
    }

    /**
     * Distinct the word from a file.
     *
     * @param pathname
     * @return
     */
    public static List<String> distinctWord(String pathname) {
        List<String> words = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(pathname));
            //Distinct
            //Finding the non-redundant word.
            words = br.lines().
                    flatMap(line -> Stream.of(line.split(" "))).
                    filter(word -> word.length() > 0).
                    map(String::toLowerCase).
                    distinct().
                    sorted().
                    collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("The sorted words are: " + words);
        return words;
    }


}

//    public void generator(){
//        Random seed = new Random();
//        Supplier<Integer> random = seed::nextInt;
//        Stream.generate(random).limit(10).forEach(System.out::println);
//        //Another way
//        IntStream.generate(() -> (int) (System.nanoTime() % 100)).
//                limit(10).forEach(System.out::println);
//    }


//package com.mmears.record.stream;
//

//
//@Slf4j
//public class StreamPractise {
//

//    @Test
//
//
//    @Test

//
//    @Test
//    public void forEachAndPeek() {
//        //forEach
//        //terminal
//        //Getting the List of Notebook
////        NoteBookReqDTO req = new NoteBookReqDTO();
////        req.setPageIndex(1L);
////        req.setPageSize(5L);
////
////        List<Notebook> notebooks;
////        LambdaQueryWrapper<Notebook> queryWapper = new QueryWrapper<Notebook>().lambda();
////        notebooks = notebookService.list(queryWapper);
////        notebooks.stream()
////                .filter(notebook -> notebook.getStatus() == NoteBookStatus.NORMAL)
////                .forEach(notebook -> System.out.println(notebook.getName()));
//
//

//    }
//
//    @Test
//    public void Optional() {
//        //0.Optional
//        //
//

//
//        //1.findFirst
//        //termimal 兼 short-circuiting 操作
//        //总是返回 Stream 的第一个元素，或者空
//        //返回值类型：Optional。
//        String[] str = new String[]{null, "2", "3", "3"};
//        String findFirst = Stream.of(str)
//                .filter(s -> s != null)
//                .findFirst().get();
//        System.out.println(findFirst);
//
//
//        //2.Reduce
//        //字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
//        //有起始值的 reduce() 都返回具体的对象。而对于第四个示例没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional
//
//        // 字符串连接，concat = "ABCD"
//        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
//        System.out.println("The result of concat is: " + concat);
//
//        // 求最小值，minValue = -3.0
//        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
//        System.out.println("The minimum value is: " + minValue);
//
//        // 求和，sumValue = 11, 有起始值
//        int sumValue = Stream.of(1, 2, 3, 4).reduce(1, Integer::sum);
//        System.out.println("The sum of those values is: " + sumValue);
//
//        // 求和，sumValue = 10, 无起始值
//        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
//        System.out.println("The sum of those values is: " + sumValue);
//
//        // 过滤，字符串连接，concat = "ace"
//        concat = Stream.of("a", "B", "c", "D", "e", "F").
//                filter(x -> x.compareTo("Z") > 0).
//                reduce("", String::concat);
//        System.out.println("The result of concat is: " + concat);
//    }
//
//    @Test

//

//
//    @Test
//    public void match(){
//        List<NoteBookReqDTO> notebooks = new ArrayList<>();
//        List<NoteBookWorkStatus> workStatusList1 = new ArrayList<>();
//        workStatusList1.add(NoteBookWorkStatus.WORKING);
//        List<NoteBookWorkStatus> workStatusList2 = new ArrayList<>();
//        workStatusList2.add(NoteBookWorkStatus.OFFLINE);
//
//        //notebooks.add(new NoteBookReqDTO("name1", "address1", workStatusList1, null));
//    }
//
//    @Test

//
//}