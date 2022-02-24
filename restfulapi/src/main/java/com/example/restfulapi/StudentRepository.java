package com.example.restfulapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean findStudentByEmail(String email);

    boolean findStudentById(Long studentId);

    boolean findStudentByName(String name);

    boolean findStudentByDob(LocalDate dob);
}
