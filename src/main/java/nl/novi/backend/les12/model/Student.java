package nl.novi.backend.les12.model;
import jakarta.persistence.*;


@Entity
@Table(name="Students")
public class Student {

    @Id
    @GeneratedValue
    Long studentNr;

    @Column(name="full_name")
    String fullName;

    @Column(name="phone_number")
    int phoneNumber;

    public Long getStudentNr() {
        return studentNr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
