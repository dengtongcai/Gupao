package singleton;

public class Lazy {
    public static Lazy lazy = null;

    public static Lazy getLazy() {
        if (null == lazy) {
            lazy = new Lazy();
            return lazy;
        } else {
            return lazy;
        }
    }
}
