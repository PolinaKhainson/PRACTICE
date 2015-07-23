package ua.edu.dataBase.data;

import java.util.StringTokenizer;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/14/12
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Access extends Audit{
    public static String Owner="owner";
    public static String Quest="quest";
    public String nameRole;
    public String descriptions;

    public Access(String nameRole, String descriptions) {
        super();
        this.nameRole = nameRole;
        this.descriptions = descriptions;
    }

    public Access(String fromString) {
        super();
        this.getFromString(fromString);
    }

    @Override
    public String toString() {
        return "Access{" +
                "nameRole='" + nameRole + '\'' +
                ", descriptions='" + descriptions + '\'' +
                ", created='"+created + '\'' +
                ", changed='"+changed + '\''+
                '}';
    }
    public Access getFromString(String toString)
    {
     StringTokenizer token=new StringTokenizer(toString, "\'");
     String[] words=new String[token.countTokens()];
     int i=-1;
     while (token.hasMoreTokens()){
      words[++i]=token.nextToken();
     }
     if(i!=-1){
         setNameRole(words[0].substring(19));
         setDescriptions(words[1].substring(14));
         return (new Access(getNameRole(),getDescriptions()));
     }
     else return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Access)) return false;

        Access that = (Access) o;

        if (descriptions != null ? !descriptions.equals(that.descriptions) : that.descriptions != null) return false;
        if (nameRole != null ? !nameRole.equals(that.nameRole) : that.nameRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameRole != null ? nameRole.hashCode() : 0;
        result = 31 * result + (descriptions != null ? descriptions.hashCode() : 0);
        return result;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
