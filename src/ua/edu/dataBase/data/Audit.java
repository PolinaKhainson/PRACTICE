package ua.edu.dataBase.data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Asya
 * Date: 4/14/12
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class Audit {
    public Date created;
    //public PersonAccess createdBy;
    public Date changed=new Date();
    //public PersonAccess changedBy;

    public Audit() {
        this.created = new Date();
        this.changed= this.created;
    }

/*
    public Audit(Person createdBy) {
        this.currentDate = new Date();
        this.createdBy = new PersonAccess(createdBy, Access.Owner);
        this.changed= this.currentDate;
        this.changedBy=this.createdBy;

    }

    public Audit(Date currentDate, PersonAccess createdBy) {
        this.currentDate = currentDate;
        this.createdBy = createdBy;
        this.changed= this.currentDate;
        this.changedBy=this.createdBy;

    }
*/

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date currentDate) {
        this.created = currentDate;
    }

/*
    public PersonAccess getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(PersonAccess createdBy) {
        this.createdBy = createdBy;
    }
*/

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }

/*
    public PersonAccess getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(PersonAccess changedBy) {
        this.changedBy = changedBy;
    }
*/
}
