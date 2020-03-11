package com.tycho.codestyle.six;

import java.util.concurrent.ThreadLocalRandom;
/**
 * 六-15 【推荐】避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一
seed 导致的性能下降。
 *
 */
public class ThreadRandom {
	private static final ThreadLocalRandom RANDOM =
            ThreadLocalRandom.current();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Player().start();
        }
    }

    private static class Player extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + ": " + RANDOM.nextInt(100));
        }
    }
}
