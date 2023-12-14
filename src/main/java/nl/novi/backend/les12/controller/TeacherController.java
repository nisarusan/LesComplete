package nl.novi.backend.les12.controller;

import nl.novi.backend.les12.dto.TeacherDto;
import nl.novi.backend.les12.service.TeacherService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    // Dependency Injections METHOD 1
    // oude methode maar minder veilig, want is niet finalized
    // @Autowired
    // private TeacherRepository teacherRepository;

    private final TeacherService service;

    // Nieuwe methode van injecteren door middel van een constructor
    public TeacherController(TeacherService service) {
        this.service = service;
    }


    // get mapping for all teachers
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
    return ResponseEntity.ok(service.findAllTeachers());
    }

    @GetMapping("/after")
    public ResponseEntity<List<TeacherDto>> getTeachersAfter(@RequestParam LocalDate
    date) {
    return ResponseEntity.ok(service.findByDobAfter(date));
    }

    // PostMapping URI include
    // @Valid is to valid the variabel frome DTO
    @PostMapping
    // Object teruggegeven als ResponseEntity want alles is afgeleid van een object
    // dus mogen we alles terugretourneren
    //BindingResult is er nodig om die errors te kunnen weergeven
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        if (br.hasFieldErrors()) {
        StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            // teacherRepository.save(teacher);
            teacherDto = service.createTeacher(teacherDto);
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + teacherDto.id).toUriString());
            return ResponseEntity.created(uri).body(teacherDto);
        }
    }

}
