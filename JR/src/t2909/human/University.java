package t2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private List<Student> students = new ArrayList<>();

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

    public University(String name, int age) {
        //super(name, age, 0);
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student s1 = null;
        for (Student s: students) {
            if(s.getAverageGrade() == averageGrade){
                s1 = s;
                break;
            }
        }
        return s1;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student s1 = students.get(0);
        double average = s1.getAverageGrade();
        for(Student s: students){
            if(s.getAverageGrade() > average){
                s1 = s;
                average = s.getAverageGrade();
            }
        }
        return s1;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        Student s1 = students.get(0);
        double average = s1.getAverageGrade();
        for(Student s: students){
            if(s.getAverageGrade() < average){
                s1 = s;
                average = s.getAverageGrade();
            }
        }
        return s1;
    }

    public void expel(Student student) {
        //TODO:
        if(students.contains(student)){
            students.remove(student);
        }
    }
}