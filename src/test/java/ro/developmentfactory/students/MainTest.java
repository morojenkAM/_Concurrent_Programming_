package ro.developmentfactory.students;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

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

    @Test
    void FilteredStudentsTest() {
        List<Student> filteredStudents = students.stream()
                .filter(a -> a.getFirstName().startsWith("A"))//Find a list of students whose first name starts with 'A'
                .collect(toList());

        assertEquals(1, filteredStudents.size());
        assertEquals("Ann", filteredStudents.get(0).getFirstName());

    }

    @Test
    void NamesByDepertamentTest() {
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
    void TotalStudentsTest() {
        long total = students.stream().count();

        assertEquals(7, total);
    }

    @Test
    void MaxAgeOptionalTest() {
        OptionalInt maxAgeOptional = students.stream()
                .mapToInt(s -> s.getAge())
                .max();

        assertTrue(maxAgeOptional.isPresent(), "OptionalInt should contain a value");
        assertEquals(56, maxAgeOptional.getAsInt(), "Max age should be 56");
    }

    @Test
    void DepartmentNamesTest() {
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
    void StudentsByDepertamentTest(){
        Map<String, Long> studentsByDepertament   = students.stream()
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
    void studentsUnder30Test() {
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

        // Extract actual student names from objects
        List<String> actualStudentNamesUnder30 = studentsUnder30.stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(Collectors.toList());

        assertEquals(expectedStudentNamesUnder30, actualStudentNamesUnder30);
    }



    @Test
    void RankBetween50and100Test(){
        List <Student> rankBetween50and100 = students.stream()
                .filter(student -> student.getAverageGrade() >=50 && student.getAverageGrade() <=100)
                .collect(toList());

        assertEquals(2, rankBetween50and100.size());

        List<String> expectedNames = Arrays.asList("Ann Gurnmeister", "Elon Gated");

        List<String> actualNames = rankBetween50and100.stream()
                .map(student -> student.getFirstName() + " " + student.getLastName())
                .collect(toList());

        assertEquals(expectedNames, actualNames);
    }

    @Test
    void AverageAge(){
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
    void DepartmentWithMaxStudentsTest() {
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
    void AverageRankTest(){
        Map<String, Double> averageRank   = students.stream()
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
    void averageRankMax(){
        Map<String, Optional<Student>> averageRankMax   = students.stream()
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
    void sortedListTest(){
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
    void SecondHighestRankTest(){
        Optional<Student> secondHighestRank = students.stream()
                .sorted(Comparator.comparingInt(Student ::getAverageGrade))
                .skip(1)
                .findFirst();

        String expectedSecondHighestStudent = "Ann Gurnmeister";

        assertTrue(secondHighestRank.isPresent());
        assertEquals(expectedSecondHighestStudent, secondHighestRank.get().getFirstName() + " " + secondHighestRank.get().getLastName());
    }
    }



