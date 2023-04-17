package com.example.assignment4;
import jakarta.persistence.*;
public class CreateStudent {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("shihab_student_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();
        Student student = new Student();
        student.setName("Shihab Ahmed");
        student.setSemester(6);
        student.setCgpa(3.92F);
        entityManager.persist(student);
        entityManager.close();
        entityManagerFactory.close();

    }
}
