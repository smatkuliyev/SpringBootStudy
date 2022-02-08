package springBootStudy.controller;

import org.springframework.stereotype.Component;

@Component
public class StudentBean02 implements StudentInterface {
    // pojo --> plan old java object

    private String name;
    private int age;
    private String id;

    public StudentBean02() {
        System.out.println("parametresiz cons2 run edildi...");
    }

    public StudentBean02(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "studentBean02{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public String study() {
        return "bu  yaziyi okuduysan StudentBean02 class'indan geliyorum :-)";
    }
}
