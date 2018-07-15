package singleton;

/**
 * 双重检查锁单例
 */
public class LazyLocked {
    public static LazyLocked lazy = null;

    public static LazyLocked getLazy() {

        if (null == lazy) {
            synchronized (LazyLocked.class) {
                if (null == lazy) {
                    lazy = new LazyLocked();
                }

            }
        }
        return lazy;
    }
}
