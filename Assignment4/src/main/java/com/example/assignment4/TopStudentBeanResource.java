package com.example.assignment4;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.*;
import java.util.*;
@Singleton
@Path("/topstudent")
public class TopStudentBeanResource {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student getTopStudent() {
        TypedQuery<Student> query = (TypedQuery<Student>) em.createQuery(
                "SELECT s FROM Student s ORDER BY s.cgpa DESC");
        query.setMaxResults(1);
        List<Student> students = query.getResultList();
        if (!students.isEmpty()) {
            return students.get(0);
        }
        return null;
    }
}

