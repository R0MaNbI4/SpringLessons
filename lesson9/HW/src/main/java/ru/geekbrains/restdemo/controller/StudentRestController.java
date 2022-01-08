package ru.geekbrains.restdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.restdemo.dto.StudentDto;
import ru.geekbrains.restdemo.mapper.StudentMapper;
import ru.geekbrains.restdemo.model.Student;
import ru.geekbrains.restdemo.service.StudentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
    StudentMapper studentMapper;
    StudentService studentService;

    public StudentRestController(StudentMapper studentMapper, StudentService studentService) {
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<StudentDto> getAllStudents() {
        return studentService.getAll().stream().map(studentMapper::getDto).collect(Collectors.toList());
    }

    @GetMapping("/info/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentMapper.getDto(studentService.getById(id));
    }

    @PostMapping("/add")
    public StudentDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        Student student = studentMapper.getModel(studentDto);
        studentService.save(student);
        return studentMapper.getDto(student);
    }

    @PutMapping("/update/{id}")
    public StudentDto updateStudent(@PathVariable Long id,
                                    @Valid @RequestBody StudentDto studentDto) {
        Student student = studentMapper.getModel(studentDto);
        student.setId(id);
        studentService.save(student);
        return studentMapper.getDto(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        System.out.println(errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
