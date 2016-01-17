package test;

import entity.Employee;
import entity.Grade;
import entity.Person;
import facade.facade;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bechw
 */
public class tester {
    
    public static void main(String[] args) {
        facade f = new facade();
        Date date = new Date(2016,01,13);
        
        Person p = new Person("fname","lname",date,20,false);
        Person p1 = new Person("fname1","lname1",date,20,false);
        Grade g = new Grade("math",12);
        Employee e = new Employee(12,200,"high");
        e.setFirstName("firstEmp");
        e.setBirthDate(date);
        e.setLastName("lastEmp");
        e.setSoSecNr(1234);
       
        f.createObject(p);
        f.createObject(p1);
        f.createObject(g);
        f.createObject(e);
        
        System.out.println(f.findPerson(2).getFirstName());
        System.out.println(f.findGrade(3).getName());
        f.editPerson(2, date, "edittedFirst", "last", false,1);
        System.out.println(f.findPerson(2).getFirstName());
        f.assignSupervisor(1, 4);
        List<Person> list = f.getSupervisees(1);
        for (Person l : list) {
            System.out.println(l);
        }
    }
}
