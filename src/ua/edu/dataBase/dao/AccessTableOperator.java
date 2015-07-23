package ua.edu.dataBase.dao;



import myFiler.MyFiler;
import ua.edu.dataBase.create.GeneratorDataTable;
import ua.edu.dataBase.data.Access;


/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/15/12
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccessTableOperator {
    String nameAccessTable="Access";
    GeneratorDataTable gen = new GeneratorDataTable(nameAccessTable, false);
    String fullNameAccessTable=gen.getPathToDataBase();
    Access access;
    int numberLine=-1;
    boolean ok=false;

    public Access createAccess(String nameRole, String descriptionRole)
    {
       access =findAccess(nameRole);
       if(access !=null){
           access.setDescriptions(descriptionRole);
           ok= MyFiler.rewriteLineInFile(fullNameAccessTable, numberLine, access.toString());
       }else {
           access =new Access(nameRole, descriptionRole);
           ok=gen.setObjectToDataBase(nameAccessTable, access);
       }


      return  ok? access :null;
    }

    public Access findAccess(String nameRole)
    {
      ok= MyFiler.findTextInFile(fullNameAccessTable, nameRole);
      numberLine=ok?MyFiler.getNumberLine():-1;
      if(ok){
        String toString=MyFiler.getLineFileByNumber(fullNameAccessTable, numberLine);
        access =new Access(toString);
        return access;
      }
      return null;
    }
}
