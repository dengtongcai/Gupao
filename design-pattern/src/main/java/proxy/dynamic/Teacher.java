package proxy.dynamic;

import proxy.quiescent.Person;

public class Teacher implements Person {


    @Override
    public void study() {
        System.out.println("老师自学新知识");
    }
}
