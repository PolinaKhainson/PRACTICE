package ua.edu.dataBase.data;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/14/12
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonAccess extends Audit{
    public Person person;
    public Access access;

    public PersonAccess(Person person) {
        super();
        this.person = person;
        this.access = new Access(Access.Quest, Access.Quest);
    }
    public PersonAccess(Person person, String nameRole) {
        super();
        this.person = person;
        this.access = new Access(nameRole, nameRole);
    }

    public PersonAccess(Person person, Access access) {
        super();
        this.person = person;
        this.access = access;
    }

    @Override
    public String toString() {
        return "PersonAccess{" +
                "person=" + person +
                ", access=" + access +
                ", created='"+created + '\'' +
                ", changed='"+changed + '\''+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonAccess)) return false;

        PersonAccess that = (PersonAccess) o;

        if (!access.equals(that.access)) return false;
        if (!person.equals(that.person)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + access.hashCode();
        return result;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }
}
