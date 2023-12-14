package nl.novi.backend.les12.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import java.time.LocalDate;


import nl.novi.backend.les12.repository.TeacherRepository;
import nl.novi.backend.les12.model.Teacher;
import nl.novi.backend.les12.dto.TeacherDto;
import nl.novi.backend.les12.exception.DuplicateException;

@Service
public class TeacherService {

    // Andere manier van autowired om te praten met repository
    // Er kan nu via Repos gepraat worden met de Teacherrepository;
    // Constructor injection
    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    // Data die binnenkomt is een TeacherDto en niet meer uit de Modelentiteiten
    // Teacherobject komen binnen en die geven we ook terug
    public TeacherDto createTeacher(TeacherDto teacherDto) {

        // instantie van Teacher object
        Teacher teacher = new Teacher();
        // Teacher object vullen
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setSalary(teacherDto.salary);

        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        List<Teacher> teachersWithMatchingName = repos.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

        if(teachersWithMatchingName.size() == 0) {
            repos.save(teacher);
            teacherDto.id = teacher.getId();

            return teacherDto;
        } else {
            throw new DuplicateException("We already found a new entry with this name");
        }
        // Teacher dto id gaan we vullen d.m.v. de teacher getId(die via de entiteit
        // model gaat)
        // de getId is nu toegewezen na het opslaan en wordt nu toegewezen en ingevuld
        // door de getId methode
        // de getId methode is niet aanwezig in de dto omdat dat een private type
        // variabel is;
  
    }

    // find allTeachers
    public List<TeacherDto> findAllTeachers() {
        // Logic to retrieve all teachers from the repository and convert them to DTOs
        List<Teacher> teachers = repos.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher t : teachers) {
            TeacherDto teacherDto = teacherToDto(t);
            teacherDtos.add(teacherDto);
        }
        return teacherDtos;
    }

    public List<TeacherDto> findByDobAfter(LocalDate date) {
        List<Teacher> teachers = repos.findByDobAfter(date);
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher teacher : teachers) {
            teacherDtos.add(teacherToDto(teacher));
        }
        return teacherDtos;

    }

    //mapper functie van dto naar Teacher object
    public Teacher dtoToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setSalary(teacherDto.salary);

        return teacher;
    }

    //mappers functies voor omzetten converten
    public TeacherDto teacherToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.id = teacher.getId();
        dto.firstName = teacher.getFirstName();
        dto.lastName = teacher.getLastName();
        dto.dob = teacher.getDob();
        dto.salary = teacher.getSalary();

        return dto;
    }
}
