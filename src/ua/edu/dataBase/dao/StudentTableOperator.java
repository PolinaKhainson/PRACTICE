package ua.edu.dataBase.dao;

import com.utils.file.Filer;
import ua.edu.dataBase.data.Person;
import ua.edu.dataBase.data.Student;
import ua.edu.dataBase.dao.interfaces.StudentI;

import java.io.File;
import java.util.*;

/**
 * Created by Oxana on 7/13/15.
 */
public class StudentTableOperator implements StudentI{
    Student student;
    List<Student> list;

    @Override
    public Student createStudent(String firstName, String lastName, Date birthDay, String department, Integer course) {
        Person psn= new Person(firstName, lastName, birthDay);
        student=new Student(psn,department, course);
        if(findStudent(psn)==null)
             Filer.appendFile(new File(gen.getPathToDataBase()), student.toString() + "\n");
        return student;
    }

    @Override
    public boolean deleteStudent(String firstName, String lastName, Date birthDay) {
        Person person=new Person(firstName, lastName, birthDay);
        String strFile=Filer.readFile(new File(gen.getPathToDataBase()), true, false);
        int index=strFile.indexOf(person.toString());
        if (index==-1) return false;
        String str=strFile.substring(index);
        int index1=(str).indexOf("\n");
        if (index1==-1) return false;
        StringBuffer buffer=new StringBuffer(
                strFile.substring(0,index)
                +((str.length()==(index1+1))?"":str.substring(index1+1))
        );
        System.out.println(buffer.toString());
        Filer.rewriteFile(new File(gen.getPathToDataBase()),
                strFile.substring(0, index) + strFile.substring(index + index1 + 1));
        return false;
    }

    @Override
    public Student findStudent(String firstName, String lastName, Date birthDay) {
        Person person=new Person(firstName, lastName, birthDay);

        return findStudent(person);
    }

    @Override
    public Student findStudent(Person person) {

        String strFile=Filer.readFile(new File(gen.getPathToDataBase()), true, false);
        int index=strFile.indexOf(person.toString());
        if (index==-1) return null;
        int index1=(strFile.substring(index)).indexOf("\n");
        if (index1==-1) return null;
        String strStudent=strFile.substring(
                index + person.toString().length()+1,
                index + index1+1);
        System.out.println(strStudent);
        String[] strStud=strStudent.split(", ");
        System.out.println(strStud[0]);
        System.out.println(strStud[1]);
        String department=strStud[0].trim();
        String course=strStud[1].trim();
        return new Student(person,department, Integer.valueOf(course));
    }
    public List<Student> listStudents(){
      list=new ArrayList<Student>();
      String strFile=Filer.readFile(new File(gen.getPathToDataBase()), true, false);
      String ss[]=strFile.split("\n");
        System.out.println(ss.length);
      for (String s:ss) {
          if(!s.isEmpty())
             list.add(Student.objectFromStr(s));
      }
      for (Student st:list){
          System.out.println(st.toString());
      }
     return list;
    }

    @Override
    public boolean saveFirstName(Student st, String firstName) {
        if(findStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate())!=null)
            deleteStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate());

        Student student=createStudent(firstName, st.getPerson().getLastName(), st.getPerson().getBirthDate(),st.getDepartment(),st.getCourse());
        return student!=null;
    }

    @Override
    public boolean saveLastName(Student st,String lastName) {
        if(findStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate())!=null)
            deleteStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate());

        Student student=createStudent(st.getPerson().getFirstName(), lastName, st.getPerson().getBirthDate(),st.getDepartment(),st.getCourse());
        return student!=null;
    }

    @Override
    public boolean saveCourse(Student st, Integer course) {
        if(findStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate())!=null)
            deleteStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate());

        Student student=createStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate(),st.getDepartment(),course);
        return student!=null;


    }

    @Override
    public boolean saveDepartment(Student st, String department) {
        if(findStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate())!=null)
            deleteStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate());

        Student student=createStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate(), department, st.getCourse());
        return student!=null;


    }

    @Override
    public boolean saveBirthDate(Student st, Date birthDate) {
        if(findStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate())!=null)
            deleteStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), st.getPerson().getBirthDate());

        Student student=createStudent(st.getPerson().getFirstName(), st.getPerson().getLastName(), birthDate, st.getDepartment(),st.getCourse());
        return student!=null;


    }





    public static void main(String[]args){
        StudentTableOperator st=new StudentTableOperator();
/*
        st.createStudent("Oxana", "Dudnik", new Date(1963, 9, 28),"physic", 1);
      st.createStudent("Oxana1", "Dudnik1", new Date(1963, 9, 28),"physic", 1);

*/
       // Person.objectFromStr("Oxana Dudnik 3.9.1963 null null null, physic, 1");

/*
        if(st.findStudent("Oxana2", "Dudnik2", new Date(1963, 9, 28))!=null)
            st.deleteStudent("Oxana2", "Dudnik2", new Date(1963, 9, 28));
*/
        Calendar c = new GregorianCalendar(1963,9, 28);
        Date date=c.getTime();
        System.out.println(date.toString());
        st.createStudent("Oxana", "Dudnik", date,"physic", 1);
        st.createStudent("Tanya", "Dudnik1", date,"mathematic", 1);

        //Student student=st.createStudent("Oxana2", "Dudnik22", new Date(1963, 9, 28), "physic", 1);
        //st.deleteStudent("Oxana2", "Dudnik2222", new Date(1963, 9, 28));
        //st.save(student,"Dudnik2222");
        st.listStudents();

        st.findStudent(new Person("Oxana", "Dudnik", date));
    }
}
