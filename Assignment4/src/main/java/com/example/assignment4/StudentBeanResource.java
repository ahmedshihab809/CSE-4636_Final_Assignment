package com.example.assignment4;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Stateless
@Path("/students")
public class StudentBeanResource {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Student createStudent(@QueryParam("name") String name,
                                 @QueryParam("semester") int semester,
                                 @QueryParam("cgpa") float cgpa) {
        Student student = new Student(name, semester, cgpa);
        em.persist(student);
        return student;
    }

    @GET
    @Path("/{studentId}/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStudentName(@PathParam("studentId") String studentId) {
        Student student = em.find(Student.class, studentId);
        return student.getName();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentWithHigherCgpa(@QueryParam("studentId1") String studentId1,
                                            @QueryParam("studentId2") String studentId2) {
        Student student1 = em.find(Student.class, studentId1);
        Student student2 = em.find(Student.class, studentId2);
        if (student1.getCgpa() > student2.getCgpa()) {
            return student1;
        } else {
            return student2;
        }
    }

    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student updateStudentName(@PathParam("studentId") String studentId,
                                     @QueryParam("name") String name) {
        Student student = em.find(Student.class, studentId);
        student.setName(name);
        em.merge(student);
        return student;
    }
}

