package proxy.dynamic;

import proxy.quiescent.Person;

import java.lang.reflect.Proxy;

public class DynamicRunner {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        DynamicProxyHandler handler = new DynamicProxyHandler(teacher);

        Person instance = (Person) Proxy.newProxyInstance(handler.getClass().getClassLoader(), teacher.getClass().getInterfaces(), handler);

        //2
//        Person instance = (Person) handler.getProxy();
        instance.study();
    }
}
