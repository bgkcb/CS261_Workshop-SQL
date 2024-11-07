package com.example.crud;
import java.util.List;

public class StudentController {
    private StudentsRepository studentsRepository;

    public StudentController(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public String addStudent(int studentId, String name, int age, String grade) {
        return studentsRepository.addStudent(studentId, name, age, grade);
    }

    public Student getStudent(int studentId) {
        return studentsRepository.getStudent(studentId);
    }

    public String updateStudent(int studentId, String name, int age, String grade) {
        return studentsRepository.updateStudent(studentId, name, age, grade);
    }

    public String deleteStudent(int studentId) {
        return studentsRepository.deleteStudent(studentId);
    }

    public List<Student> getAllStudents() {
        return studentsRepository.getAllStudents();
    }
}
