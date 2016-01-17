/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Grade;
import entity.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bechw
 */
public class facade {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    
    public void createObject(Object o){
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();
        em.close();
    }
    
    public Person findPerson(long id){
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class, id);
    }
    
    public Grade findGrade(long id){
        EntityManager em = emf.createEntityManager();
        return em.find(Grade.class, id);
    }
    
    public void editPerson(int id,Date d,String first, String last,boolean married,long supervisorId){
        EntityManager em = emf.createEntityManager();
        Person p = findPerson(id);
        Person p1 = findPerson(supervisorId);

        p.setBirthDate(d);
        p.setFirstName(first);
        p.setLastName(last);
        p.setIsMarried(married);
        p.setSupervisor(p1);
        
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }
    
    public void deletePerson(long id){
        EntityManager em = emf.createEntityManager();
        Person p = findPerson(id);
        
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }
    
    public void deleteGrade(long id){
        EntityManager em = emf.createEntityManager();
        Grade p = findGrade(id);
        
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }
    
    public void assignSupervisor(long supervisorId, long minion){
        EntityManager em = emf.createEntityManager();
        
        Person p = findPerson(supervisorId);
        Person p1 = findPerson(minion);
        
        p.addSupervisee(p1);
        
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }
    
    public List<Person> getSupervisees(long id){
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        
        return p.getSupervisees();
    }
}
