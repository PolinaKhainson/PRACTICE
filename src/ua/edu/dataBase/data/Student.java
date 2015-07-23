package ua.edu.dataBase.data;

import java.util.Date;

/**
 * Created by Oxana on 7/13/15.
 */
public class Student extends Audit implements Comparable<Student>{

    @Override
    public int compareTo(Student o) {
        String family=o.getPerson().getLastName();
        return family.compareTo(this.getPerson().getLastName());
    }

    Person person;
    String department;
    Integer course;


    public Student() {
    }

    public Student(Person person) {
        this.person = person;
    }

    public Student(Person person, String department, Integer course) {
        this.person = person;
        this.department = department;
        this.course = course;
    }

    @Override
    public String toString() {
        return
                person.toString() +
                ", " + department +
                ", " + course
                ;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setFirstName(String firstName) {
        this.getPerson().setFirstName(firstName);
    }

    public void setLastName(String lastName){
        this.getPerson().setLastName(lastName);
    }

    public void setBirthDate(Date birthDate){
        this.getPerson().setBirthDate(birthDate);
    }

    public Person getPerson() {
        return person;
    }

    public String getDepartment() {
        return department;
    }

    public Integer getCourse() {
        return course;
    }

    public String getFirstName(){
        return this.getPerson().getFirstName();
    }

    public String getLastName(){
        return this.getPerson().getLastName();
    }

    public Date getBirthDate(){
        return this.getPerson().getBirthDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!person.equals(student.person)) return false;
        if (!department.equals(student.department)) return false;
        return course.equals(student.course);

    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }
    public static Student objectFromStr(String str){
        if(str==null || str.isEmpty())return null;
        String[] ss=str.split(", ");
        Person psn=Person.objectFromStr(ss[0]);
        Student student=new Student(psn);
        student.setDepartment(ss[1]);
        student.setCourse(Integer.valueOf(ss[2]));
        return student;

    }
}
