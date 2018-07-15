package singleton;

import java.util.concurrent.CountDownLatch;

public class LazyTest {
    private static final int count = 15000;
    static CountDownLatch cdl = new CountDownLatch(count);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {

            new Thread((Runnable) () -> {
                try {
                    Lazy instance = Lazy.getLazy();
                    System.out.println(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
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

        System.out.println(System.currentTimeMillis() - start);
    }
}
