package ru.geekbrains.restdemo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.geekbrains.restdemo.dto.StudentDto;
import ru.geekbrains.restdemo.model.Student;

@Component
public class StudentMapper {
    ModelMapper modelMapper;

    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StudentDto getDto(Student student) {
        StudentDto studentDto = modelMapper.map(student, StudentDto.class);
        return studentDto;
    }

    public Student getModel(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        return student;
    }
}
