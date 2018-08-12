package proxy.quiescent;

public class StudentProxy implements Person {
    private Student stu;

    public StudentProxy(Person stu) {
        if (stu instanceof Student) {
            this.stu = (Student) stu;
        }
    }

    @Override
    public void study() {
        System.out.println("学习前先吃饭");
        stu.study();
    }
}
