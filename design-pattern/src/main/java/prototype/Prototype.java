package prototype;


import lombok.Getter;
import lombok.Setter;
import singleton.HungrySingleton;

import java.io.*;

/**
 * 原型模式：深拷贝
 * 使用对象序列化反序列化实现
 */
@Setter
@Getter
public class Prototype implements Cloneable, Serializable {
    private String name;
    private int age;
    private HungrySingleton sigleton;

    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        return deepClone();
    }

    /**
     * 深拷贝
     *
     * @return
     */
    private Prototype deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            Object o = ois.readObject();
            return (Prototype) o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
