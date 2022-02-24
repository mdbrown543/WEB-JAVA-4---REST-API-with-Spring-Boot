package com.example.restfulapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student newStudent){
        if(studentRepository.findStudentByEmail(newStudent.getEmail())){
            throw new IllegalStateException("Student already exists");
        } else{
            studentRepository.save(newStudent);
        }
    }

    public void deleteStudent(Long studentId) {
        if(studentRepository.findStudentById(studentId)){
            studentRepository.deleteById(studentId);
        } else{
            throw new IllegalStateException("Student not found");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email, LocalDate dob){

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student not found"));
        if(studentRepository.findStudentByName(name) && name != null){
            throw new IllegalStateException("Student name already exists");
        } else{
            student.setName(name);
        }

        if(studentRepository.findStudentByEmail(email) && email != null){
            throw new IllegalStateException("Student email already exists");
        } else{
            student.setEmail(email);
        }

        if(studentRepository.findStudentByDob(dob) && dob != null){
            throw new IllegalStateException("Student date of birth already exists");
        } else{
            student.setDob(dob);
        }

    }


}









