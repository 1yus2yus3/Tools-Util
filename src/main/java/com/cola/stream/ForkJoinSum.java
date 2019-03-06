package com.cola.stream;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Copyright (C), 杭州未智科技有限公司
 *
 * @author: Cola
 * @date: 2019/03/06 15:36
 * @description:
 */
public class ForkJoinSum extends RecursiveTask {

    private long start;
    private long end;

    //临界值
    private final long THRESHHOLD = 10000L;

    public ForkJoinSum() {
    }

    public ForkJoinSum(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Object compute() {
        //任务分割
        if(end - start <= THRESHHOLD){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            long mid = (start + end)/2;
            ForkJoinSum left = new ForkJoinSum(start,mid);
            left.fork();//分支

            ForkJoinSum right = new ForkJoinSum(mid+1,end);
            right.fork();

            return Long.parseLong(left.join().toString()) + Long.parseLong(right.join().toString());

        }
    }


    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        ForkJoinTask<Long> forkJoinTask = new ForkJoinSum(0L,50000000000L);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long t = forkJoinPool.invoke(forkJoinTask);

        long end = System.currentTimeMillis();
        System.out.println("五百亿求和花费的时间为: " + (end - start));

        //五百亿求和花费的时间为: 11513
    }
}
