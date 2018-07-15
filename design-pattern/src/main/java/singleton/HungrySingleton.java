package singleton;

import java.io.Serializable;

/**
 * 单例模式
 */
public class HungrySingleton implements Serializable {

    private static final HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getHungrySingleton() {
        return instance;
    }
}
