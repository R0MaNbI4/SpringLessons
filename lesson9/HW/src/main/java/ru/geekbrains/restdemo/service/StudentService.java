package ru.geekbrains.restdemo.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.restdemo.model.Student;
import ru.geekbrains.restdemo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.getById(id);
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

    public void delete(Long id) {
        delete(getById(id));
    }
}
