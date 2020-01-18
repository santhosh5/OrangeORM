package santhosh.pm.ormrecord;

import santhosh.pm.ormorange.OrangeORMRecord;

public class StudentOrm extends OrangeORMRecord {

    public String name;

    public String marks;

    public StudentOrm(){

    }

    public StudentOrm ( String name, String marks ) {
        this.name = name;

        this.marks = marks;
    }

    public String getName ( ) {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getMarks ( ) {
        return marks;
    }

    public void setMarks ( String marks ) {
        this.marks = marks;
    }
}
