package ua.edu.dataBase.create;


import myFiler.MyFiler;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/14/12
 * Time: 6:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneratorDataTable {

    public String nameDataBase;
   // private String mainDir="\\src\\ua\\od\\edu\\resources\\dataBase\\";
    private String mainDir="\\dataBases\\";
    private String currentDir="";
    private String dataBaseDir="";
    public String pathToDataBase;
    public boolean ok=false;
    public static String error="";
    /*constructor*/
    public GeneratorDataTable() {
        this.setNameDatabase(nameDataBase);
        currentDir= MyFiler.getCurrentDirectory();
    }
    public GeneratorDataTable(String nameDatabase) {
        this();
        this.setNameDatabase(nameDatabase);
        dataBaseDir=currentDir+mainDir;
        ok=MyFiler.createDir(dataBaseDir);
        pathToDataBase=dataBaseDir+nameDatabase;
        ok=MyFiler.createFile(pathToDataBase);

        if (ok)
        {
          System.out.println("DataBase generated successfully!");
          this.setPathToDataBase(pathToDataBase);
        } else
        {
          error="Attention!!!! DataBase did not generate!";
          System.err.println(error);

        }
    }

    public String getPathToDataBase(String nameDatabase)
    {
        currentDir=MyFiler.getCurrentDirectory()+mainDir +nameDatabase;
      return currentDir;
    }
    public GeneratorDataTable(String nameDatabase, boolean deleteExistFile) {

        this();
        this.setNameDatabase(nameDatabase);
        dataBaseDir=currentDir+mainDir;
        ok=MyFiler.createDir(dataBaseDir);
        pathToDataBase=dataBaseDir+nameDatabase;
        ok=MyFiler.createFile(pathToDataBase, deleteExistFile);

        if (ok)
        {
            System.out.println("DataBase generated successfully!");
            this.setPathToDataBase(pathToDataBase);
        } else
        {
            error="Attention!!!! DataBase did not generate!";
            System.err.println(error);
        }
    }

    public boolean createDataBase(String nameDataBase)
    {
        if(isExistDataBase(nameDataBase))return true;

        this.setNameDatabase(nameDataBase);
        currentDir=MyFiler.getCurrentDirectory();
        dataBaseDir=currentDir+mainDir;
        ok=MyFiler.createDir(dataBaseDir);
        pathToDataBase=dataBaseDir+nameDataBase;
        ok=MyFiler.createFile(pathToDataBase);

        if (ok)
        {
            System.out.println("DataBase generated successfully!");
            this.setPathToDataBase(pathToDataBase);
        } else
        {
            error="Attention!!!! DataBase did not generate!";
            System.err.println(error);
        }

        return ok;
    }
    public boolean isExistDataBase(String nameDataBase)
    {
      return MyFiler.readFile(getPath(nameDataBase));
    }
    /*getPathFromNameBase*/
    private String getPath(String nameDataBase)
    {
        currentDir=MyFiler.getCurrentDirectory();
        dataBaseDir=currentDir+mainDir;
        this.setPathToDataBase(dataBaseDir+nameDataBase);

     return this.getPathToDataBase();
    }
   /*getObjectDatabase*/
    public Object getDataBaseObject(String nameDatabase)
    {
      return MyFiler.readTextFromFile(getPath(nameDatabase));
    }
    /*setObjectToDataBase*/
    public boolean setObjectToDataBase(String nameDatabase, Object obj)
    {
      MyFiler.setNameFile(getPath(nameDatabase));
      ok=MyFiler.appendTextIntoFile("\n"+"\r"+obj.toString());
      error=error+"\n"+"\r"+MyFiler.getError();
     return ok;
    }
   /*getters&setters*/

    public String getPathToDataBase() {
        return pathToDataBase;
    }

    public void setPathToDataBase(String pathToDataBase) {
        this.pathToDataBase = pathToDataBase;
    }

    public String getNameDatabase() {
        return nameDataBase;
    }

    public void setNameDatabase(String nameDatabase) {
        this.nameDataBase = nameDatabase;
    }

    public static void main(String[]args){
        GeneratorDataTable generatorDataTable=new GeneratorDataTable();
        generatorDataTable.createDataBase("Student");
    }
}
