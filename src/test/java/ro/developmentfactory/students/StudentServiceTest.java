package ro.developmentfactory.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;
    private final List<Student> students = Arrays.asList(
                new Student("John", "Doe", 30, "Male", "Mechanical Engineering", 122),
                new Student("Jane", "Smith", 22, "Female", "Computer Engineering", 324),
                new Student("Ann", "Gurnmeister", 56, "Female", "Biotech Engineering", 64),
                new Student("Elon", "Gated", 27, "Male", "Mechanical Engineering", 90),
                new Student("Justin", "Case", 26, "Male", "Computer Engineering", 340),
                new Student("Dianne", "Ameter", 31, "Female", "Biotech Engineering", 128),
                new Student("Joss", "Sticks", 23, "Male", "Computer Engineering", 20)
    );

@BeforeEach
    void setUp(){
    studentService = new StudentService();}


    static List<Arguments> provideFirstNamePrefixes() {
        return Arrays.asList(
                Arguments.of("A", 1),  // Expected one student starting with 'A'
                Arguments.of("B", 0),  // Expected no students starting with 'B'
                Arguments.of("C", 0),  // Expected no students starting with 'C'
                Arguments.of("D", 1),  // Expected one student starting with 'D'
                Arguments.of("F", 0),  // Expected no students starting with 'F'
                Arguments.of("G", 0),  // Expected no students starting with 'G'
                Arguments.of("H", 0),  // Expected no students starting with 'H'
                Arguments.of("J", 4),  // Expected four students starting with 'J'
                Arguments.of("K", 0),  // Expected no students starting with 'K'
                Arguments.of("L", 0),  // Expected no students starting with 'L'
                Arguments.of("M", 0),  // Expected no students starting with 'M'
                Arguments.of("N", 0),  // Expected no students starting with 'N'
                Arguments.of("V", 0),  // Expected no students starting with 'V'
                Arguments.of("X", 0),  // Expected no students starting with 'X'
                Arguments.of("Z", 0),  // Expected no students starting with 'Z'
                Arguments.of("Q", 0),  // Expected no students starting with 'Q'
                Arguments.of("W", 0),  // Expected no students starting with 'W'
                Arguments.of("E", 1),  // Expected one student starting with 'E'
                Arguments.of("R", 0),  // Expected no students starting with 'R'
                Arguments.of("T", 0),  // Expected no students starting with 'T'
                Arguments.of("Y", 0),  // Expected no students starting with 'Y'
                Arguments.of("U", 0),  // Expected no students starting with 'U'
                Arguments.of("I", 0),  // Expected no students starting with 'I'
                Arguments.of("O", 0),  // Expected no students starting with 'O'
                Arguments.of("P", 0)   // Expected no students starting with 'P'
        );
    }

    @Test
    @DisplayName("Group names by department")
    void NamesByDepertament_GroupingAndMappingNames() {
        Map<String, Set<String>> namesByDepertament = studentService.getNamesByDepertament(students);

        assertEquals(3, namesByDepertament.size());

        Set<String> mechanicalEngineers = namesByDepertament.get("Mechanical Engineering");
        assertFalse(mechanicalEngineers.contains("John"));
        assertFalse(mechanicalEngineers.contains("Elon"));

        Set<String> computerEngineers = namesByDepertament.get("Computer Engineering");
        assertFalse(computerEngineers.contains("Jane"));
        assertFalse(computerEngineers.contains("Justin"));
        assertFalse(computerEngineers.contains("Joss"));

        Set<String> biotechEngineers = namesByDepertament.get("Biotech Engineering");
        assertFalse(biotechEngineers.contains("Ann"));
        assertFalse(biotechEngineers.contains("Dianne"));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Group names by departament with null student list")
    void GroupNamesByDepartment_NullOrEmtyStudentList(List<Student> studentList) {
    Map<String, Set<String>> namesByDepartament = studentService.getNamesByDepertament(studentList);
    assertNull(namesByDepartament);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Count total number of students with null student list")
    void CountTotalNumberOfStudents_NullOrEmtyStudentList(List<Student> studentList) {
    OptionalInt resul = studentService.getTotalStudents(studentList);
    assertFalse(resul.isPresent());
    }


    @Test
    @DisplayName("Count total number of students")
    void TotalStudents_CountAllStudents() {
        OptionalInt result = studentService.getTotalStudents(students);
        assertTrue(result.isPresent());
        assertEquals(7,result.getAsInt());
    }

    @Test
    @DisplayName("Find maximum age in the list of students")
    void MaxAgeOptional_FindMaxAgeInList() {
        OptionalInt maxAgeOptional = studentService.getMaxAgeOptional(students);
        assertTrue(maxAgeOptional.isPresent(), "OptionalInt should contain a value");
        assertEquals(56, maxAgeOptional.getAsInt(), "Max age should be 56");
    }

    @Test
    @DisplayName("Extract distinct department names")
    void DepartmentNames_ExtractDistinctDepartmentNames() {
        List<String> departmentNames = studentService.getDdepartmentNames(students);

        assertEquals(3, departmentNames.size());
        List<String> expectedDepartmentNames = Arrays.asList(
                "Mechanical Engineering",
                "Computer Engineering",
                "Biotech Engineering"
        );
        assertEquals(expectedDepartmentNames, departmentNames);
    }

    @Test
    @DisplayName("Count students per department")
    void StudentsByDepertament_CountStudentsPerDepartment() {
        Map<String, Long> studentsByDepertament = studentService.getStudentsByDepertament(students);
        assertEquals(3, studentsByDepertament.size());
        assertEquals(2, studentsByDepertament.get("Mechanical Engineering"));
        assertEquals(3, studentsByDepertament.get("Computer Engineering"));
        assertEquals(2, studentsByDepertament.get("Biotech Engineering"));

    }
    @Test
    @DisplayName("Filter students under 30 years old")
    void StudentsUnder30_FilterStudentByAge() {
        List<Student> studentsUnder30 = studentService.getStudentsUnder30(students);
        assertEquals(4, studentsUnder30.size());
    }


    @Test
    @DisplayName("Filter students by average grade between 50 and 100")
           void RankBetween50and100_FilterStudentsByAverageGrade() {
        //Given: We have a list of students.
        List<Student> rankBetween50and100 = studentService.getRankBetween50and100(students);
                // When: We filter the students based on their average grades, selecting only those with grades between 50 and 100.

// Then: We verify that the resulting number of students is 2, and their names are “Ann Gurnmeister” and “Elon Gated.”
        assertEquals(2, rankBetween50and100.size());
    }

    @Test
    @DisplayName("Calculate average age by gender")
    void AverageAge_CalculateAverageAgeByGender() {
        Map<String, Double> averageAge = studentService.getAverageAge(students);
        double expectedMaleAverageAge = (30 + 27 + 26 + 23) / 4.0;
        double expectedFemaleAverageAge = (22 + 56 + 31) / 3.0;

        assertEquals(expectedMaleAverageAge, averageAge.get("Male"), 0.001);
        assertEquals(expectedFemaleAverageAge, averageAge.get("Female"), 0.001);
    }

    @Test
    @DisplayName("Find department with most students")
    void DepartmentWithMaxStudents_FindDepartmentWithMostStudents() {
        Map<String, Long> studentsByDepartment = studentService.getMaxStudents(students);
        assertEquals(3, studentsByDepartment.size());
        assertEquals(2, studentsByDepartment.get("Mechanical Engineering"));
    }

    @Test
    @DisplayName("Calculate average rank by department")
    void AverageRank_CalculateAverageRankByDepartment() {
        Map<String, Double> averageRank = studentService.getAverageRank(students);
        double expectedMechanicalEngAverageRank = (122.0 + 90.0) / 2.0;
        double expectedComputerEngAverageRank = (324.0 + 340.0 + 20.0) / 3.0;
        double expectedBiotechEngAverageRank = (64.0 + 128.0) / 2.0;

        assertEquals(expectedMechanicalEngAverageRank, averageRank.get("Mechanical Engineering"), 0.001);
        assertEquals(expectedComputerEngAverageRank, averageRank.get("Computer Engineering"), 0.001);
        assertEquals(expectedBiotechEngAverageRank, averageRank.get("Biotech Engineering"), 0.001);
    }

    @Test
    @DisplayName("Find student with highest rank in each department")
    void AverageRankMax_FindStudentWithHighestRankInEachDepartment() {
        Map<String, Optional<Student>> averageRankMax = studentService.getAverageRankMax(students);

        String expectedMechanicalEngMaxStudent = "John Doe";
        String expectedComputerEngMaxStudent = "Justin Case";
        String expectedBiotechEngMaxStudent = "Dianne Ameter";

        assertEquals(expectedMechanicalEngMaxStudent, averageRankMax.get("Mechanical Engineering").get().getFirstName() + " " + averageRankMax.get("Mechanical Engineering").get().getLastName());
        assertEquals(expectedComputerEngMaxStudent, averageRankMax.get("Computer Engineering").get().getFirstName() + " " + averageRankMax.get("Computer Engineering").get().getLastName());
        assertEquals(expectedBiotechEngMaxStudent, averageRankMax.get("Biotech Engineering").get().getFirstName() + " " + averageRankMax.get("Biotech Engineering").get().getLastName());

    }

    @Test
    @DisplayName("Sort students by average grade")
    void SortedList_SortStudentsByAverageGrade() {
        List<Student> sortedList = studentService.getSortedList(students);

        List<String> expectedSortedNames = Arrays.asList(
                "Joss Sticks",
                "Ann Gurnmeister",
                "Elon Gated",
                "John Doe",
                "Dianne Ameter",
                "Jane Smith",
                "Justin Case"
        );

        for (int i = 0; i < sortedList.size(); i++) {
            assertEquals(expectedSortedNames.get(i), sortedList.get(i).getFirstName() + " " + sortedList.get(i).getLastName());
        }
    }

    @Test
    @DisplayName("Find student with second highest rank")
    void SecondHighestRank_FindStudentWithSecondHighestRank() {
        Optional<Student> secondHighestRank = studentService.getSecondHighestRank(students);

        String expectedSecondHighestStudent = "Ann Gurnmeister";

        assertTrue(secondHighestRank.isPresent());
        assertEquals(expectedSecondHighestStudent, secondHighestRank.get().getFirstName() + " " + secondHighestRank.get().getLastName());
    }
}





