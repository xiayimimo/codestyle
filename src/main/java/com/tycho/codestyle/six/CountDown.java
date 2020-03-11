package com.tycho.codestyle.six;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
/**
 * 六-14 【推荐】使用 CountDownLatch 进行异步转同步操作，每个线程退出前必须调用 countDown
方法，线程执行代码注意 catch 异常，确保 countDown 方法被执行到，避免主线程无法执行
至 await 方法，直到超时才返回结果。
 *
 */
public class CountDown {

    private static final int THREAD_COUNT_NUM = 7;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT_NUM);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= THREAD_COUNT_NUM; i++) {
            int index = i;
            new Thread(() -> {
                try {
                    System.out.println("第" + index + "颗龙珠已收集到！");
                    //模拟收集第i个龙珠,随机模拟不同的寻找时间
                    Thread.sleep(new Random().nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每收集到一颗龙珠,需要等待的颗数减1
                countDownLatch.countDown();
            }).start();
        }
        //等待检查，即上述7个线程执行完毕之后，执行await后边的代码
        countDownLatch.await();
        System.out.println("集齐七颗龙珠！召唤神龙！");
    }

}
