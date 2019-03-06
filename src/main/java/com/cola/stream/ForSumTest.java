package com.cola.stream;

import java.util.Date;
import java.util.OptionalLong;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * Copyright (C), 杭州未智科技有限公司
 *
 * @author: Cola
 * @date: 2019/03/06 15:28
 * @description:
 */
public class ForSumTest {


    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 0; i <= 50000000000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("FOR            ==>五百亿求和花费的时间为: " + (end - start));


        //-4378596987249509888
        //五百亿求和花费的时间为: 15632



        Long start1 = System.currentTimeMillis();
        //使用StreamAPI
        OptionalLong result = LongStream.rangeClosed(0L, 50000000000L)
                .parallel()
                .reduce(Long::sum);
        long end1 = System.currentTimeMillis();
        System.out.println("StreamAPI      ==>五百亿求和花费的时间为: " + (end1 - start1));




        Long start11 = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTask = new ForkJoinSum(0L,50000000000L);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long t = forkJoinPool.invoke(forkJoinTask);

        long end11 = System.currentTimeMillis();
        System.out.println("ForkJoinTask   ==>五百亿求和花费的时间为: " + (end11 - start11));


        Long start2 = System.currentTimeMillis();
        long sum2 = 0;
        for (long i = 0; i <= 500000000L; i++) {
            sum2 += i;
        }
        long end2 = System.currentTimeMillis();
        System.out.println("FOR            ==>五亿求和花费的时间为: " + (end2 - start2));


        //-4378596987249509888
        //五百亿求和花费的时间为: 15632



        Long start3 = System.currentTimeMillis();
        //使用StreamAPI
        OptionalLong result3 = LongStream.rangeClosed(0L, 500000000L)
                .parallel()
                .reduce(Long::sum);
        long end3 = System.currentTimeMillis();
        System.out.println("StreamAPI      ==> 五亿求和花费的时间为: " + (end3 - start3));



        Long start33 = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTask33 = new ForkJoinSum(0L,500000000L);

        ForkJoinPool forkJoinPool33 = new ForkJoinPool();
        Long t33 = forkJoinPool33.invoke(forkJoinTask33);

        long end33 = System.currentTimeMillis();
        System.out.println("ForkJoinTask   ==>五亿求和花费的时间为: " + (end33 - start33));



        Long start4 = System.currentTimeMillis();
        long sum4 = 0;
        for (long i = 0; i <= 5L; i++) {
            sum4 += i;
        }
        long end4 = System.currentTimeMillis();
        System.out.println("FOR            == >五求和花费的时间为: " + (end4 - start4));


        //-4378596987249509888
        //五百亿求和花费的时间为: 15632



        Long start5 = System.currentTimeMillis();
        //使用StreamAPI
        OptionalLong result5 = LongStream.rangeClosed(0L, 5L)
                .parallel()
                .reduce(Long::sum);
        long end5 = System.currentTimeMillis();
        System.out.println("StreamAPI      ==> 五求和花费的时间为: " + (end5 - start5));



        Long start55 = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTask55 = new ForkJoinSum(0L,5L);

        ForkJoinPool forkJoinPool55 = new ForkJoinPool();
        Long t55 = forkJoinPool55.invoke(forkJoinTask55);

        long end55 = System.currentTimeMillis();
        System.out.println("ForkJoinTask   ==>五求和花费的时间为: " + (end55 - start55));



    }

}
