package com.ucd.practise.utils;


import com.ucd.practise.model.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {

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
     * Distinct the word from a file, and then turn them to lowercase.
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
        return words;
    }


    /**
     * Checking whether the people list satisfy the requiring.
     * type 1: all of the people are adult
     * type 2: at least one child
     *
     * @param type
     * @param people
     * @return
     */
    public static boolean match(int type, List<Person> people) {
        if (type == 1) {
            boolean isAllAdult = people.stream().
                    allMatch(p -> p.getAge() > 18);
            return isAllAdult;
        } else {
            boolean isThereAnyChild = people.stream().
                    anyMatch(p -> p.getAge() < 12);
            return isThereAnyChild;
        }

    }


    /**
     * Generating random string in 2 type.
     *
     * @param type
     * @return
     */
    public static Stream generator(int type) {
        if (type == 1) {
            Random seed = new Random();
            Supplier<Integer> random = seed::nextInt;
            Stream<Integer> output = Stream.generate(random).limit(10);
            return output;
        } else {
            Stream output = Stream.generate(() -> (int) (System.nanoTime() % 100)).
                    limit(10);
            return output;
        }
    }

    /**
     * Using supplier generate the peron in particular formal.
     * Then printing the output.
     */
    public static void supplier() {
        Stream.generate(new PersonSupplier()).
                limit(10).
                forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));
    }

    /**
     * Stream.iterate
     * iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。
     * 然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推。
     * <p>
     * Using iterator creating a arithmetic progression
     *
     * @param seed  the first elements of the stream.
     * @param limit the limitation of stream.
     * @param diff  the diff of between neighbor elements.
     */
    public static void iterator(int seed, int limit, int diff) {
        Stream.iterate(seed, n -> n + diff).limit(limit).forEach(x -> System.out.print(x + " "));
    }


    /**
     * groupingBy(Function, Collector)
     * groupingBy(Function, Supplier, Collector)
     * <p>
     * Grouping by age and then printing the result.
     */
    public static void groupByAge() {
        Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.groupingBy(Person::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
            System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
        }
    }

    /**
     * partitioningBy(Predicate, Collector)
     * Predicate: boolean
     * partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果来构造返回的数据结构，
     * get(true) 和 get(false) 能即为全部的元素对象。
     * <p>
     * Classify the children from people
     */
    public static void partitioningByAge() {
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());
    }

    /**
     * Reduce
     * 字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce
     * 有起始值的 reduce() 都返回具体的对象。
     * 没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional
     */

    /**
     * Reduce
     * concating the input String array with particular split symbol.
     *
     * @param standard The condition for filter.
     * @param list     The input array which needed to be concat.
     * @param identity In the head of the result which is the identity of the result string
     * @return
     */
    public static String concatString(String standard, String[] list, String identity) {
        String concat = Stream.of(list).
                filter(x -> x.compareTo(standard) > 0).
                reduce(identity, String::concat);
        return concat;
    }

    /**
     * Reduce
     * Using reduce to find the minimum or the maximum.
     *
     * @param nums The double nums
     * @param type min & max
     * @return
     */
    public static Double minOrMax(Double[] nums, String type) {

        if ("min".equalsIgnoreCase(type)) {
            double minValue = Stream.of(nums).reduce(Double.MAX_VALUE, Double::min);
            return minValue;
        } else {
            double maxValue = Stream.of(nums).reduce(Double.MIN_VALUE, Double::max);
            return maxValue;
        }
    }

    /**
     * Reduce
     * The sum of the nums.
     *
     * @param nums The nums
     * @param seed The origin num
     * @return The sum of nums and origin num
     */
    public static Integer sum(Integer[] nums, Integer seed) {
        Integer sumValue;
        if (seed != null) {
            sumValue = Stream.of(nums).reduce(seed, Integer::sum);
        } else {
            sumValue = Stream.of(nums).reduce(Integer::sum).get();
        }
        return sumValue;
    }
}


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
//    }
