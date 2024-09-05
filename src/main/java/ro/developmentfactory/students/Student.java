package ro.developmentfactory.students;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String departamentName;
    private int averageGrade;

}
