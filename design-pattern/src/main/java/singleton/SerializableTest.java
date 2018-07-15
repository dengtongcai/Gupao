package singleton;

import java.io.*;

/**
 * 普通得java对象实现序列化之后，在反序列化的过程中会创建新的实例，通过重写readResolve可以解决
 * 或者直接使用Enum，可以不被反序列化破坏
 */
public class SerializableTest {
    static EnumSingleton instance = EnumSingleton.INSTANCE.getInstance();
    static SerializObj serializ = EnumSingleton.INSTANCE.getSerializObj();
    static SerializObj serializ1 = SerializObj.getInstance();


    static ObjectOutputStream oos = null;
    static ObjectInputStream ois = null;

    public static void main(String[] args) {
        try {
            //序列化
            oos = new ObjectOutputStream(new FileOutputStream("./serializObjResove1.txt"));
            oos.writeObject(serializ1);

            //反序列化
            ois = new ObjectInputStream(new FileInputStream("./serializObjResove1.txt"));
            Object o = ois.readObject();

            System.out.println(serializ1);
            System.out.println(o);
            System.out.println(serializ1 == o);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
