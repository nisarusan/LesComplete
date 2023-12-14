package nl.novi.backend.les12.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class TeacherDto {
    //Iedereen mag hier bij hoef niet met getters en setters te werken
    public Long id;


    //validators in dto annonations
    @NotBlank
    public String firstName;


    @Size(min=3, max=128)
    public String lastName;

    @Past
    public LocalDate dob;

    @Max(value=100000)
    public int salary;
}