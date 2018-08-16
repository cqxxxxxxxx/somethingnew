package com.cqx.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Intermediate: 做数据映射/过滤操作，然后返回个新的流，交给下个操作使用。
 * Terminal: 一个流只有一个terminal操作，执行后流就被消费掉了。terminal操作执行才真正开始流的遍历。
 * short-circuiting: 当操作一个无限大的 Stream，而又希望在有限时间内完成操作，则在管道内拥有一个 short-circuiting 操作是必要非充分条件。
 *
 * 1.Intermediate：
 *      map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
 *      peek、 limit、 skip、 parallel、 sequential、 unordered
 * 2.Terminal：
 *      forEach、 forEachOrdered、 toArray、 reduce、 collect、
 *      min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
 * 3.Short-circuiting：
 *      anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
 *
 * Created by cqx on 2017/2/3.
 */
public class StreamDemo {

    //todo put some java8-stream learning demos

    /**
     * stream的构造
     */
    public void streamCreateTest(){
        // 1. Individual values
        Stream stream1 = Stream.of("a", "b", "c");

        // 2. Arrays
        String [] strArray = new String[] {"a", "b", "c"};
        Stream stream2 = Stream.of(strArray);
        Stream stream3 = Arrays.stream(strArray);

        // 3. Collections
        List<String> list = Arrays.asList(strArray);
        Stream stream4 = list.stream();
    }


    /**
     * intermediate->map:把stream中的每个元素映射成另外个元素
     * intermediate->flatMap:一对多的映射
     */
    @Test
    public void mapTest(){
        List<Integer> nums = Arrays.asList(1,2,3,4,5);
        //把nums中每个元素平方后转换成List并放到squareNums中
        List<Integer> squareNums = nums.stream()
                .map(integer -> integer*integer)
                .collect(Collectors.toList());

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        //把inputStream中的结构扁平化
        Stream<Integer> outputStream = inputStream
                .flatMap((childList) -> childList.stream());

        outputStream.forEach(System.out::println);  //输出1，2，3，4，5，6这样的

        int[][] ints = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };

//        Stream.of(ints).flatMap(array -> Stream.of(array).forEach(System.out::println));
    }


    /**
     * intermediate->peek: 操作每一个元素并返回个新的跟之前的一样的流
     * terminal->forEach: 接收个lambda表达式，在每个元素上执行该表达式，并消费掉该stream
     */
    @Test
    public void peekAndForeach(){
        List<Integer> nums = Arrays.asList(1,2,3,4,5);

        nums.stream()
                .peek(System.out::println)
                .forEach(System.out::println);
    }


    @Test
    public void reduce() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        // 可以给一个起始种子值
        Integer sum = nums.stream().reduce(10, Integer::sum);
        System.out.println(sum);

        // 没有起始值时返回为Optional类型
        Optional<Integer> sumOptional = nums.stream().reduce(Integer::sum);
        System.out.println(sumOptional.get());


        //max reduce
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Integer maxReduce = integerStream.reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(maxReduce);

        // max
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4, 5);
        OptionalInt max = integerStream1.mapToInt(i -> i).max();
        System.out.println(max.getAsInt());

        //构造字符串流
        List<String> strs = Arrays.asList("H", "E", "L", "L", "O");
        // reduce
        String concatReduce = strs.stream().reduce("", String::concat);
        System.out.println(concatReduce);
    }


}
