package singleton;

import java.util.concurrent.CountDownLatch;

public class HungrySingletonTest {
    private static final int count = 15;
    static CountDownLatch cdl = new CountDownLatch(count);

    public static void main(String[] args) {
        for (int i = 0; i < count; i++) {

            new Thread((Runnable) () -> {
                try {
//                    cdl.await(5L, TimeUnit.SECONDS);
                    HungrySingleton instance = HungrySingleton.getHungrySingleton();
                    System.out.println(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                    System.out.println(Thread.currentThread().getName() + "--执行完毕--" + cdl.getCount());
                }
            }).start();
        }

        try {
            System.out.println("主线程等待" + cdl.getCount());
            cdl.await();
            System.out.println("主线程停止等待" + cdl.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
