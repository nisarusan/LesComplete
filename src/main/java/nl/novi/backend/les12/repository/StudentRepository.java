package nl.novi.backend.les12.repository;

import nl.novi.backend.les12.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
