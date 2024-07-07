package ro.developmentfactory.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private List<Student> students;

    @BeforeEach
    void setUp() {
        students = Arrays.asList(
                new Student("John", "Doe", 30, "Male", "Mechanical Engineering", 122),
                new Student("Jane", "Smith", 22, "Female", "Computer Engineering", 324),
                new Student("Ann", "Gurnmeister", 56, "Female", "Biotech Engineering", 64),
                new Student("Elon", "Gated", 27, "Male", "Mechanical Engineering", 90),
                new Student("Justin", "Case", 26, "Male", "Computer Engineering", 340),
                new Student("Dianne", "Ameter", 31, "Female", "Biotech Engineering", 128),
                new Student("Joss", "Sticks", 23, "Male", "Computer Engineering", 20)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFirstNamePrefixes")
    @DisplayName("Filter students by first name prefix")
    void filteredStudents_FilterByFirstName_Prefix(String prefix, int expectedSize) {
        // When filtering students by first name starting with the given prefix
        List<Student> filteredStudents = students.stream()
                .filter(student -> student.getFirstName().startsWith(prefix))
                .collect(Collectors.toList());

        // Then assert the size of the filtered list
        assertEquals(expectedSize, filteredStudents.size());
    }

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
        Map<String, Set<String>> namesByDepertament = students.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        mapping(
                                Student::getFirstName,
                                toSet())
                ));

        assertEquals(3, namesByDepertament.size());

        Set<String> mechanicalEngineers = namesByDepertament.get("Mechanical Engineering");
        assertTrue(mechanicalEngineers.contains("John"));
        assertTrue(mechanicalEngineers.contains("Elon"));

        Set<String> computerEngineers = namesByDepertament.get("Computer Engineering");
        assertTrue(computerEngineers.contains("Jane"));
        assertTrue(computerEngineers.contains("Justin"));
        assertTrue(computerEngineers.contains("Joss"));

        Set<String> biotechEngineers = namesByDepertament.get("Biotech Engineering");
        assertTrue(biotechEngineers.contains("Ann"));
        assertTrue(biotechEngineers.contains("Dianne"));
    }

    @Test
    @DisplayName("Group names by department with empty student list")
    void GroupNamesByDepartment_EmptyStudentList() {
        // Simulate an empty list of students
        List<Student> emptyStudentsList = Collections.emptyList();

        Map<String, Set<String>> namesByDepertament = emptyStudentsList.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        mapping(
                                Student::getFirstName,
                                toSet())
                ));

        assertEquals(0, namesByDepertament.size(), "Number of departments when students list is empty");
    }

    @Test
    @DisplayName("Test scenario when there are no Mechanical Engineers in the list")
    void DoesNotExistDepertamentMechanical_GroupingAndMappingNames() {
        List<Student> noMechanicalEngineers = students.stream()
                .filter(student -> !student.getDepartamentName().equals("Mechanical Engineering"))
                .collect(toList());

        Map<String, Set<String>> namesByDepertament = noMechanicalEngineers.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        mapping(
                                Student::getFirstName,
                                toSet())
                ));

        assertFalse(namesByDepertament.containsKey("Mechanical Engineering"), "No Mechanical Engineering students");
    }

    @Test
    @DisplayName("Test scenario when there are no Computer Engineering in the list")
    void DoesNotExistDepertamentComputer_GroupingAndMappingNames() {
        List<Student> noComputerEngineering = students.stream()
                .filter(student -> !student.getDepartamentName().equals("Mechanical Engineering"))
                .collect(toList());

        Map<String, Set<String>> namesByDepertament = noComputerEngineering.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        mapping(
                                Student::getFirstName,
                                toSet())
                ));

        assertFalse(namesByDepertament.containsKey("Mechanical Engineering"), "No Mechanical Engineering students");
    }

    @Test
    @DisplayName("Test scenario when there are no Biotech Engineering in the list")
    void DoesNotExistDepertamentBiotech_GroupingAndMappingNames() {
        List<Student> noBiotechEngineering = students.stream()
                .filter(student -> !student.getDepartamentName().equals("Biotech Engineering"))
                .collect(toList());

        Map<String, Set<String>> namesByDepertament = noBiotechEngineering.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        mapping(
                                Student::getFirstName,
                                toSet())
                ));

        assertFalse(namesByDepertament.containsKey("Biotech Engineering"), "No Mechanical Engineering students");
    }

    @Test
    @DisplayName("Count total number of students")
    void TotalStudents_CountAllStudents() {
        long total = students.stream().count();

        assertEquals(7, total);
    }

    @Test
    @DisplayName("Find maximum age in the list of students")
    void MaxAgeOptional_FindMaxAgeInList() {
        OptionalInt maxAgeOptional = students.stream()
                .mapToInt(s -> s.getAge())
                .max();

        assertTrue(maxAgeOptional.isPresent(), "OptionalInt should contain a value");
        assertEquals(56, maxAgeOptional.getAsInt(), "Max age should be 56");
    }

    @Test
    @DisplayName("Extract distinct department names")
    void DepartmentNames_ExtractDistinctDepartmentNames() {
        List<String> departmentNames = students.stream()
                .map(Student::getDepartamentName)
                .distinct().toList();

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
        Map<String, Long> studentsByDepertament = students.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        counting())
                );
        assertEquals(3, studentsByDepertament.size());

        assertEquals(2, studentsByDepertament.get("Mechanical Engineering"));
        assertEquals(3, studentsByDepertament.get("Computer Engineering"));
        assertEquals(2, studentsByDepertament.get("Biotech Engineering"));

    }

    @Test
    @DisplayName("Filter students under 30 years old")
    void StudentsUnder30_FilterStudentByAge() {
        List<Student> studentsUnder30 = students.stream()
                .filter(student -> student.getAge() < 30)
                .collect(Collectors.toList());

        assertEquals(4, studentsUnder30.size());

        List<String> expectedStudentNamesUnder30 = Arrays.asList(
                "Jane Smith",
                "Elon Gated",
                "Justin Case",
                "Joss Sticks"
        );
        List<String> actualStudentNamesUnder30 = studentsUnder30.stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(Collectors.toList());

        assertEquals(expectedStudentNamesUnder30, actualStudentNamesUnder30);
    }


    @Test
    @DisplayName("Filter students by average grade between 50 and 100")
           void RankBetween50and100_FilterStudentsByAverageGrade() {
        //Given: We have a list of students.
        List<Student> rankBetween50and100 = students.stream()
                // When: We filter the students based on their average grades, selecting only those with grades between 50 and 100.
                .filter(student -> student.getAverageGrade() >= 50 && student.getAverageGrade() <= 100)
                .collect(toList());

// Then: We verify that the resulting number of students is 2, and their names are “Ann Gurnmeister” and “Elon Gated.”
        assertEquals(2, rankBetween50and100.size());

        List<String> expectedNames = Arrays.asList("Ann Gurnmeister", "Elon Gated");
        List<String> actualNames = rankBetween50and100.stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(toList());

        assertEquals(expectedNames, actualNames);
    }

    @Test
    @DisplayName("Calculate average age by gender")
    void AverageAge_CalculateAverageAgeByGender() {
        Map<String, Double> averageAge = students.stream()
                .collect(groupingBy(
                        Student::getGender,
                        averagingInt(Student::getAge)));

        double expectedMaleAverageAge = (30 + 27 + 26 + 23) / 4.0;
        double expectedFemaleAverageAge = (22 + 56 + 31) / 3.0;

        assertEquals(expectedMaleAverageAge, averageAge.get("Male"), 0.001);
        assertEquals(expectedFemaleAverageAge, averageAge.get("Female"), 0.001);
    }

    @Test
    @DisplayName("Find department with most students")
    void DepartmentWithMaxStudents_FindDepartmentWithMostStudents() {
        Map<String, Long> studentsByDepartment = students.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        counting()
                ));

        Optional<Map.Entry<String, Long>> maxDepartment = studentsByDepartment.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        assertEquals("Computer Engineering", maxDepartment.get().getKey());
        assertEquals(3, maxDepartment.get().getValue().longValue());
    }

    @Test
    @DisplayName("Calculate average rank by department")
    void AverageRank_CalculateAverageRankByDepartment() {
        Map<String, Double> averageRank = students.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        averagingInt(Student::getAverageGrade)));

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
        Map<String, Optional<Student>> averageRankMax = students.stream()
                .collect(groupingBy(
                        Student::getDepartamentName,
                        maxBy(Comparator.comparingInt(Student::getAverageGrade))));

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
        List<Student> sortedList = students.stream()
                .sorted(Comparator.comparingInt(Student::getAverageGrade))
                .collect(toList());

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
        Optional<Student> secondHighestRank = students.stream()
                .sorted(Comparator.comparingInt(Student::getAverageGrade))
                .skip(1)
                .findFirst();

        String expectedSecondHighestStudent = "Ann Gurnmeister";

        assertTrue(secondHighestRank.isPresent());
        assertEquals(expectedSecondHighestStudent, secondHighestRank.get().getFirstName() + " " + secondHighestRank.get().getLastName());
    }

    @Test
    @DisplayName("GetFiltredStudents when list is null should throw IllegalArgumentException")
    void testGetFiltredStudents_NullList() {
        StudentService studentService = new StudentService();
        assertThrows(NullPointerException.class, () -> studentService.getFiltredStudents(null));
    }

    @Test
    @DisplayName("GetFiltredStudents when list is empty should return empty list")
    void testGetFiltredStudents_EmptyList() {
        StudentService studentService = new StudentService();
        List<Student> emptyList = Collections.emptyList();
        List<Student> filteredStudents = studentService.getFiltredStudents(emptyList);
        assertTrue(filteredStudents.isEmpty());
    }

    @Test
    @DisplayName("GetSecondHighestRank when list is null should throw IllegalArgumentException")
    void testGetSecondHighestRank_NullList() {
        StudentService studentService = new StudentService();
        assertThrows(NullPointerException.class, () -> studentService.getSecondHighestRank(null));
    }

    @Test
    @DisplayName("GetSecondHighestRank when list is empty should return empty optional")
    void testGetSecondHighestRank_EmptyList() {
        StudentService studentService = new StudentService();
        List<Student> emptyList = Collections.emptyList();
        Optional<Student> secondHighestRank = studentService.getSecondHighestRank(emptyList);
        assertFalse(secondHighestRank.isPresent());
    }
}




