package singleton;

import java.io.Serializable;

public class SerializObj implements Serializable {
    static SerializObj instance = new SerializObj();

    public static SerializObj getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;
    }
}
