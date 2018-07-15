package prototype;

import singleton.HungrySingleton;

public class PrototypeDeep {

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype origin = new Prototype();
        origin.setName("datoucai");
        origin.setAge(12);
        origin.setSigleton(new HungrySingleton());

        Prototype clone = origin.clone();
        clone.setName("datoucai");

        System.out.println(origin);
        System.out.println(clone);
        System.out.println(origin == clone);
        System.out.println(clone.getName() == origin.getName());
        System.out.println(origin.getSigleton() == clone.getSigleton());

    }
}
