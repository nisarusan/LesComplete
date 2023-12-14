package nl.novi.backend.les12.controller;


import nl.novi.backend.les12.model.Student;
import nl.novi.backend.les12.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;


    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    //Deze nog niet af deze name method
    // @GetMapping("/name")
    // public ResponseEntity<List<Student>> getStudentName(@RequestParam String name) {
    //     return ResponseEntity.ok(studentRepository.findStudentBy(name));
    // }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + student.getStudentNr()).toUriString());
        return ResponseEntity.created(uri).body(student);
    }



}
