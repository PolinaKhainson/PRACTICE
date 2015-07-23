package ua.edu.dataBase.dao;


import ua.edu.dataBase.create.GeneratorDataTable;
import ua.edu.dataBase.data.Access;
import ua.edu.dataBase.data.Person;
import ua.edu.dataBase.data.PersonAccess;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/19/12
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonAccessTableOperator {
    public static String personAccessTableName="PersonAccessTable";
    private static GeneratorDataTable gen=new GeneratorDataTable(personAccessTableName, false);
    private static AccessTableOperator accessTableOperator=new AccessTableOperator();
/*
    private Person person;
    boolean ok=false;
*/
    private static PersonAccess psnAccess;
    /*create PersonAccess*/
    public PersonAccess createPersonAccess(Person person, String nameRole  )
    {
        Access access = accessTableOperator.createAccess(nameRole, nameRole);
        psnAccess=new PersonAccess(person, access);
        gen.setObjectToDataBase(personAccessTableName, psnAccess);
        return psnAccess;
    }

    public PersonAccess createPersonAccess(Person person )
    {
        return createPersonAccess(person, Access.Quest);
    }

    public PersonAccess createPersonAccess(Person person, String nameRole, String descriptionRole  )
    {
        Access access = accessTableOperator.createAccess(nameRole, descriptionRole);
        PersonAccess psnAccess=new PersonAccess(person, access);
        gen.setObjectToDataBase(personAccessTableName, psnAccess);
        return psnAccess;
    }

    public PersonAccess createPersonAccess(Person person, Access access)
    {
        gen.createDataBase(personAccessTableName);
        PersonAccess psnAccess=new PersonAccess(person, access);
        gen.setObjectToDataBase(personAccessTableName, access);
        return psnAccess;
    }

}
