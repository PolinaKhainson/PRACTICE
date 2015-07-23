package ua.edu.dataBase.dao;


import myFiler.MyFiler;
import ua.edu.dataBase.create.GeneratorDataTable;
import ua.edu.dataBase.data.Access;
import ua.edu.dataBase.data.Person;


import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/15/12
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonTableOperator {

    public static String personTableName="PersonTable";

    private static GeneratorDataTable gen=new GeneratorDataTable(personTableName, false);
    private static AccessTableOperator accessTableOperator=new AccessTableOperator();
    private Person person;
    boolean ok=false;

    public Person createPerson(String firstName, String lastName, Date birthDay)
    {
      Access access = accessTableOperator.createAccess(Access.Quest, Access.Quest);
      person=new Person(firstName, lastName, birthDay, Access.Quest);

      ok=gen.setObjectToDataBase(personTableName, person );
     return (ok? person:null);
    }

    public Person createPerson(String firstName, String lastName, Date birthDay, String access)
    {
        accessTableOperator.createAccess(access,access);
        person=new Person(firstName, lastName, birthDay,access );
        ok=gen.setObjectToDataBase(personTableName, person );
        return (ok? person:null);
    }

    public Person findPerson(String firstName, String lastName, Date birthDay)
    {
      if(gen.isExistDataBase("Person"))
      {
          person=new Person(firstName,lastName, birthDay);
          ok= MyFiler.findTextInFile(gen.getPathToDataBase() + personTableName, person.toString());
      }else return null;
     return ok?person:null;
    }


}
