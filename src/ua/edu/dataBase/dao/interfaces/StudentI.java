package ua.edu.dataBase.dao.interfaces;

import ua.edu.dataBase.create.GeneratorDataTable;
import ua.edu.dataBase.data.Person;
import ua.edu.dataBase.data.Student;

import java.util.Date;
import java.util.List;

/**
 * Created by Oxana on 7/13/15.
 */
public interface StudentI {
    public final static GeneratorDataTable gen=new GeneratorDataTable("Student");
    public Student createStudent(String firstName, String lastName, Date birthDay, String department, Integer course);
    public boolean deleteStudent(String firstName, String lastName, Date birthDay);
    public Student findStudent(String firstName, String lastName, Date birthDay);
    public Student findStudent(Person person);
    public List<Student> listStudents();
    public boolean saveFirstName(Student st,String firstName);
    public boolean saveLastName(Student st,String lastName);
    public boolean saveBirthDate(Student st,Date birthDate);
    public boolean saveDepartment(Student st,String department);
    public boolean saveCourse(Student st,Integer course);
}
